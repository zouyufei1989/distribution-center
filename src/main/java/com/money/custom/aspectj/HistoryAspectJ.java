package com.money.custom.aspectj;

import com.google.common.collect.Lists;
import com.money.custom.rabbitmq.QueueConsts;
import com.money.custom.utils.RabbitMqUtils;
import com.money.framework.base.annotation.AddChangeLog;
import com.money.framework.base.entity.ExcelMultipartFile;
import com.money.framework.base.entity.OperationalEntity;
import com.money.framework.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Aspect
public class HistoryAspectJ implements ApplicationContextAware {

    final static Logger logger = LoggerFactory.getLogger(HistoryAspectJ.class);

    protected ApplicationContext applicationContext = null;

    @Autowired
    RabbitMqUtils rabbitMqUtils;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Pointcut("execution(* com.money.custom.service.impl.*.*(..))")
    public void doing() {
    }

    @Around("doing() && @annotation(addChangeLog)")
    public Object process(ProceedingJoinPoint point, AddChangeLog addChangeLog) throws Throwable {
        Object[] args = point.getArgs();
        Object result = point.proceed(args);

        if (Objects.isNull(result)) {
            logger.warn("无id返回的方法，无法记录历史操作记录");
        } else {
            Map<String, Object> messageData = new HashMap<>(4);
            messageData.put("updater", getUpdater(args));
            messageData.put("ids", getEntityKeyId(result));
            messageData.put("type", addChangeLog.changeLogEntity().getName());
            messageData.put("createDate", DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            rabbitMqUtils.send(QueueConsts.HISTORY_QUEUE, messageData);

        }

        return result;
    }

    private String getUpdater(Object[] args) {
        String updater = StringUtils.EMPTY;
        Optional<Object> optional = Arrays.stream(args).filter(arg -> arg instanceof OperationalEntity).findAny();
        if (optional.isPresent()) {
            updater = ((OperationalEntity) optional.get()).getUpdater();
            return updater;
        }

        optional = Arrays.stream(args).filter(arg -> arg instanceof ExcelMultipartFile).findAny();
        if (optional.isPresent()) {
            updater = ((ExcelMultipartFile) optional.get()).getOperationalEntity().getUpdater();
        }
        return updater;
    }

    private List<String> getEntityKeyId(Object result) {
        List<String> ids = new ArrayList<>();
        if (result instanceof String) {
            ids = Lists.newArrayList(result.toString());
        } else if (result instanceof List) {
            ids = (List<String>) result;
        }
        return ids;
    }

}