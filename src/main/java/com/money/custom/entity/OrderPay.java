package com.money.custom.entity;


import com.money.custom.entity.enums.PayTypeEnum;
import com.money.custom.entity.request.PayOrderRequest;
import com.money.framework.base.entity.OperationalEntity;

public class OrderPay extends OperationalEntity {

    private Integer id;
    private String orderBatchId;
    private Integer orderId;
    private Integer sumMoney;
    private Integer actuallyMoney;
    private Integer payType;

    public OrderPay() {}

    public OrderPay(Order order, PayOrderRequest request) {
        this.orderBatchId = order.getBatchId();
        this.orderId = order.getId();
        this.sumMoney = order.getOrderPrice();
        this.actuallyMoney = request.getActuallyMoney() * order.getOrderPrice() / request.getSumMoney();
        this.payType = request.getPayType();

        copyOperationInfo(request);
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderBatchId() {
        return orderBatchId;
    }

    public void setOrderBatchId(String orderBatchId) {
        this.orderBatchId = orderBatchId;
    }

    public Integer getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Integer sumMoney) {
        this.sumMoney = sumMoney;
    }

    public Integer getActuallyMoney() {
        return actuallyMoney;
    }

    public void setActuallyMoney(Integer actuallyMoney) {
        this.actuallyMoney = actuallyMoney;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}
