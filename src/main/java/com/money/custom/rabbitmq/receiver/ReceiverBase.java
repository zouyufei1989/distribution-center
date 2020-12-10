package com.money.custom.rabbitmq.receiver;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
public abstract class ReceiverBase implements ApplicationContextAware {


    ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass().getName());
    }

    void processMessage(Map map) {
        if (Objects.isNull(map) || CollectionUtils.isEmpty(map.entrySet())) {
            getLogger().warn("消息参数为空");
            return;
        }
        try {
            doProcess(map);
            getLogger().info("消息处理完成 - {}",this.getClass().getSimpleName());
        } catch (Exception ex) {
            getLogger().error("消息处理失败:" + ex.getMessage(), ex);
        }
    }

    abstract void doProcess(Map message);
}