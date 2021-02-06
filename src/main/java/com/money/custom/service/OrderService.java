package com.money.custom.service;

import com.money.custom.entity.Order;
import com.money.custom.entity.OrderRefundParams;
import com.money.custom.entity.request.*;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface OrderService extends BaseService {

    List<Order> selectSearchList(QueryOrderRequest request);

    int selectSearchListCount(QueryOrderRequest request);

    Order findById(String id);

    String add(AddOrderRequest request);

    List<String> changeStatus(ChangeOrderStatusRequest request);

    void refund(OrderRefundRequest refundRequest);

    OrderRefundParams queryOrderInfo4Refund(Integer orderId);
}
