package com.money.custom.service;

import com.money.custom.entity.Order;
import com.money.custom.entity.request.AddOrderRequest;
import com.money.custom.entity.request.ChangeOrderStatusRequest;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface OrderService extends BaseService {

    Order findById(String id);

    String add(AddOrderRequest request);

    List<String> changeStatus(ChangeOrderStatusRequest request);

}
