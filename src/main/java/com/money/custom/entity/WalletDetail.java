package com.money.custom.entity;


import com.money.framework.base.entity.OperationalEntity;

public class WalletDetail extends OperationalEntity {

    private Integer id;
    private Integer walletId;
    private Integer moneyChange;
    private Integer changeType;

    private Integer befSumMoney;
    private Integer befUsedMoney;
    private Integer befAvailableMoney;
    private Integer aftSumMoney;
    private Integer aftUsedMoney;
    private Integer aftAvailableMoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    public Integer getMoneyChange() {
        return moneyChange;
    }

    public void setMoneyChange(Integer moneyChange) {
        this.moneyChange = moneyChange;
    }

    public Integer getBefSumMoney() {
        return befSumMoney;
    }

    public void setBefSumMoney(Integer befSumMoney) {
        this.befSumMoney = befSumMoney;
    }

    public Integer getBefUsedMoney() {
        return befUsedMoney;
    }

    public void setBefUsedMoney(Integer befUsedMoney) {
        this.befUsedMoney = befUsedMoney;
    }

    public Integer getBefAvailableMoney() {
        return befAvailableMoney;
    }

    public void setBefAvailableMoney(Integer befAvailableMoney) {
        this.befAvailableMoney = befAvailableMoney;
    }

    public Integer getAftSumMoney() {
        return aftSumMoney;
    }

    public void setAftSumMoney(Integer aftSumMoney) {
        this.aftSumMoney = aftSumMoney;
    }

    public Integer getAftUsedMoney() {
        return aftUsedMoney;
    }

    public void setAftUsedMoney(Integer aftUsedMoney) {
        this.aftUsedMoney = aftUsedMoney;
    }

    public Integer getAftAvailableMoney() {
        return aftAvailableMoney;
    }

    public void setAftAvailableMoney(Integer aftAvailableMoney) {
        this.aftAvailableMoney = aftAvailableMoney;
    }
}
