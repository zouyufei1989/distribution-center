package com.money.custom.rabbitmq.receiver;

import com.money.custom.entity.VisitLog;
import com.money.custom.rabbitmq.QueueConsts;
import com.money.custom.service.VisitLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = QueueConsts.VISIT_LOG_QUEUE)
public class VisitLogReceiver extends ReceiverBase {

    @Autowired
    VisitLogService visitLogService;

    @RabbitHandler
    public void process(Map message) {
        processMessage(message);
    }

    @Override
    void doProcess(Map message) {
        VisitLog log = (VisitLog) message.get("visitLog");
        visitLogService.add(log);
        getLogger().info("添加访问记录");
    }

}