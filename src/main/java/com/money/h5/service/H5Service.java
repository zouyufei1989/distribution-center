package com.money.h5.service;

import com.gexin.fastjson.JSON;
import com.money.custom.entity.Customer;
import com.money.custom.entity.enums.CustomerTypeEnum;
import com.money.custom.entity.enums.RedisKeyEnum;
import com.money.custom.entity.enums.ResponseCodeEnum;
import com.money.custom.entity.request.QueryCustomerRequest;
import com.money.custom.service.CustomerService;
import com.money.framework.base.entity.ResponseBase;
import com.money.framework.base.service.impl.BaseServiceImpl;
import com.money.framework.util.RedisUtils;
import com.money.h5.entity.dto.H5Customer;
import com.money.h5.entity.dto.WechatPhoneResponse;
import com.money.h5.entity.request.AddCustomer4WechatRequest;
import com.money.h5.entity.request.LoginRequest;
import com.money.h5.entity.request.TransWechatInfo2CustomerRequest;
import com.money.h5.entity.response.WechatLoginResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
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
        redisUtils.setObject(RedisKeyEnum.WECHAT_SESSION_KEY.getName() + openId, sessionKey);

        Customer customer = customerService.findByOpenId(openId);

        if (Objects.nonNull(customer) && StringUtils.isNotEmpty(customer.getPhone()) && StringUtils.isNotEmpty(customer.getHeadCover()) && StringUtils.isNotEmpty(customer.getNickName())) {
            return ResponseBase.success(openId, new H5Customer(customer));
        }

        if (Objects.isNull(customer)) {
            getLogger().info("openId {}不存在，自动创建用户", openId);
            AddCustomer4WechatRequest addCustomer4WechatRequest = new AddCustomer4WechatRequest();
            addCustomer4WechatRequest.setOpenId(openId);
            customerService.addFromWechat(addCustomer4WechatRequest);
        }

        ResponseBase error = ResponseBase.error(ResponseCodeEnum.ASK_4_USER_INFO);
        error.setData(openId);
        return error;
    }

    @Transactional
    public ResponseBase completeCustomerInfo(TransWechatInfo2CustomerRequest request) {
        WechatPhoneResponse phoneResponse = wechatService.gainPhone(request);
        String phone = phoneResponse.getPurePhoneNumber();

        Customer customerByOpenId = customerService.findByOpenId(request.getOpenId());
        getLogger().info("openId {} 用户: {}个", request.getOpenId(), Objects.isNull(customerByOpenId) ? 0 : 1);

        QueryCustomerRequest queryCustomerRequest = new QueryCustomerRequest();
        queryCustomerRequest.setExactPhone(phone);
        List<Customer> customersByPhone = customerService.selectSearchList(queryCustomerRequest);
        getLogger().info("phone {} 用户: {}个", phone, customersByPhone.size());

        if (CollectionUtils.isEmpty(customersByPhone)) {
            totalNewFromWechat(request, phone, customerByOpenId);
            return ResponseBase.success();
        }

        Customer customerByPhone = customersByPhone.get(0);
        if (customerByOpenId.getId().equals(customerByPhone.getId())) {
            wechatSameWeb(request, phone, customerByOpenId);
            return ResponseBase.success();
        }

        wechatDiffWeb(request, phoneResponse, customerByOpenId, customerByPhone);
        return ResponseBase.success();

    }

    private void wechatDiffWeb(TransWechatInfo2CustomerRequest request, WechatPhoneResponse phoneResponse, Customer customerByOpenId, Customer customerByPhone) {
        getLogger().info("后台已创建，小程序已创建，且不是同一人");
        customerService.deleteByOpenId(customerByOpenId.getOpenId());
        getLogger().info("删除之前小程序登录自动创建的用户:{}", JSON.toJSONString(customerByOpenId));

        Customer customer = new Customer();
        customer.setId(customerByPhone.getId());
        customer.setNickName(request.getNickName());
        customer.setHeadCover(request.getAvatarUrl());
        customer.setPhone(phoneResponse.getPurePhoneNumber());
        customer.setOpenId(customerByOpenId.getOpenId());
        customer.ofH5(request);
        customerService.edit(customer);
    }

    private void wechatSameWeb(TransWechatInfo2CustomerRequest request, String phone, Customer customerByOpenId) {
        getLogger().info("后台已创建，小程序已创建，且是相同一人");
        totalNewFromWechat(request, phone, customerByOpenId);
    }

    private void totalNewFromWechat(TransWechatInfo2CustomerRequest request, String phone, Customer customerByOpenId) {
        getLogger().info("后台未创建，从小程序添加");
        Customer customer = new Customer();
        customer.setId(customerByOpenId.getId());
        customer.setNickName(request.getNickName());
        customer.setHeadCover(request.getAvatarUrl());
        customer.setPhone(phone);
        customer.ofH5(request);
        customerService.edit(customer);
    }
}
