package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ConsumeRequest extends OperationalEntity {

    @NotNull(message = "请输入订单ID")
    private Integer orderId;
    private Integer orderItemId;
    @NotNull(message = "请输入使用数量")
    @Min(value = 1, message = "使用次数不可小于1")
    private Integer cnt;
    @NotNull(message = "客户id不可为空")
    private Integer customerGroupId;

    public Integer getCustomerGroupId() {
        return customerGroupId;
    }

    public void setCustomerGroupId(Integer customerGroupId) {
        this.customerGroupId = customerGroupId;
    }

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }
}
