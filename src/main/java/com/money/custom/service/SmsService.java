package com.money.custom.service;

import com.money.custom.entity.Sms;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface SmsService extends BaseService {

   void addSms(Sms sms);

   void addSmsSend(Sms sms);

   List<Sms> querySmsToSend(int countLimit);
}
