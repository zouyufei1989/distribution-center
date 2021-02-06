package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderRefundRequest extends OperationalEntity {

    @NotNull(message = "订单id不可为空")
    private Integer orderId;
    @Min(value = 0, message = "退款金额不可小于0")
    private Long refundAmount;
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
    }
}
