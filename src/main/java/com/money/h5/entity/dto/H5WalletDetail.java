package com.money.h5.entity.dto;

import com.money.custom.entity.WalletDetail;
import com.money.framework.util.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description = "余额变更记录")
public class H5WalletDetail {

    @ApiModelProperty(value = "金额")
    private Integer amount;
    @ApiModelProperty(value = "充值日期")
    private String createDate;
    @ApiModelProperty(value = "消费期限")
    private String expireDate;
    @ApiModelProperty(value = "积分比例")
    private Integer bonusRate;

    public H5WalletDetail(WalletDetail item) {
        amount = item.getMoneyChange();
        createDate = DateUtils.format(item.getCreateDate(), "yyyy-MM-dd HH:mm:ss");
        expireDate = item.getCustomer().getCustomerGroup().getExpireDate();
        bonusRate = item.getCustomer().getBonusPlan().getBonusRate();
    }

    public Integer getAmount() {
        return amount;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public Integer getBonusRate() {
        return bonusRate;
    }

    public String getCreateDate() {
        return createDate;
    }
}
