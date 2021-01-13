package com.money.custom.entity;


import com.money.custom.entity.enums.PayTypeEnum;
import com.money.framework.base.entity.OperationalEntity;

public class OrderPayItem extends OperationalEntity {

    private Integer id;
    private Integer orderPayId;
    private Integer orderId;
    private Integer type;
    private Long amount;

    private Customer customer;

    public OrderPayItem() {}

    public OrderPayItem(OrderPay pay, PayTypeEnum type, Long amount) {
        this.orderPayId = pay.getId();
        this.orderId = pay.getOrderId();
        this.type = type.getValue();
        this.amount = amount;

        this.customer = pay.getCustomer();

        copyOperationInfo(pay);
    }

    public Customer getCustomer() {
        return customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderPayId() {
        return orderPayId;
    }

    public void setOrderPayId(Integer orderPayId) {
        this.orderPayId = orderPayId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
