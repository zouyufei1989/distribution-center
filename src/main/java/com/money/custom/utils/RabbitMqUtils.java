package com.money.custom.utils;

import com.money.custom.rabbitmq.QueueConsts;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RabbitMqUtils {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void send(String msgName, Object paramValue, String paramName) {
        Map<String, Object> map = new HashMap<>(1);
        map.put(paramName, paramValue);
        send(msgName, map);
    }

    public void send(String msgName, Map<String, Object> params) {
        rabbitTemplate.convertAndSend(QueueConsts.EXCHANGE, msgName, params);
    }
}
