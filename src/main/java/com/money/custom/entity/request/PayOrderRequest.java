package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;

public class PayOrderRequest extends OperationalEntity {

    private String orderBatchId;
    private Long sumMoney;
    private Long actuallyMoney;

    private Long moneyAmount;
    private Long bonusAmount;
    private Long offLineAmount;

    private Long actuallyMoneyUsed;
    private Long moneyAmountUsed;
    private Long bonusAmountUsed;
    private Long offLineAmountUsed;

    private Integer payType;

    public PayOrderRequest() {}

    public PayOrderRequest(PurchaseRequest request, String orderBatchId) {
        this.orderBatchId = orderBatchId;
        this.sumMoney = request.getSumMoney();
        this.actuallyMoney = request.getActuallyMoney();
        this.payType = request.getPayType();

        this.offLineAmount = request.getExtraMoneyOffline();
        moneyAmountUsed = 0L;
        bonusAmountUsed = 0L;
        offLineAmountUsed = 0L;
        actuallyMoneyUsed = 0L;
        copyOperationInfo(request);
    }

    public boolean fullPay() {
        return actuallyMoney.equals(sumMoney);
    }

    public Long getActuallyMoneyUsed() {
        return actuallyMoneyUsed;
    }

    public void addActuallyMoneyUsed(Long actuallyMoneyUsed) {
        this.actuallyMoneyUsed += actuallyMoneyUsed;
    }

    public Long getMoneyAmountUsed() {
        return moneyAmountUsed;
    }

    public void addMoneyAmountUsed(Long moneyAmountUsed) {
        this.moneyAmountUsed += moneyAmountUsed;
    }

    public Long getBonusAmountUsed() {
        return bonusAmountUsed;
    }

    public void addBonusAmountUsed(Long bonusAmountUsed) {
        this.bonusAmountUsed += bonusAmountUsed;
    }

    public Long getOffLineAmountUsed() {
        return offLineAmountUsed;
    }

    public void addOffLineAmountUsed(Long offLineAmountUsed) {
        this.offLineAmountUsed += offLineAmountUsed;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getPayType() {
        return payType;
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

    public String getOrderBatchId() {
        return orderBatchId;
    }

    public void setOrderBatchId(String orderBatchId) {
        this.orderBatchId = orderBatchId;
    }

    public Long getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(Long moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public Long getBonusAmount() {
        return bonusAmount;
    }

    public void setBonusAmount(Long bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public Long getOffLineAmount() {
        return offLineAmount;
    }

    public void setOffLineAmount(Long offLineAmount) {
        this.offLineAmount = offLineAmount;
    }
}
