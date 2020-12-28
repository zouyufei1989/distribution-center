package com.money.h5.entity.dto;

import com.money.custom.entity.CustomerActivity;
import com.money.custom.entity.OrderConsumption;
import com.money.custom.entity.OrderItem;
import com.money.custom.entity.OrderItemConsumption;
import com.money.framework.util.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "消费记录")
public class H5Consumption {

    @ApiModelProperty(value = "消费门店")
    private String groupName;
    @ApiModelProperty(value = "消费时间")
    private String createDate;
    @ApiModelProperty(value = "消费类型")
    private String consumeType;
    @ApiModelProperty(value = "项目名称")
    private String goodsName;
    @ApiModelProperty(value = "消费次数")
    private Integer cnt;

    public H5Consumption() {}

    public H5Consumption(OrderConsumption consumption) {
        groupName = consumption.getItems().get(0).getGroupName();
        createDate = DateUtils.format(consumption.getCreateDate(), "yyyy-MM-dd HH:mm:ss");
        consumeType = consumption.getOrder().getGoodsTypeName() + "消费";
        goodsName = consumption.getOrder().getGoodsName();
        cnt = consumption.getItems().get(0).getCnt();
    }

    public String getGroupName() {
        return groupName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getConsumeType() {
        return consumeType;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public Integer getCnt() {
        return cnt;
    }
}
