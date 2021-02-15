package com.money.h5.entity.request;

import com.money.h5.entity.H5RequestBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel
public class ReserveRequest extends H5RequestBase {

    @ApiModelProperty(value = "订单id", example = "70")
    @NotNull(message = "请选择要预约的订单")
    private Integer orderId;
    @ApiModelProperty(value = "预约最早时间", example = "09:00:00")
    @NotBlank(message = "请指定预约最早时间")
    private String startTime;
    @ApiModelProperty(value = "预约最晚时间", example = "10:00:00")
    @NotBlank(message = "请指定预约最晚时间")
    private String endTime;
    @ApiModelProperty(value = "预约日期", example = "2021-03-02")
    @NotBlank(message = "请指定预约日期")
    private String date;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
