package com.money.custom.entity.request;

import com.money.custom.entity.BonusPlan;
import com.money.custom.entity.OrderPayItem;

public class BonusRechargeRequest extends RechargeRequest {

    private Integer orderPayItemId;
    private Integer bonusRate;

    public BonusRechargeRequest(Integer amount, OrderPayItem item, BonusPlan bonusPlan) {
        setAmount(amount);
        setCustomerGroupId(item.getCustomer().getCustomerGroup().getParentId());
        setOrderPayItemId(item.getId());
        this.bonusRate = bonusPlan.getBonusRate();

        copyOperationInfo(item);
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
