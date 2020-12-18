package com.money.custom.entity.request;

import com.money.custom.entity.enums.PayTypeEnum;
import com.money.framework.base.entity.OperationalEntity;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class PayOrderRequest extends OperationalEntity {

    private String orderBatchId;
    private Integer sumMoney;
    private Integer actuallyMoney;

    private Integer moneyAmount;
    private Integer bonusAmount;
    private Integer offLineAmount;

    public PayOrderRequest() {}

    public PayOrderRequest(PurchaseRequest request, String orderBatchId) {
        this.orderBatchId = orderBatchId;
        this.sumMoney = request.getSumMoney();
        this.actuallyMoney = request.getActuallyMoney();

        copyOperationInfo(request);
    }

    public Integer getPayType() {
        Integer payType = PayTypeEnum.NONE.getValue();
        if (Objects.nonNull(moneyAmount) && moneyAmount > 0) {
            payType += PayTypeEnum.MONEY.getValue();
        }
        if (Objects.nonNull(bonusAmount) && bonusAmount > 0) {
            payType += PayTypeEnum.BOUNS.getValue();
        }
        if (Objects.nonNull(offLineAmount) && offLineAmount > 0) {
            payType += PayTypeEnum.OFFLINE.getValue();
        }

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
