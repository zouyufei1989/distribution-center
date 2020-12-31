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
        //TODO 根据手机号查询用户 如果存在更新openid
        WechatLoginResponse wechatLoginResponse = wechatService.jscode2session(loginRequest.getJsCode());
        Assert.isTrue(wechatLoginResponse.success(), "获取openId失败:" + wechatLoginResponse.getErrmsg());

        String openId = wechatLoginResponse.getOpenId();
        String sessionKey = wechatLoginResponse.getSession_key();
        redisUtils.setObject(RedisKeyEnum.WECHAT_SESSION_KEY + openId, sessionKey);

        Customer customer = customerService.findByOpenId(openId);
        if (Objects.isNull(customer)) {
            AddCustomer4WechatRequest addCustomer4WechatRequest = new AddCustomer4WechatRequest();
            addCustomer4WechatRequest.setOpenId(openId);
            customerService.addFromWechat(addCustomer4WechatRequest);
        } else {
            if (StringUtils.isEmpty(customer.getPhone()) || StringUtils.isEmpty(customer.getHeadCover()) && StringUtils.isEmpty(customer.getNickName())) {
                ResponseBase error = ResponseBase.error(ResponseCodeEnum.ASK_4_USER_INFO);
                error.setData(openId);
                return error;
            }
        }

        return ResponseBase.success(openId);
    }

    public ResponseBase completeCustomerInfo(TransWechatInfo2CustomerRequest request) {
        Customer byId = customerService.findByOpenId(request.getOpenId());
        Assert.isTrue(StringUtils.isEmpty(byId.getNickName()) && StringUtils.isEmpty(byId.getHeadCover()), "信息已同步，无需更新");
        //TODO 更新手机号
        //WechatPhoneResponse phoneResponse = wechatService.gainPhone(request);

        Customer customer = new Customer();
        customer.setOpenId(request.getOpenId());
        customer.setNickName(request.getNickName());
        customer.setHeadCover(request.getAvatarUrl());
        //customer.setPhone(phoneResponse.getPurPhoneNumber());
        customer.ofH5(request);
        customerService.edit(customer);

        return ResponseBase.success();
    }
}
