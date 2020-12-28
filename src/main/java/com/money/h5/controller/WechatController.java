package com.money.h5.controller;

import com.money.framework.base.entity.ResponseBase;
import com.money.h5.entity.request.GainPhoneRequest;
import com.money.h5.entity.request.LoginRequest;
import com.money.h5.entity.request.TransWechatInfo2CustomerRequest;
import com.money.h5.entity.response.QueryActivityDetailResponse;
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

    @ApiOperation(value = "登录，返回openId", notes = "code: 9-需要补齐手机号，10-需要补齐微信用户信息")
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseBase login(@Valid @RequestBody LoginRequest request, BindingResult bindingResult) {
        return h5Service.login(request);
    }

    @ApiOperation(value = "同步微信用户手机号")
    @ResponseBody
    @RequestMapping(value = "completePhone", method = RequestMethod.POST)
    public ResponseBase completePhone(@Valid @RequestBody GainPhoneRequest request, BindingResult bindingResult) {
        return h5Service.completePhone(request);
    }

    @ApiOperation(value = "同步微信用户基础信息")
    @ResponseBody
    @RequestMapping(value = "completeCustomerInfo", method = RequestMethod.POST)
    public ResponseBase completeCustomerInfo(@Valid @RequestBody TransWechatInfo2CustomerRequest request, BindingResult bindingResult) {
        return h5Service.completeCustomerInfo(request);
    }

}
