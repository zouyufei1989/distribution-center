package com.money.custom.entity.request;

import com.money.custom.entity.OrderPay;
import com.money.framework.base.entity.OperationalEntity;

public class DeductionRequest extends OperationalEntity {

    private Integer customerGroupId;
    private Long amount;
    private Integer orderPayItemId;

    public DeductionRequest(OrderPay orderPay, Long amount, String payItemId) {
        this.customerGroupId = orderPay.getCustomer().getCustomerGroup().getId();
        this.amount = amount;
        this.orderPayItemId = Integer.parseInt(payItemId);

        copyOperationInfo(orderPay);
    }

    public Integer getOrderPayItemId() {
        return orderPayItemId;
    }

    public void setOrderPayItemId(Integer orderPayItemId) {
        this.orderPayItemId = orderPayItemId;
    }

    public Integer getCustomerGroupId() {
        return customerGroupId;
    }

    public void setCustomerGroupId(Integer customerGroupId) {
        this.customerGroupId = customerGroupId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
