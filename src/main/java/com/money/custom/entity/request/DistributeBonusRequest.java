package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

public class DistributeBonusRequest extends OperationalEntity {

    @NotNull(message = "积分数量不可为空")
    @Min(value = 1, message = "积分数最小为1")
    private Integer amount;
    @NotEmpty(message = "请选择下发积分客户")
    private List<Integer> customerGroupIds = new ArrayList<>();

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public List<Integer> getCustomerGroupIds() {
        return customerGroupIds;
    }

    public void setCustomerGroupIds(List<Integer> customerGroupIds) {
        this.customerGroupIds = customerGroupIds;
    }


}
