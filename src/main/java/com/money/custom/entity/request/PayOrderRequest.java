package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;

import java.util.Objects;

public class PayOrderRequest extends OperationalEntity {

    private String orderBatchId;
    private Integer sumMoney;
    private Integer actuallyMoney;

    private Integer moneyAmount;
    private Integer bonusAmount;
    private Integer offLineAmount;

    private Integer payType;

    public PayOrderRequest() {}

    public PayOrderRequest(PurchaseRequest request, String orderBatchId) {
        this.orderBatchId = orderBatchId;
        this.sumMoney = request.getSumMoney();
        this.actuallyMoney = request.getActuallyMoney();
        this.payType = request.getPayType();
        this.offLineAmount = request.getExtraMoneyOffline();
        if (Objects.isNull(this.offLineAmount)) {
            this.offLineAmount = 0;
        }

        copyOperationInfo(request);
    }

    public Integer getPayType() {
        return payType;
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

    public String getOrderBatchId() {
        return orderBatchId;
    }

    public void setOrderBatchId(String orderBatchId) {
        this.orderBatchId = orderBatchId;
    }

    public Integer getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(Integer moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public Integer getBonusAmount() {
        return bonusAmount;
    }

    public void setBonusAmount(Integer bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public Integer getOffLineAmount() {
        return offLineAmount;
    }

    public void setOffLineAmount(Integer offLineAmount) {
        this.offLineAmount = offLineAmount;
    }
}
