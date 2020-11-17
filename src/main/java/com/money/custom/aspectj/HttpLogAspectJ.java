package com.money.custom.aspectj;

import com.gexin.fastjson.JSON;
import com.money.custom.entity.HttpLog;
import com.money.custom.entity.request.HttpRequest;
import com.money.custom.rabbitmq.QueueConsts;
import com.money.custom.utils.RabbitMqUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class HttpLogAspectJ implements ThrowsAdvice {

    private final static Logger logger = LoggerFactory.getLogger(HttpLogAspectJ.class);

    @Autowired
    RabbitMqUtils rabbitMqUtils;

    @Pointcut("execution(public * com.money.framework.util.HttpUtils.*(..))  ")
    @Order(1)
    public void anyMethod() {
    }


    @Around(value = "anyMethod()")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        // 访问目标方法的参数：
        Object[] args = point.getArgs();
        Object returnValue = point.proceed(args);

        if (args[0] instanceof HttpRequest) {
            try {
                HttpRequest httpRequest = (HttpRequest) args[0];
                HttpLog log = new HttpLog();
                log.setRequest(httpRequest.getUrl());
                log.setParams(httpRequest.getParamStr());
                log.setType(httpRequest.getLogTypeEnum().getValue());
                log.setResponse(JSON.toJSONString(returnValue));
                log.setHeaders(httpRequest.getHeaderStr());

                rabbitMqUtils.send(QueueConsts.HTTP_LOG_QUEUE, log, "log");
            } catch (Exception ex) {
                logger.error("记录httpLog异常", ex);
            }
        }

        return returnValue;
    }

}