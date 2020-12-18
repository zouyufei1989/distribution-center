package com.money.custom.entity.request;

import com.money.custom.entity.OrderItem;

import java.util.Map;

public class QueryOrderItemRequest extends QueryGridRequestBase {

    private OrderItem orderItem = new OrderItem();

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("orderItem", orderItem);
        return params;
    }

    public void setOrderId(Integer orderId){
        orderItem.setOrderId(orderId);
    }

}
