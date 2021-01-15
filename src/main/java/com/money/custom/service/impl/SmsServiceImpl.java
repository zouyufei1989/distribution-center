package com.money.custom.service.impl;

import com.money.custom.dao.SmsDao;
import com.money.custom.entity.Sms;
import com.money.custom.entity.enums.IEnumKeyValue;
import com.money.custom.entity.enums.SmsStatusEnum;
import com.money.custom.entity.enums.SmsTypeEnum;
import com.money.custom.entity.response.SendSmsResponse;
import com.money.custom.rabbitmq.QueueConsts;
import com.money.custom.service.AliyunService;
import com.money.custom.service.SmsService;
import com.money.custom.utils.RabbitMqUtils;
import com.money.framework.base.service.impl.BaseServiceImpl;
import com.money.framework.util.EnumUtils;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class SmsServiceImpl extends BaseServiceImpl implements SmsService {

    @Autowired
    SmsDao smsDao;
    @Autowired
    AliyunService aliyunService;
    @Autowired
    RabbitMqUtils rabbitMqUtils;

    @Override
    public void addSms(Sms sms) {
        getLogger().info("新增短信通知:{}", sms.toString());
        smsDao.add(sms);
    }

    @Override
    public void addSmsSend(Sms sms) {
        getLogger().info("立即发送短信:{}", sms.toString());
        smsDao.add(sms);
        rabbitMqUtils.send(QueueConsts.MSG_QUEUE, sms.getId(), "id");
    }

    @Override
    public List<Sms> querySmsToSend(int countLimit) {
        return smsDao.querySmsToSend(countLimit);
    }
}
