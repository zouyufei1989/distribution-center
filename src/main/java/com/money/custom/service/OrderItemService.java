package com.money.custom.service;

import com.money.custom.entity.OrderItem;
import com.money.custom.entity.request.ChangeOrderStatusRequest;
import com.money.custom.entity.request.QueryOrderItemRequest;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface OrderItemService extends BaseService {

    List<OrderItem> selectSearchList(QueryOrderItemRequest request);

    OrderItem findById(String id);

    List<String> addBatch(List<OrderItem> items);

    List<String> changeStatus(ChangeOrderStatusRequest request);

}
