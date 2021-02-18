package com.money.h5.entity.request;

import com.money.h5.entity.H5RequestBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel
public class CancelReserveRequest extends H5RequestBase {

    @ApiModelProperty(value = "预约ID", example = "2")
    @NotNull(message = "预约ID不可为空")
    private Integer reservationId;

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }
}
