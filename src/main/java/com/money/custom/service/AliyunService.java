package com.money.custom.service;

import com.money.custom.entity.response.SendSmsResponse;
import com.money.framework.base.service.BaseService;

public interface AliyunService extends BaseService {

   SendSmsResponse sendSms(String phone, String param, String signName, String templateCode);

}
