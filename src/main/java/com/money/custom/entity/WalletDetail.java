package com.money.custom.entity;


import com.money.custom.entity.enums.WalletChangeTypeEnum;
import com.money.custom.entity.request.RechargeRequest;
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

    public WalletDetail() {}

    public WalletDetail(RechargeRequest rechargeRequest, Wallet wallet) {
        this.changeType = WalletChangeTypeEnum.RECHARGE.getValue();
        this.moneyChange = rechargeRequest.getAmount();

        this.walletId = wallet.getId();
        this.befSumMoney = wallet.getSumMoney();
        this.befUsedMoney = wallet.getUsedMoney();
        this.befAvailableMoney = wallet.getAvailableMoney();
        this.aftSumMoney = wallet.getSumMoney() + this.moneyChange;
        this.aftUsedMoney = wallet.getUsedMoney();
        this.aftAvailableMoney = wallet.getAvailableMoney() + this.moneyChange;

        copyOperationInfo(rechargeRequest);
    }

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
