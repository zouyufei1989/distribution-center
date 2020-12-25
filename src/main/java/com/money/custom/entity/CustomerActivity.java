package com.money.custom.entity;

import com.money.framework.base.entity.BaseEntity;

public class CustomerActivity extends BaseEntity {


    private Integer activityId;
    private String activitySerialNumber;
    private String activityName;
    private Integer availableCnt;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivitySerialNumber() {
        return activitySerialNumber;
    }

    public void setActivitySerialNumber(String activitySerialNumber) {
        this.activitySerialNumber = activitySerialNumber;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getAvailableCnt() {
        return availableCnt;
    }

    public void setAvailableCnt(Integer availableCnt) {
        this.availableCnt = availableCnt;
    }
}
