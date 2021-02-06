package com.money.custom.entity.request;

import java.util.Map;

public class QueryOrderRefundRequest extends QueryGridRequestBase {

    private String orderId;

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("orderId", orderId);
        return params;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
