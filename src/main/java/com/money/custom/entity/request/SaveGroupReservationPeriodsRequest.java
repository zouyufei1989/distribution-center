package com.money.custom.entity.request;

import com.money.custom.entity.GroupReservationPeriod;
import com.money.framework.base.entity.OperationalEntity;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class SaveGroupReservationPeriodsRequest extends OperationalEntity {

    @Valid
    @NotEmpty(message = "预约时间段设置不可为空")
    private List<GroupReservationPeriod> periods;

    public List<GroupReservationPeriod> getPeriods() {
        return periods;
    }

    public void setPeriods(List<GroupReservationPeriod> periods) {
        this.periods = periods;
    }
}
