package com.money.custom.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = IntelApplication.class)
public class RabbitMQTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

//    @Test
    public void testSendMsg() {
        Map<String, Object> map = new HashMap<>();
        map.put("type", "aaaaa");
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend(QueueConsts.EXCHANGE, QueueConsts.HISTORY_QUEUE, map);
    }
}
