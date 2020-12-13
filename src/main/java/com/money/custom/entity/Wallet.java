package com.money.custom.entity;


import com.money.custom.entity.request.RechargeRequest;
import com.money.framework.base.entity.OperationalEntity;

public class Wallet extends OperationalEntity {

    private Integer id;
    private Integer sumMoney;
    private Integer usedMoney;
    private Integer availableMoney;

    public void recharge(RechargeRequest request) {
        this.sumMoney += request.getAmount();
        this.availableMoney += request.getAmount();
        copyOperationInfo(request);
    }

    public static Wallet totalNew(OperationalEntity operationEntry) {
        Wallet wallet = new Wallet();
        wallet.setSumMoney(0);
        wallet.setUsedMoney(0);
        wallet.setAvailableMoney(0);
        wallet.copyOperationInfo(operationEntry);
        return wallet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Integer sumMoney) {
        this.sumMoney = sumMoney;
    }

    public Integer getUsedMoney() {
        return usedMoney;
    }

    public void setUsedMoney(Integer usedMoney) {
        this.usedMoney = usedMoney;
    }

    public Integer getAvailableMoney() {
        return availableMoney;
    }

    public void setAvailableMoney(Integer availableMoney) {
        this.availableMoney = availableMoney;
    }
}
