package com.money.custom.entity;

import com.money.framework.base.entity.BaseEntity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class GroupReservationPeriod extends BaseEntity {

    private Integer id;
    @NotNull(message = "可预约人数不可为空")
    @Min(value = 0, message = "可预约人数不可小于0")
    private Integer cnt;
    private Integer status;
    @NotBlank(message = "开始时间不可为空")
    private String startTime;
    @NotBlank(message = "结束时间不可为空")
    private String endTime;

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
