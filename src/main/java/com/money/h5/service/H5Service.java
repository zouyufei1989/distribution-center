package com.money.h5.service;

import com.gexin.fastjson.JSON;
import com.money.custom.entity.Customer;
import com.money.custom.entity.Employee;
import com.money.custom.entity.Sms;
import com.money.custom.entity.enums.RedisKeyEnum;
import com.money.custom.entity.enums.ResponseCodeEnum;
import com.money.custom.entity.request.QueryCustomerRequest;
import com.money.custom.entity.request.QueryEmployeeRequest;
import com.money.custom.service.AssignActivityService;
import com.money.custom.service.CustomerService;
import com.money.custom.service.EmployeeService;
import com.money.custom.service.SmsService;
import com.money.custom.utils.VerifyCodeUtils;
import com.money.framework.base.entity.ResponseBase;
import com.money.framework.base.exception.CustomSpecException;
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

        Customer customer = customerService.findByPhone(loginRequest.getPhone());
        getLogger().info("phone - {} 顾客 ：{}", loginRequest.getPhone(), JSON.toJSONString(customer));

        if (Objects.isNull(customer)) {
            customer = customerService.findByOpenId(openId);
            getLogger().info("openId - {} 顾客 ：{}", openId, JSON.toJSONString(customer));
        }

        Assert.notNull(customer, ResponseCodeEnum.CUSTOMER_NO_GROUP.getName());
        Assert.notNull(customer.getCustomerGroup(), ResponseCodeEnum.CUSTOMER_NO_GROUP.getName());
        Assert.notNull(customer.getCustomerGroup().getId(), ResponseCodeEnum.CUSTOMER_NO_GROUP.getName());

        if (StringUtils.isNotEmpty(customer.getPhone()) && StringUtils.isNotEmpty(customer.getHeadCover()) && StringUtils.isNotEmpty(customer.getNickName())) {
            getLogger().info("客户登录: openId - {} phone - {}.", openId, loginRequest.getPhone());
            return ResponseBase.success(openId, new H5Customer(customer));
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
        getLogger().info("通过openId {} 查询到员工信息 {}", openId, employees.size());
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
            getLogger().info("通过phone {} 查询到员工信息", phone);
            return employees.get(0);
        }

        return null;

    }

    @Transactional
    public ResponseBase completeCustomerInfo(TransWechatInfo2CustomerRequest request) {
        String phone = request.getPhone();
        getLogger().info("登录的手机号:[{}]", phone);
        Assert.isTrue(request.getPhone().length() == 11, "手机号格式错误");

        if (tryEmployee(request, phone)) {
            final ResponseBase success = ResponseBase.success();
            markAsEmployee(success);
            getLogger().info("客户补充信息: phone - {}.", request.getPhone());
            return success;
        }

        Customer customerByPhone = customerService.findByPhone(phone);
        getLogger().info("phone {} 用户: {}", phone, JSON.toJSONString(customerByPhone));

        Customer customer = new Customer();
        customer.setId(customerByPhone.getId());
        customer.setNickName(request.getNickName());
        customer.setHeadCover(request.getAvatarUrl());
        customer.setOpenId(request.getOpenId());
        customer.ofH5(request);
        customerService.edit(customer);

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
        if (request.isTest()) {
            return ResponseBase.success(request.getPhone(), request.getOpenId());
        }

        Assert.isTrue(StringUtils.isNoneBlank(request.getPhone()), "手机号不可为空");

        String redisKey = RedisKeyEnum.SMS_VERIFY_CODE.getName() + request.getPhone();
        String code = redisUtils.getObject(redisKey, String.class);
        Assert.isTrue(StringUtils.equals(code, request.getSmsCode()), ResponseCodeEnum.WRONG_SMS_VERIFY_CODE.getName());

        final Employee employee = tryEmployee(request.getPhone());
        if (Objects.nonNull(employee)) {
            ResponseBase success = ResponseBase.success(request.getPhone(), employee.getOpenId());
            markAsEmployee(success);
            getLogger().info("员工短息登录: phone - {}.", request.getPhone());
            return success;
        }

        getLogger().info("顾客短息登录: phone - {}.", request.getPhone());
        String openId = autoRegisterIfNewPhone(request.getPhone(), request.isAutoCreate());

        return ResponseBase.success(request.getPhone(), openId);
    }

    public ResponseBase phoneLogin(PhoneLoginRequest request) {
        Assert.hasText(request.getJsCode(), "jsCode丢失");
        WechatLoginResponse wechatLoginResponse = wechatService.jscode2session(request.getJsCode());
        redisUtils.setObject(RedisKeyEnum.WECHAT_SESSION_KEY.getName() + request.getPhone(), wechatLoginResponse.getSession_key());
        String openId = wechatLoginResponse.getOpenId();

        WechatPhoneResponse wechatPhoneResponse = wechatService.gainPhone(request);
        String phone = wechatPhoneResponse.getPurePhoneNumber();
        Assert.hasText(phone, "手机号不可为空");

        final Employee employee = tryEmployee(phone);
        if (Objects.nonNull(employee)) {
            employee.setHeadCover(request.getAvatarUrl());
            employee.setNickName(request.getNickName());
            employee.setOpenId(wechatLoginResponse.getOpenId());
            employeeService.edit(employee);
            getLogger().info("更新员工信息 {}", JSON.toJSONString(employee));

            ResponseBase success = ResponseBase.success(phone, employee.getOpenId());
            markAsEmployee(success);
            getLogger().info("员工手机登录: phone - {}.", phone);
            return success;
        }

        getLogger().info("顾客手机登录: phone - {}.", phone);
        autoRegisterIfNewPhone(phone, request.isAutoCreate());

        Customer byPhone = customerService.findByPhone(phone);
        byPhone.setNickName(request.getNickName());
        byPhone.setHeadCover(request.getAvatarUrl());
        byPhone.setOpenId(wechatLoginResponse.getOpenId());
        customerService.edit(byPhone);
        getLogger().info("更新顾客信息 {}", JSON.toJSONString(byPhone));

        return ResponseBase.success(phone, openId);
    }

    String autoRegisterIfNewPhone(String phone, boolean autoCreate) {
        QueryCustomerRequest request = new QueryCustomerRequest();
        request.setExactPhone(phone);
        List<Customer> customers = customerService.selectSearchList(request);
        if (CollectionUtils.isNotEmpty(customers)) {
            getLogger().info("手机号 {} 已存在", phone);
            return customers.get(0).getOpenId();
        }

        if (autoCreate) {
            Customer customer = new Customer();
            customer.setPhone(phone);
            customer.ofH5(phone);
            customerService.add(customer);
            getLogger().info("创建新用户 {}", phone);
            return StringUtils.EMPTY;
        }
        throw CustomSpecException.businessError(ResponseCodeEnum.CUSTOMER_NO_GROUP);
    }

    Employee tryEmployee(String phone) {
        QueryEmployeeRequest request = new QueryEmployeeRequest();
        request.getEmployee().setPhone(phone);
        final List<Employee> employees = employeeService.selectSearchList(request);
        if (CollectionUtils.isNotEmpty(employees)) {
            getLogger().info("手机号 {} 已存在", phone);
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
