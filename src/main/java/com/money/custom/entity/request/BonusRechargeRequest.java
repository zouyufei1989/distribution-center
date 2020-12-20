package com.money.custom.entity.request;

import com.money.custom.entity.OrderPayItem;

public class BonusRechargeRequest extends RechargeRequest {

    private Integer orderPayItemId;

    public BonusRechargeRequest(Integer amount, OrderPayItem item) {
        setAmount(amount);
        setCustomerGroupId(item.getCustomer().getCustomerGroup().getParentId());
        setOrderPayItemId(item.getId());

        copyOperationInfo(item);
    }

    public Integer getOrderPayItemId() {
        return orderPayItemId;
    }

    public void setOrderPayItemId(Integer orderPayItemId) {
        this.orderPayItemId = orderPayItemId;
    }
}
