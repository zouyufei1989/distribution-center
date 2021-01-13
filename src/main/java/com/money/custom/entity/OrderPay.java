package com.money.custom.entity;


import com.money.custom.entity.enums.PayTypeEnum;
import com.money.custom.entity.request.PayOrderRequest;
import com.money.framework.base.entity.OperationalEntity;

import java.util.List;

public class OrderPay extends OperationalEntity {

    private Integer id;
    private String orderBatchId;
    private Integer orderId;
    private Long sumMoney;
    private Long actuallyMoney;
    private Integer payType;
    private Long bonusRate;

    private Long moneyAmount;
    private Long bonusAmount;
    private Long offlineAmount;

    private Customer customer;
    private List<OrderPayItem> items;
    private Order order;

    public OrderPay() {}

    public OrderPay(Order order, PayOrderRequest request, boolean lastOne) {
        Long payPercent = order.getOrderPrice() * 100 / request.getSumMoney();

        this.orderBatchId = order.getBatchId();
        this.orderId = order.getId();
        this.sumMoney = order.getOrderPrice();
        this.payType = request.getPayType();

        if (lastOne) {
            this.actuallyMoney = request.getActuallyMoney() - request.getActuallyMoneyUsed();
        } else {
            this.actuallyMoney = request.getActuallyMoney() * payPercent / 100;
            if (request.fullPay()) {
                this.actuallyMoney = order.getOrderPrice();
            }
            request.addActuallyMoneyUsed(this.actuallyMoney);
        }

        if (PayTypeEnum.MONEY.pay(this.payType)) {
            if (lastOne) {
                this.moneyAmount = request.getMoneyAmount() - request.getMoneyAmountUsed();
            } else {
                this.moneyAmount = request.getMoneyAmount() * payPercent / 100;
                if (request.fullPay() && PayTypeEnum.MONEY.payOnly(this.payType)) {
                    this.moneyAmount = order.getOrderPrice();
                }
                request.addMoneyAmountUsed(this.moneyAmount);
            }
        }
        if (PayTypeEnum.BONUS.pay(this.payType)) {
            if (lastOne) {
                this.bonusAmount = request.getBonusAmount() - request.getBonusAmountUsed();
            } else {
                this.bonusAmount = request.getBonusAmount() * payPercent / 100;
                if (request.fullPay() && PayTypeEnum.BONUS.payOnly(this.payType)) {
                    this.bonusAmount = order.getOrderPrice();
                }
                request.addBonusAmountUsed(this.bonusAmount);
            }
        }
        if (PayTypeEnum.OFFLINE.pay(this.payType)) {
            if (lastOne) {
                this.offlineAmount = request.getOffLineAmount() - request.getOffLineAmountUsed();
            } else {
                this.offlineAmount = request.getOffLineAmount() * payPercent / 100;
                if (request.fullPay() && PayTypeEnum.OFFLINE.payOnly(this.payType)) {
                    this.offlineAmount = order.getOrderPrice();
                }
                request.addOffLineAmountUsed(this.offlineAmount);
            }
        }

        copyOperationInfo(request);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderPayItem> getItems() {
        return items;
    }

    public void setItems(List<OrderPayItem> items) {
        this.items = items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getBonusRate() {
        return bonusRate;
    }

    public void setBonusRate(Long bonusRate) {
        this.bonusRate = bonusRate;
    }

    public void setMoneyAmount(Long moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public void setBonusAmount(Long bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public void setOfflineAmount(Long offlineAmount) {
        this.offlineAmount = offlineAmount;
    }

    public Long getMoneyAmount() {
        return moneyAmount;
    }

    public Long getBonusAmount() {
        return bonusAmount;
    }

    public Long getOfflineAmount() {
        return offlineAmount;
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

    public Long getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Long sumMoney) {
        this.sumMoney = sumMoney;
    }

    public Long getActuallyMoney() {
        return actuallyMoney;
    }

    public void setActuallyMoney(Long actuallyMoney) {
        this.actuallyMoney = actuallyMoney;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

}
