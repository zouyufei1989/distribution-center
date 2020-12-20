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
    private Integer bonusRate;

    private Integer moneyAmount;
    private Integer bonusAmount;
    private Integer offlineAmount;

    private Customer customer;

    public OrderPay() {}

    public OrderPay(Order order, PayOrderRequest request, boolean lastOne) {
        int payPercent = order.getOrderPrice() * 100 / request.getSumMoney();

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getBonusRate() {
        return bonusRate;
    }

    public void setBonusRate(Integer bonusRate) {
        this.bonusRate = bonusRate;
    }

    public void setMoneyAmount(Integer moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public void setBonusAmount(Integer bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public void setOfflineAmount(Integer offlineAmount) {
        this.offlineAmount = offlineAmount;
    }

    public Integer getMoneyAmount() {
        return moneyAmount;
    }

    public Integer getBonusAmount() {
        return bonusAmount;
    }

    public Integer getOfflineAmount() {
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
