package com.money.custom.entity;


import com.money.custom.entity.enums.WalletChangeTypeEnum;
import com.money.custom.entity.request.DeductionRequest;
import com.money.custom.entity.request.RechargeRequest;
import com.money.framework.base.entity.OperationalEntity;

public class WalletDetail extends OperationalEntity {

    private Integer id;
    private Integer walletId;
    private Long moneyChange;
    private Integer changeType;

    private Long befSumMoney;
    private Long befUsedMoney;
    private Long befAvailableMoney;
    private Long aftSumMoney;
    private Long aftUsedMoney;
    private Long aftAvailableMoney;

    private Customer customer;

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

    public WalletDetail(DeductionRequest rechargeRequest, Wallet wallet) {
        this.changeType = WalletChangeTypeEnum.DEDUCTION.getValue();
        this.moneyChange = rechargeRequest.getAmount();

        this.walletId = wallet.getId();
        this.befSumMoney = wallet.getSumMoney();
        this.befUsedMoney = wallet.getUsedMoney();
        this.befAvailableMoney = wallet.getAvailableMoney();
        this.aftSumMoney = wallet.getSumMoney();
        this.aftUsedMoney = wallet.getUsedMoney() + this.moneyChange;
        this.aftAvailableMoney = wallet.getAvailableMoney() - this.moneyChange;

        copyOperationInfo(rechargeRequest);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getChangeType() {
        return changeType;
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

    public Long getMoneyChange() {
        return moneyChange;
    }

    public void setMoneyChange(Long moneyChange) {
        this.moneyChange = moneyChange;
    }

    public Long getBefSumMoney() {
        return befSumMoney;
    }

    public void setBefSumMoney(Long befSumMoney) {
        this.befSumMoney = befSumMoney;
    }

    public Long getBefUsedMoney() {
        return befUsedMoney;
    }

    public void setBefUsedMoney(Long befUsedMoney) {
        this.befUsedMoney = befUsedMoney;
    }

    public Long getBefAvailableMoney() {
        return befAvailableMoney;
    }

    public void setBefAvailableMoney(Long befAvailableMoney) {
        this.befAvailableMoney = befAvailableMoney;
    }

    public Long getAftSumMoney() {
        return aftSumMoney;
    }

    public void setAftSumMoney(Long aftSumMoney) {
        this.aftSumMoney = aftSumMoney;
    }

    public Long getAftUsedMoney() {
        return aftUsedMoney;
    }

    public void setAftUsedMoney(Long aftUsedMoney) {
        this.aftUsedMoney = aftUsedMoney;
    }

    public Long getAftAvailableMoney() {
        return aftAvailableMoney;
    }

    public void setAftAvailableMoney(Long aftAvailableMoney) {
        this.aftAvailableMoney = aftAvailableMoney;
    }
}
