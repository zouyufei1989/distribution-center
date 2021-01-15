package com.money.h5.controller;

import com.money.custom.service.AssignActivityService;
import com.money.framework.base.entity.ResponseBase;
import com.money.h5.entity.H5RequestBase;
import com.money.h5.entity.request.LoginRequest;
import com.money.h5.entity.request.QueryByIdRequest;
import com.money.h5.entity.request.SmsLoginRequest;
import com.money.h5.entity.request.TransWechatInfo2CustomerRequest;
import com.money.h5.service.H5Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "微信服务")
@RequestMapping(value = "wechat")
@Controller
@CrossOrigin(allowCredentials = "true", maxAge = 3600)
public class WechatController {

    @Autowired
    H5Service h5Service;
    @Autowired
    AssignActivityService assignActivityService;

    @ApiOperation(value = "登录，返回openId", notes = "code: 9-需要补齐手机号，10-需要补齐微信用户信息")
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseBase login(@Valid @RequestBody LoginRequest request, BindingResult bindingResult) {
        return h5Service.login(request);
    }

    @ApiOperation(value = "同步微信用户基础信息")
    @ResponseBody
    @RequestMapping(value = "completeCustomerInfo", method = RequestMethod.POST)
    public ResponseBase completeCustomerInfo(@Valid @RequestBody TransWechatInfo2CustomerRequest request, BindingResult bindingResult) {
        return h5Service.completeCustomerInfo(request);
    }

    @ApiOperation(value = "领取活动", notes = "openId=abc")
    @ResponseBody
    @RequestMapping(value = "claimActivity", method = RequestMethod.POST)
    public ResponseBase claimActivity(@Valid @RequestBody QueryByIdRequest request, BindingResult bindingResult) {
        assignActivityService.claimActivity(Integer.parseInt(request.getId()), request.getOpenId());
        return ResponseBase.success();
    }

    @ApiOperation(value = "获取登录短信验证码", notes = "返回值中data为验证码有效期(秒)")
    @ResponseBody
    @RequestMapping(value = "smsVerifyCode", method = RequestMethod.POST)
    public ResponseBase smsVerifyCode(@Valid @RequestBody H5RequestBase request, BindingResult bindingResult) {
        return h5Service.sendSmsVerifyCode(request.getPhone());
    }

    @ApiOperation(value = "短信验证码登录，登录成功返回手机号")
    @ResponseBody
    @RequestMapping(value = "smsLogin", method = RequestMethod.POST)
    public ResponseBase smsLogin(@Valid @RequestBody SmsLoginRequest request, BindingResult bindingResult) {
        return h5Service.smsLogin(request);
    }

}
