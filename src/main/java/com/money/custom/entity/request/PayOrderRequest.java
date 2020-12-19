package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;

public class PayOrderRequest extends OperationalEntity {

    private String orderBatchId;
    private Integer sumMoney;
    private Integer actuallyMoney;

    private Integer moneyAmount;
    private Integer bonusAmount;
    private Integer offLineAmount;

    private Integer actuallyMoneyUsed;
    private Integer moneyAmountUsed;
    private Integer bonusAmountUsed;
    private Integer offLineAmountUsed;

    private Integer payType;

    public PayOrderRequest() {}

    public PayOrderRequest(PurchaseRequest request, String orderBatchId) {
        this.orderBatchId = orderBatchId;
        this.sumMoney = request.getSumMoney();
        this.actuallyMoney = request.getActuallyMoney();
        this.payType = request.getPayType();

        this.offLineAmount = request.getExtraMoneyOffline();
        moneyAmountUsed = 0;
        bonusAmountUsed = 0;
        offLineAmountUsed = 0;
        actuallyMoneyUsed = 0;
        copyOperationInfo(request);
    }

    public boolean fullPay() {
        return actuallyMoney.equals(sumMoney);
    }

    public Integer getActuallyMoneyUsed() {
        return actuallyMoneyUsed;
    }

    public void addActuallyMoneyUsed(Integer actuallyMoneyUsed) {
        this.actuallyMoneyUsed += actuallyMoneyUsed;
    }

    public Integer getMoneyAmountUsed() {
        return moneyAmountUsed;
    }

    public void addMoneyAmountUsed(Integer moneyAmountUsed) {
        this.moneyAmountUsed += moneyAmountUsed;
    }

    public Integer getBonusAmountUsed() {
        return bonusAmountUsed;
    }

    public void addBonusAmountUsed(Integer bonusAmountUsed) {
        this.bonusAmountUsed += bonusAmountUsed;
    }

    public Integer getOffLineAmountUsed() {
        return offLineAmountUsed;
    }

    public void addOffLineAmountUsed(Integer offLineAmountUsed) {
        this.offLineAmountUsed += offLineAmountUsed;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
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
