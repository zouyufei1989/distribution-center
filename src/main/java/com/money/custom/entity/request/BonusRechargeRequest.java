package com.money.custom.entity.request;

import com.money.custom.entity.BonusPlan;
import com.money.custom.entity.OrderPayItem;
import com.money.custom.entity.enums.BonusChangeTypeEnum;

public class BonusRechargeRequest extends RechargeRequest {

    private Integer orderPayItemId;
    private Integer bonusRate;
    private BonusChangeTypeEnum changeTypeEnum;
    private Integer srcCustomerMoneyPay;
    private Integer srcCustomerMoneyAvailable;

    public BonusRechargeRequest(Integer amount, OrderPayItem item, BonusPlan bonusPlan,BonusChangeTypeEnum changeTypeEnum) {
        setAmount(amount);
        setCustomerGroupId(item.getCustomer().getCustomerGroup().getParentId());
        setOrderPayItemId(item.getId());
        this.bonusRate = bonusPlan.getBonusRate();
        this.changeTypeEnum = changeTypeEnum;

        srcCustomerMoneyPay = item.getAmount();
        srcCustomerMoneyAvailable = item.getCustomer().getWallet().getAvailableMoney();

        copyOperationInfo(item);
    }

    public Integer getSrcCustomerMoneyPay() {
        return srcCustomerMoneyPay;
    }

    public void setSrcCustomerMoneyPay(Integer srcCustomerMoneyPay) {
        this.srcCustomerMoneyPay = srcCustomerMoneyPay;
    }

    public Integer getSrcCustomerMoneyAvailable() {
        return srcCustomerMoneyAvailable;
    }

    public void setSrcCustomerMoneyAvailable(Integer srcCustomerMoneyAvailable) {
        this.srcCustomerMoneyAvailable = srcCustomerMoneyAvailable;
    }

    public BonusChangeTypeEnum getChangeTypeEnum() {
        return changeTypeEnum;
    }

    public void setChangeTypeEnum(BonusChangeTypeEnum changeTypeEnum) {
        this.changeTypeEnum = changeTypeEnum;
    }

    public Integer getBonusRate() {
        return bonusRate;
    }

    public Integer getOrderPayItemId() {
        return orderPayItemId;
    }

    public void setOrderPayItemId(Integer orderPayItemId) {
        this.orderPayItemId = orderPayItemId;
    }
}
