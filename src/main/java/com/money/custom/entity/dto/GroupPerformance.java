package com.money.custom.entity.dto;

import com.money.framework.base.entity.OperationalEntity;

public class GroupPerformance extends OperationalEntity {

    private Long sumMoney;
    private String date;

    public Long getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Long sumMoney) {
        this.sumMoney = sumMoney;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
