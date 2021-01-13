package com.money.custom.entity.request;

import com.money.custom.entity.BonusPlan;
import com.money.custom.entity.OrderPayItem;
import com.money.custom.entity.enums.BonusChangeTypeEnum;

public class BonusRechargeRequest extends RechargeRequest {

    private Integer orderPayItemId;
    private Long bonusRate;
    private BonusChangeTypeEnum changeTypeEnum;
    private Long srcCustomerMoneyPay;
    private Long srcCustomerMoneyAvailable;

    public BonusRechargeRequest(Long amount, OrderPayItem item, BonusPlan bonusPlan,BonusChangeTypeEnum changeTypeEnum) {
        setAmount(amount);
        setCustomerGroupId(item.getCustomer().getCustomerGroup().getParentId());
        setOrderPayItemId(item.getId());
        this.bonusRate = bonusPlan.getBonusRate();
        this.changeTypeEnum = changeTypeEnum;

        srcCustomerMoneyPay = item.getAmount();
        srcCustomerMoneyAvailable = item.getCustomer().getWallet().getAvailableMoney();

        copyOperationInfo(item);
    }

    public Long getSrcCustomerMoneyPay() {
        return srcCustomerMoneyPay;
    }

    public void setSrcCustomerMoneyPay(Long srcCustomerMoneyPay) {
        this.srcCustomerMoneyPay = srcCustomerMoneyPay;
    }

    public Long getSrcCustomerMoneyAvailable() {
        return srcCustomerMoneyAvailable;
    }

    public void setSrcCustomerMoneyAvailable(Long srcCustomerMoneyAvailable) {
        this.srcCustomerMoneyAvailable = srcCustomerMoneyAvailable;
    }

    public BonusChangeTypeEnum getChangeTypeEnum() {
        return changeTypeEnum;
    }

    public void setChangeTypeEnum(BonusChangeTypeEnum changeTypeEnum) {
        this.changeTypeEnum = changeTypeEnum;
    }

    public Long getBonusRate() {
        return bonusRate;
    }

    public Integer getOrderPayItemId() {
        return orderPayItemId;
    }

    public void setOrderPayItemId(Integer orderPayItemId) {
        this.orderPayItemId = orderPayItemId;
    }
}
