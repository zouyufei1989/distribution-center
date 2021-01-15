package com.money.custom.task;

import com.money.custom.entity.Sms;
import com.money.custom.rabbitmq.QueueConsts;
import com.money.custom.service.SmsService;
import com.money.custom.utils.RabbitMqUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//@ConditionalOnExpression("'${switch.task}'.equals('on')")
@Component
public class SendSmsTask extends ConfigurableTaskBase {

    @Autowired
    SmsService smsService;
    @Autowired
    RabbitMqUtils rabbitMqUtils;

    @Override
    void execute(Map<String, String> params) {
        int countLimit = useIntParamsIfPresent(params, "countLimit", 20);
        List<Sms> smsList = smsService.querySmsToSend(countLimit);

        getLogger().info("{}短信待发送", smsList.size());
        if (CollectionUtils.isEmpty(smsList)) {
            return;
        }

        smsList.forEach(sms -> rabbitMqUtils.send(QueueConsts.MSG_QUEUE, sms.getId(), "id"));
    }


}
