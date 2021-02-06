package com.money.custom.entity;

import com.money.custom.entity.request.OrderRefundRequest;
import com.money.framework.base.entity.BaseEntity;

public class OrderRefund extends BaseEntity {

    private Integer id;
    private Integer orderId;
    private Long refundAmount;
    private Integer type;

    public OrderRefund(){}

    public OrderRefund(OrderRefundRequest refundRequest){
        this.orderId = refundRequest.getOrderId();
        this.refundAmount = refundRequest.getRefundAmount();
        this.type = refundRequest.getType();
        copyOperationInfo(refundRequest);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
