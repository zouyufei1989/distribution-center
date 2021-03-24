package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;

public class QueryReservationCalenderByOrderIdRequest extends OperationalEntity {

    private String startDate;
    private String endDate;
    private Integer orderId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
