package com.money.custom.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.money.custom.entity.response.SendSmsResponse;
import com.money.custom.service.AliyunService;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AliyunServiceImpl extends BaseServiceImpl implements AliyunService {

    @Value("${aliyuncs.sms.keyid}")
    String ACCESS_KEY;
    @Value("${aliyuncs.sms.secret}")
    String SECRET;

    static final String REGION = "cn-hangzhou";


    @Override
    public SendSmsResponse sendSms(String phone, String param, String signName, String templateCode) {
        DefaultProfile profile = DefaultProfile.getProfile(REGION, ACCESS_KEY, SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", REGION);
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", param);

        try {
            CommonResponse smsResponse = client.getCommonResponse(request);
            return new SendSmsResponse(request, smsResponse);
        } catch (Exception ex) {
            getLogger().error("发送短信失败", ex);
            return new SendSmsResponse(request, ex);
        }
    }
}
