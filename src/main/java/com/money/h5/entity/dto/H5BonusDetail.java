package com.money.h5.entity.dto;

import com.money.custom.entity.BonusWalletDetail;
import com.money.custom.entity.enums.BonusChangeTypeEnum;
import com.money.custom.utils.StringFormatUtils;
import com.money.framework.util.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel(description = "积分记录")
public class H5BonusDetail {
    @ApiModelProperty(value = "积分")
    private Long bonusAmount;
    @ApiModelProperty(value = "消费顾客")
    private String customerName;
    @ApiModelProperty(value = "消费/提现金额")
    private Long consumeAmount;
    @ApiModelProperty(value = "积分比例")
    private String bonusRate;
    @ApiModelProperty(value = "消费/提现时间")
    private String createDate;

    public H5BonusDetail(BonusWalletDetail detail) {
        bonusAmount = detail.getBonusChange();
        customerName = detail.getSrcCustomerName();
        consumeAmount = detail.getSrcCustomerMoneyPay();
        createDate = DateUtils.format(detail.getCreateDate(),"yyyy-MM-dd HH:mm:ss");
        if (detail.getChangeType().equals(BonusChangeTypeEnum.BONUSBACK.getValue())) {
            bonusRate = "首次消费返积分";
        } else if(Objects.nonNull(detail.getBonusRate())){
            bonusRate = StringFormatUtils.percent(detail.getBonusRate());
        }

    }

    public String getCreateDate() {
        return createDate;
    }

    public Long getBonusAmount() {
        return bonusAmount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Long getConsumeAmount() {
        return consumeAmount;
    }

    public String getBonusRate() {
        return bonusRate;
    }
}
