package com.money.custom.entity;

import com.money.framework.base.entity.BaseEntity;

public class Reservation extends BaseEntity {


    private Integer id;
    private Integer orderId;
    private Integer customerGroupId;
    private String date;
    private String startTime;
    private String endTime;
    private Integer startTimeSec;
    private Integer endTimeSec;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerGroupId() {
        return customerGroupId;
    }

    public void setCustomerGroupId(Integer customerGroupId) {
        this.customerGroupId = customerGroupId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getStartTimeSec() {
        return startTimeSec;
    }

    public void setStartTimeSec(Integer startTimeSec) {
        this.startTimeSec = startTimeSec;
    }

    public Integer getEndTimeSec() {
        return endTimeSec;
    }

    public void setEndTimeSec(Integer endTimeSec) {
        this.endTimeSec = endTimeSec;
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public void setStatus(Integer status) {
        this.status = status;
    }
}
