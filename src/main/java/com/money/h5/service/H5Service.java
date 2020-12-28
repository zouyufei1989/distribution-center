package com.money.h5.service;

import com.money.custom.entity.Customer;
import com.money.custom.entity.enums.RedisKeyEnum;
import com.money.custom.entity.enums.ResponseCodeEnum;
import com.money.custom.service.CustomerService;
import com.money.framework.base.entity.ResponseBase;
import com.money.framework.base.service.impl.BaseServiceImpl;
import com.money.framework.util.RedisUtils;
import com.money.h5.entity.dto.WechatPhoneResponse;
import com.money.h5.entity.request.AddCustomer4WechatRequest;
import com.money.h5.entity.request.GainPhoneRequest;
import com.money.h5.entity.request.LoginRequest;
import com.money.h5.entity.request.TransWechatInfo2CustomerRequest;
import com.money.h5.entity.response.WechatLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class H5Service extends BaseServiceImpl {

    @Autowired
    CustomerService customerService;
    @Autowired
    WechatService wechatService;
    @Autowired
    RedisUtils redisUtils;

    public ResponseBase login(LoginRequest loginRequest) {
        WechatLoginResponse wechatLoginResponse = wechatService.jscode2session(loginRequest.getJsCode());
        Assert.isTrue(wechatLoginResponse.success(), "获取openId失败:" + wechatLoginResponse.getErrmsg());

        String openId = wechatLoginResponse.getOpenId();
        String sessionKey = wechatLoginResponse.getSession_key();
        redisUtils.setObject(RedisKeyEnum.WECHAT_SESSION_KEY + openId, sessionKey);

        Customer customer = customerService.findById(openId);
        if (Objects.isNull(customer)) {
            AddCustomer4WechatRequest addCustomer4WechatRequest = new AddCustomer4WechatRequest();
            addCustomer4WechatRequest.setOpenId(openId);
            customerService.addFromWechat(addCustomer4WechatRequest);
        }

        if (StringUtils.isEmpty(customer.getPhone())) {
            return ResponseBase.error(ResponseCodeEnum.ASK_4_PHONE);
        }
        if (StringUtils.isEmpty(customer.getHeadCover()) && StringUtils.isEmpty(customer.getNickName())) {
            return ResponseBase.error(ResponseCodeEnum.ASK_4_USER_INFO);
        }

        return ResponseBase.success(openId);
    }

    public ResponseBase completeCustomerInfo(TransWechatInfo2CustomerRequest request) {
        Customer byId = customerService.findById(request.getOpenId());
        Assert.isTrue(StringUtils.isEmpty(byId.getNickName()) && StringUtils.isEmpty(byId.getHeadCover()), "用户微信信息已同步，无需更新");

        Customer customer = new Customer();
        customer.setOpenId(request.getOpenId());
        customer.setNickName(request.getNickName());
        customer.setHeadCover(request.getAvatarUrl());
        customer.ofH5(request);
        customerService.edit(customer);
        return ResponseBase.success();
    }

    public ResponseBase completePhone(GainPhoneRequest request) {
        Customer byId = customerService.findById(request.getOpenId());
        Assert.isTrue(StringUtils.isEmpty(byId.getPhone()), "手机号已录入，无需更新");

        WechatPhoneResponse phoneResponse = wechatService.gainPhone(request);
        Customer customer = new Customer();
        customer.setOpenId(request.getOpenId());
        customer.setPhone(phoneResponse.getPhoneNumber());
        customer.ofH5(request.getOpenId());
        customerService.edit(customer);

        return ResponseBase.success();
    }
}
