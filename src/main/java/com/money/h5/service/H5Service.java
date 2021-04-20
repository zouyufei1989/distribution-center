package com.money.h5.service;

import com.gexin.fastjson.JSON;
import com.money.custom.entity.Customer;
import com.money.custom.entity.Employee;
import com.money.custom.entity.Sms;
import com.money.custom.entity.enums.RedisKeyEnum;
import com.money.custom.entity.enums.ResponseCodeEnum;
import com.money.custom.entity.request.MoAEmployeeRequest;
import com.money.custom.entity.request.QueryCustomerRequest;
import com.money.custom.entity.request.QueryEmployeeRequest;
import com.money.custom.service.AssignActivityService;
import com.money.custom.service.CustomerService;
import com.money.custom.service.EmployeeService;
import com.money.custom.service.SmsService;
import com.money.custom.utils.VerifyCodeUtils;
import com.money.framework.base.entity.ResponseBase;
import com.money.framework.base.service.impl.BaseServiceImpl;
import com.money.framework.util.RedisUtils;
import com.money.h5.entity.dto.H5Customer;
import com.money.h5.entity.dto.H5Employee;
import com.money.h5.entity.dto.WechatPhoneResponse;
import com.money.h5.entity.request.*;
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
    @Autowired
    SmsService smsService;
    @Autowired
    AssignActivityService assignActivityService;
    @Autowired
    EmployeeService employeeService;

    public ResponseBase login(LoginRequest loginRequest) {
        WechatLoginResponse wechatLoginResponse = wechatService.jscode2session(loginRequest.getJsCode());
        Assert.isTrue(wechatLoginResponse.success(), "获取openId失败:" + wechatLoginResponse.getErrmsg());

        String openId = wechatLoginResponse.getOpenId();
        String sessionKey = wechatLoginResponse.getSession_key();
        redisUtils.setObject(RedisKeyEnum.WECHAT_SESSION_KEY.getName() + openId, sessionKey);

        final Employee employee = tryEmployee(openId, loginRequest.getPhone());
        if (Objects.nonNull(employee) && StringUtils.isNotEmpty(employee.getPhone()) && StringUtils.isNotEmpty(employee.getHeadCover()) && StringUtils.isNotEmpty(employee.getNickName())) {
            final ResponseBase success = ResponseBase.success(openId, new H5Employee(employee));
            markAsEmployee(success);
            getLogger().info("员工登录: openId - {} ; phone - {}.", openId, loginRequest.getPhone());
            return success;
        }
        if (Objects.nonNull(employee)) {
            ResponseBase error = ResponseBase.error(ResponseCodeEnum.ASK_4_USER_INFO);
            error.setData(openId);
            error.setExtraData(loginRequest.getPhone());
            markAsEmployee(error);
            getLogger().info("员工登录 - 请求补充信息: openId - {} ; phone - {}.", openId, loginRequest.getPhone());
            return error;
        }

        Customer customer = customerService.findByOpenId(openId);

        if (Objects.nonNull(customer) && StringUtils.isNotEmpty(customer.getPhone()) && StringUtils.isNotEmpty(customer.getHeadCover()) && StringUtils.isNotEmpty(customer.getNickName())) {
            getLogger().info("客户登录: openId - {}.", openId);
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
        error.setExtraData(loginRequest.getPhone());
        getLogger().info("客户登录: openId - {}， 请求补充信息.", openId);
        return error;
    }

    private Employee tryEmployee(String openId, String phone) {
        QueryEmployeeRequest queryEmployeeRequest = new QueryEmployeeRequest();
        queryEmployeeRequest.getEmployee().setOpenId(openId);
        List<Employee> employees = employeeService.selectSearchList(queryEmployeeRequest);
        if (employees.size() == 1) {
            return employees.get(0);
        }

        if (StringUtils.isBlank(phone)) {
            return null;
        }

        queryEmployeeRequest = new QueryEmployeeRequest();
        queryEmployeeRequest.getEmployee().setPhone(phone);
        employees = employeeService.selectSearchList(queryEmployeeRequest);
        if (employees.size() == 1) {
            return employees.get(0);
        }

        return null;

    }

    @Transactional
    public ResponseBase completeCustomerInfo(TransWechatInfo2CustomerRequest request) {
        String phone = request.getPhone();
        getLogger().info("登录的手机号:[{}]", phone);

        if (StringUtils.isEmpty(phone)) {
            Assert.isTrue(StringUtils.isNoneBlank(request.getRawData()), "无法获取手机号");
            Assert.isTrue(StringUtils.isNoneBlank(request.getIv()), "无法获取手机号");
            WechatPhoneResponse phoneResponse = wechatService.gainPhone(request);
            phone = phoneResponse.getPurePhoneNumber();
            getLogger().info("获取小程序手机号 {}", phone);
        }

        if (tryEmployee(request, phone)) {
            final ResponseBase success = ResponseBase.success();
            markAsEmployee(success);
            getLogger().info("客户补充信息: phone - {}.", request.getPhone());
            return success;
        }

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

        wechatDiffWeb(request, phone, customerByOpenId, customerByPhone);
        return ResponseBase.success();

    }

    private boolean tryEmployee(TransWechatInfo2CustomerRequest request, String phone) {
        QueryEmployeeRequest queryEmployeeRequest = new QueryEmployeeRequest();
        queryEmployeeRequest.getEmployee().setPhone(phone);
        final List<Employee> employees = employeeService.selectSearchList(queryEmployeeRequest);
        if (CollectionUtils.isEmpty(employees)) {
            return false;
        }

        final Employee employee = Employee.buildFromWechat4Edit(request, employees.get(0).getId(), phone);
        employeeService.edit(employee);

        return true;
    }

    private void wechatDiffWeb(TransWechatInfo2CustomerRequest request, String phone, Customer customerByOpenId, Customer customerByPhone) {
        getLogger().info("后台已创建，小程序已创建，且不是同一人");
        customerService.deleteByOpenId(customerByOpenId.getOpenId());
        getLogger().info("删除之前小程序登录自动创建的用户:{}", JSON.toJSONString(customerByOpenId));

        Customer customer = new Customer();
        customer.setId(customerByPhone.getId());
        customer.setNickName(request.getNickName());
        customer.setHeadCover(request.getAvatarUrl());
        customer.setPhone(phone);
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


    public ResponseBase sendSmsVerifyCode(String phone) {
        Assert.isTrue(StringUtils.isNoneBlank(phone), "手机号不可为空");

        String key = RedisKeyEnum.SMS_VERIFY_CODE.getName() + phone;
        String code = redisUtils.getObject(key, String.class);
        if (StringUtils.isNotEmpty(code)) {
            ResponseBase error = ResponseBase.error(ResponseCodeEnum.TOO_FREQUENCY_4_SMS_VERIFY_CODE);
            error.setData(redisUtils.getExpiredSec(key) + "：" + code);
            return error;
        }

        code = VerifyCodeUtils.generateDigitalVerifyCode(4);
        redisUtils.setObject(RedisKeyEnum.SMS_VERIFY_CODE.getName() + phone, code, RedisKeyEnum.SMS_VERIFY_CODE.getTimeout());
        getLogger().info("生成短信验证码{} -> {}", phone, code);

        Sms sms = Sms.verifyCodeNotify(phone, code);
        smsService.addSmsSend(sms);

        return ResponseBase.success(RedisKeyEnum.SMS_VERIFY_CODE.getTimeout());
    }

    public ResponseBase smsLogin(SmsLoginRequest request) {
        Assert.isTrue(StringUtils.isNoneBlank(request.getPhone()), "手机号不可为空");

        String redisKey = RedisKeyEnum.SMS_VERIFY_CODE.getName() + request.getPhone();
        String code = redisUtils.getObject(redisKey, String.class);
        Assert.isTrue(StringUtils.equals(code, request.getSmsCode()), ResponseCodeEnum.WRONG_SMS_VERIFY_CODE.getName());

        final Employee employee = tryEmployee(request);
        if (Objects.nonNull(employee)) {
            ResponseBase success = ResponseBase.success(request.getPhone(), employee.getOpenId());
            markAsEmployee(success);
            getLogger().info("员工短息登录: phone - {}.", request.getPhone());
            return success;
        }

        getLogger().info("顾客短息登录: phone - {}.", request.getPhone());
        String openId = autoRegisterIfNewPhone(request);
        return ResponseBase.success(request.getPhone(), openId);
    }

    String autoRegisterIfNewPhone(SmsLoginRequest loginRequest) {
        QueryCustomerRequest request = new QueryCustomerRequest();
        request.setExactPhone(loginRequest.getPhone());
        List<Customer> customers = customerService.selectSearchList(request);
        if (CollectionUtils.isNotEmpty(customers)) {
            getLogger().info("手机号 {} 已存在", loginRequest.getPhone());
            return customers.get(0).getOpenId();
        }

        Customer customer = new Customer();
        customer.setPhone(loginRequest.getPhone());
        customer.ofH5(loginRequest);
        customerService.add(customer);
        getLogger().info("创建新用户 {}", loginRequest.getPhone());
        return StringUtils.EMPTY;
    }

    Employee tryEmployee(SmsLoginRequest loginRequest) {
        QueryEmployeeRequest request = new QueryEmployeeRequest();
        request.getEmployee().setPhone(loginRequest.getPhone());
        final List<Employee> employees = employeeService.selectSearchList(request);
        if (CollectionUtils.isNotEmpty(employees)) {
            getLogger().info("手机号 {} 已存在", loginRequest.getPhone());
            return employees.get(0);
        }
        return null;
    }

    public String getActivityDistributionUniqueCode(QueryByIdRequest request) {
        return assignActivityService.getActivityDistributionUniqueCode(request.getId());
    }

    void markAsEmployee(ResponseBase responseBase) {
        responseBase.addJsonAttr("employee", true);
    }
}
