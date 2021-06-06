package com.money.test;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.gexin.fastjson.JSON;
import com.money.custom.entity.Customer;
import com.money.custom.service.CustomerService;
import com.money.framework.util.upyun.UpYunUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitTest {

    @Autowired
    CustomerService customerService;
    @Autowired
    UpYunUtil upYunUtil;

    @Test
    public void testUpyunParams() throws Exception {
        System.out.println(JSON.toJSONString(upYunUtil.getParams4FormUpload("a.txt")));
    }

    @Test
    public void testFindCustomer() {
        Customer byOpenId = customerService.findByOpenId("oSpLm5PPyJv5tO-HCGnH5mGUR6lA");
        System.out.println(JSON.toJSONString(byOpenId));
    }

    public void testSms() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4GJ1kgWpTmXjUz8tYgV8", "Z5soA9a7e0vC0NPseirNe5CuWWjKxf");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "18698277572");
        request.putQueryParameter("SignName", "aaa");
        request.putQueryParameter("TemplateCode", "SMS_208510248");
        request.putQueryParameter("TemplateParam", "{code:1234}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
