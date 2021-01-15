package com.money.custom.rabbitmq.receiver;

import com.money.custom.dao.SmsDao;
import com.money.custom.entity.Sms;
import com.money.custom.entity.enums.IEnumKeyValue;
import com.money.custom.entity.enums.SmsStatusEnum;
import com.money.custom.entity.enums.SmsTypeEnum;
import com.money.custom.entity.response.SendSmsResponse;
import com.money.custom.rabbitmq.QueueConsts;
import com.money.custom.service.AliyunService;
import com.money.custom.service.SmsService;
import com.money.custom.service.VisitLogService;
import com.money.framework.util.EnumUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RabbitListener(queues = QueueConsts.MSG_QUEUE)
public class MessageReceiver extends ReceiverBase {

    @Value("${aliyuncs.sms.sign.name}")
    String SIGN_NAME;

    @Autowired
    SmsDao smsDao;
    @Autowired
    AliyunService aliyunService;

    @RabbitHandler
    public void process(Map message) {
        processMessage(message);
    }

    @Override
    void doProcess(Map message) {
        Integer smsId = (Integer) message.get("id");
        sendSms(smsId);
        getLogger().info("发送短信");
    }

    void sendSms(Integer id) {
        Sms sms = smsDao.querySmsDetail(id);
        Assert.notNull(sms, "未查询到短信信息");

        if (!sms.getStatus().equals(SmsStatusEnum.PENDING.getValue())) {
            getLogger().warn("短信{}不可重复发送", id);
            return;
        }

        try {
            markSending(sms);

            Optional<IEnumKeyValue> typeOpt = EnumUtils.getByValue(SmsTypeEnum.class, sms.getType());
            Assert.isTrue(typeOpt.isPresent(), "未知短信类型" + sms.getType());
            SendSmsResponse response = aliyunService.sendSms(sms.getPhone(), sms.getParams(), SIGN_NAME, typeOpt.get().getName());
            sms.setRequest(response.getRequest());
            sms.setResponse(response.getResponse());
            sms.setStatus(response.isSuccess() ? SmsStatusEnum.SEND.getValue() : SmsStatusEnum.FAIL.getValue());
            smsDao.updateSmsPushResult(sms);
        } catch (Exception ex) {
            sms.setStatus(SmsStatusEnum.FAIL.getValue());
            sms.setResponse(ex.getMessage());
        } finally {
            smsDao.updateSmsPushResult(sms);
        }
    }

    private void markSending(Sms sms) {
        sms.setStatus(SmsStatusEnum.SENDING.getValue());
        smsDao.updateSmsPushResult(sms);
    }
}