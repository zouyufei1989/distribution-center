package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;

import javax.validation.constraints.NotNull;

public class ChangeGroupReserveFlagRequest extends OperationalEntity {

    @NotNull(message = "请指明是否可预约")
    private Integer reserveFlag;

    public Integer getReserveFlag() {
        return reserveFlag;
    }

    public void setReserveFlag(Integer reserveFlag) {
        this.reserveFlag = reserveFlag;
    }
}
