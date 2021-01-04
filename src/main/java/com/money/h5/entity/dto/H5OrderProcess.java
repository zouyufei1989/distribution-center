package com.money.h5.entity.dto;

import com.money.custom.entity.OrderConsumption;
import com.money.custom.entity.OrderItem;
import com.money.custom.entity.OrderItemConsumption;
import com.money.custom.entity.OrderPay;
import com.money.custom.utils.StringFormatUtils;
import com.money.framework.util.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "订单流转")
public class H5OrderProcess {

    @ApiModelProperty(value = "类型")
    private String type;
    @ApiModelProperty(value = "时间")
    private String date;
    @ApiModelProperty(value = "描述")
    private String desc;

    public H5OrderProcess() {}

    public H5OrderProcess(OrderPay orderPay) {
        this.type = "购买项目";
        this.date = DateUtils.format(orderPay.getCreateDate(), "yyyy-MM-dd HH:mm:ss");
        this.desc = String.format("-%s元", StringFormatUtils.moneyFen2Yuan(orderPay.getActuallyMoney()));
    }

    public H5OrderProcess(OrderItemConsumption consumption) {
        this.type = "消费项目";
        this.date = DateUtils.format(consumption.getCreateDate(), "yyyy-MM-dd HH:mm:ss");
        this.desc = String.format("-%s次", consumption.getCnt());
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getDesc() {
        return desc;
    }
}
