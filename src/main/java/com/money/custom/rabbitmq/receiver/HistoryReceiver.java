package com.money.custom.rabbitmq.receiver;

import com.alibaba.fastjson.JSON;
import com.money.custom.entity.History;
import com.money.custom.entity.enums.ChangeLogEntityEnum;
import com.money.custom.rabbitmq.QueueConsts;
import com.money.custom.service.BannerService;
import com.money.custom.service.HistoryService;
import com.money.framework.base.dao.BaseDao;
import com.money.framework.base.exception.PandabusSpecException;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@RabbitListener(queues = QueueConsts.HISTORY_QUEUE)
public class HistoryReceiver extends ReceiverBase {

    @Autowired
    HistoryService historyService;

    @RabbitHandler
    public void process(Map message) {
        processMessage(message);
    }

    @Override
    void doProcess(Map message) {
        List<String> ids = (List<String>) message.get("ids");
        String type = message.get("type").toString();
        Class clz = (Class) message.get("classType");
        String updater = message.get("updater").toString();
        String createDate = message.get("createDate").toString();
        ChangeLogEntityEnum entityEnum = ChangeLogEntityEnum.get(type);
        if (Objects.isNull(entityEnum)) {
            getLogger().warn("历史记录类型{}不存在", type);
            return;
        }
        List<History> histories = buildHistories(entityEnum, updater, ids, createDate, clz);
        if (CollectionUtils.isNotEmpty(histories)) {
            historyService.addBatch(histories);
        }
    }

    private List<History> buildHistories(ChangeLogEntityEnum changeLogEntity, String updater, List<String> ids, String createDate, Class clz) {
        List<History> historyList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(ids)) {
            for (String id : ids) {
                Object detail4Log = getDetail4Log(clz, id);
                if (Objects.isNull(detail4Log)) {
                    continue;
                }
                historyList.add(new History(id, changeLogEntity.getName(), JSON.toJSONString(detail4Log), updater, createDate));
            }
        }
        return historyList;
    }

    private Object getDetail4Log(Class cls, String id) {
        Object service = applicationContext.getBean(cls);
        Method findById = null;
        try {
            findById = cls.getMethod("findById", String.class);
            return findById.invoke(service, id);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            getLogger().warn("获取历史记录失败", e.getMessage());
        }
        return null;
    }

}