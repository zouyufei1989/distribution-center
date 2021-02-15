package com.money.h5.entity.request;

import com.money.h5.entity.H5RequestBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel("预约日历request： startDate==endDate 则返回当天每个时间段的预约情况")
public class QueryReserveCalenderRequest extends H5RequestBase {

    @ApiModelProperty(value = "开始日期",example = "2021-03-01")
    @NotBlank(message = "开始日期不可为空")
    private String startDate;
    @ApiModelProperty(value = "结束日期",example = "2021-03-31")
    @NotBlank(message = "结束日期不可为空")
    private String endDate;
    @ApiModelProperty(value = "订单id", example = "70")
    @NotNull(message = "订单id不可为空")
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
