package com.money.h5.entity.dto;

import com.money.custom.entity.OrderPay;
import com.money.custom.entity.enums.GoodsTypeEnum;
import com.money.framework.util.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "消费记录")
public class H5PayInfo {

    @ApiModelProperty(value = "消费门店")
    private String groupName;
    @ApiModelProperty(value = "消费时间")
    private String createDate;
    @ApiModelProperty(value = "支付类型")
    private String payType;
    @ApiModelProperty(value = "消费金额")
    private Long amount;
    @ApiModelProperty(value = "商品名称")
    private String goodsName;
    @ApiModelProperty(value = "商品类型")
    private String goodsType;
    @ApiModelProperty(value = "购买次数")
    private Integer cnt;

    public H5PayInfo() {}

    public H5PayInfo(OrderPay orderPay) {
        groupName = orderPay.getGroupName();
        createDate = DateUtils.format(orderPay.getCreateDate(), "yyyy-MM-dd HH:mm:ss");
        payType = orderPay.getOrder().getPayTypeName();
        goodsName = orderPay.getOrder().getGoodsName();
        amount = orderPay.getActuallyMoney();
        cnt = orderPay.getOrder().getOrderCnt() * orderPay.getOrder().getGoodsCnt();
        goodsType = orderPay.getOrder().getGoodsTypeName();
        if (goodsType.equals(GoodsTypeEnum.ACTIVITY.getName())) {
            this.payType = "活动抵消";
        }
    }

    public String getGoodsType() {
        return goodsType;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getPayType() {
        return payType;
    }

    public Long getAmount() {
        return amount;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public Integer getCnt() {
        return cnt;
    }
}
