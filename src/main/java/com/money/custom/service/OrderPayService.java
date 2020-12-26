package com.money.custom.service;

import com.money.custom.entity.OrderItemConsumption;
import com.money.custom.entity.OrderPay;
import com.money.custom.entity.request.PayOrderRequest;
import com.money.custom.entity.request.QueryOrderConsumptionRequest;
import com.money.custom.entity.request.QueryOrderPayRequest;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface OrderPayService extends BaseService {

    List<OrderPay> selectSearchList(QueryOrderPayRequest request);

    int selectSearchListCount(QueryOrderPayRequest request);

    void pay(PayOrderRequest request);

    String add(OrderPay item);

    OrderPay findById(String id);
}
