package com.money.custom.entity.request;

import com.money.custom.entity.Order;

import java.util.Map;

public class QueryOrderRequest extends QueryGridRequestBase {

    Order order = new Order();

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("order", order);
        return params;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setOrderBatchId(String batchId) {
        this.order.setBatchId(batchId);
    }
}
