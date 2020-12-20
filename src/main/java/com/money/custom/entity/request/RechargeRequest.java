package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class RechargeRequest extends OperationalEntity {

    @NotNull(message = "请指定要充值的客户")
    private Integer customerGroupId;
    @NotNull(message = "请输入充值金额")
    private Integer amount;

    public Integer getCustomerGroupId() {
        return customerGroupId;
    }

    public void setCustomerGroupId(Integer customerGroupId) {
        this.customerGroupId = customerGroupId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
