package com.money.custom.entity.request;

import com.money.custom.entity.OrderPay;
import com.money.custom.entity.enums.BonusChangeTypeEnum;

import java.util.Map;

public class QueryOrderPayRequest extends QueryGridRequestBase {

    private OrderPay orderPay = new OrderPay();
    private String openId;

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("orderPay", orderPay);
        params.put("openId", openId);
        return params;
    }

    public OrderPay getOrderPay() {
        return orderPay;
    }

    public void setOrderPay(OrderPay orderPay) {
        this.orderPay = orderPay;
    }

    public void setOrderId(Integer orderId) {
        this.orderPay.setOrderId(orderId);
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
