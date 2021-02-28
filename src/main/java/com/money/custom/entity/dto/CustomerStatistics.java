package com.money.custom.entity.dto;

import com.money.framework.base.entity.BaseEntity;

public class CustomerStatistics extends BaseEntity {

    private Integer id;
    private String customerName;
    private String parentName;
    private String phone;
    private Integer monthCnt;
    private String lastConsumeDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getMonthCnt() {
        return monthCnt;
    }

    public void setMonthCnt(Integer monthCnt) {
        this.monthCnt = monthCnt;
    }

    public String getLastConsumeDate() {
        return lastConsumeDate;
    }

    public void setLastConsumeDate(String lastConsumeDate) {
        this.lastConsumeDate = lastConsumeDate;
    }
}
