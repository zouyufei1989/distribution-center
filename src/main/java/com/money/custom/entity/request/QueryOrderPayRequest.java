package com.money.custom.entity.request;

import com.money.custom.entity.OrderPay;
import com.money.custom.entity.enums.BonusChangeTypeEnum;
import com.money.framework.util.DateUtils;

import java.util.Date;
import java.util.Map;

public class QueryOrderPayRequest extends QueryGridRequestBase {

    private OrderPay orderPay = new OrderPay();
    private String openId;
    private String startDate;
    private String endDate;

    public QueryOrderPayRequest() {}

    public QueryOrderPayRequest(String monthInterval) {
        startDate = DateUtils.nextMonth(Integer.parseInt(monthInterval) * -1) + "-01";
        String end = DateUtils.nowMonth();
        endDate = DateUtils.getMonthEnd(end);
    }

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("orderPay", orderPay);
        params.put("openId", openId);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        return params;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
