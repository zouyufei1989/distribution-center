package com.money.custom.service;

import com.money.custom.entity.OrderPay;
import com.money.custom.entity.request.PayOrderRequest;
import com.money.framework.base.service.BaseService;

public interface OrderPayService extends BaseService {

    void pay(PayOrderRequest request);

    String add(OrderPay item);
}
