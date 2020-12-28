package com.money.custom.entity.request;

import com.money.custom.entity.OrderConsumption;

import java.util.Map;

public class QueryOrderConsumptionRequest extends QueryGridRequestBase {

    private OrderConsumption orderConsumption = new OrderConsumption();
    private String openId;

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("orderConsumption", orderConsumption);
        params.put("openId", openId);
        return params;
    }

    public OrderConsumption getOrderConsumption() {
        return orderConsumption;
    }

    public void setOrderConsumption(OrderConsumption orderConsumption) {
        this.orderConsumption = orderConsumption;
    }

    public void setOrderId(Integer orderId) {
        this.orderConsumption.setOrderId(orderId);
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
