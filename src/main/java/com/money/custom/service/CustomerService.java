package com.money.custom.service;

import com.money.custom.entity.Customer;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.custom.entity.request.MoACustomerRequest;
import com.money.custom.entity.request.QueryCustomerRequest;
import com.money.framework.base.service.BaseService;
import com.money.h5.entity.request.AddCustomer4WechatRequest;

import java.util.List;

public interface CustomerService extends BaseService {

    List<Customer> selectSearchList(QueryCustomerRequest request);

    int selectSearchListCount(QueryCustomerRequest request);

    Customer findById(String id);

    String add(MoACustomerRequest request);

    String addFromWechat(AddCustomer4WechatRequest request);

    String edit(MoACustomerRequest request);

}