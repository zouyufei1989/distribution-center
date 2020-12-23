package com.money.custom.entity;


import com.money.custom.entity.enums.BonusChangeTypeEnum;
import com.money.custom.entity.request.BonusRechargeRequest;
import com.money.custom.entity.request.DeductionRequest;
import com.money.custom.entity.request.DistributeBonusRequest;
import com.money.framework.base.entity.OperationalEntity;

public class BonusWalletDetail extends OperationalEntity {

    private Integer id;
    private Integer bonusWalletId;
    private Integer bonusChange;
    private Integer changeType;
    private Integer befSumBonus;
    private Integer befPendingBonus;
    private Integer befAvailableBonus;
    private Integer befUsedBonus;
    private Integer aftSumBonus;
    private Integer aftPendingBonus;
    private Integer aftAvailableBonus;
    private Integer aftUsedBonus;
    private Integer orderPayItemId;
    private Integer bonusRate;

    private Customer customer;
    private Integer payAmount;

    private Integer srcCustomerMoneyPay;
    private Integer srcCustomerMoneyAvailable;
    private String srcCustomerName;


    public BonusWalletDetail() {}

    public BonusWalletDetail(BonusRechargeRequest request, BonusWallet wallet) {
        bonusRate = request.getBonusRate();
        bonusWalletId = wallet.getId();
        orderPayItemId = request.getOrderPayItemId();
        bonusChange = request.getAmount();
        changeType = request.getChangeTypeEnum().getValue();
        befSumBonus = wallet.getSumBonus();
        befPendingBonus = wallet.getPendingBonus();
        befAvailableBonus = wallet.getAvailableBonus();
        befUsedBonus = wallet.getUsedBonus();

        aftSumBonus = wallet.getSumBonus() + bonusChange;
        aftPendingBonus = wallet.getPendingBonus() + bonusChange;
        aftAvailableBonus = wallet.getAvailableBonus();
        aftUsedBonus = wallet.getUsedBonus();

        srcCustomerMoneyPay = request.getSrcCustomerMoneyPay();
        srcCustomerMoneyAvailable = request.getSrcCustomerMoneyAvailable() - request.getSrcCustomerMoneyPay();

        copyOperationInfo(request);
    }

    public BonusWalletDetail(DeductionRequest request, BonusWallet wallet) {
        bonusWalletId = wallet.getId();
        orderPayItemId = request.getOrderPayItemId();
        bonusChange = request.getAmount();
        changeType = BonusChangeTypeEnum.DEDUCTION.getValue();
        befSumBonus = wallet.getSumBonus();
        befPendingBonus = wallet.getPendingBonus();
        befAvailableBonus = wallet.getAvailableBonus();
        befUsedBonus = wallet.getUsedBonus();

        aftSumBonus = wallet.getSumBonus();
        aftPendingBonus = wallet.getPendingBonus();
        aftAvailableBonus = wallet.getAvailableBonus() - request.getAmount();
        aftUsedBonus = wallet.getUsedBonus() + request.getAmount();

        copyOperationInfo(request);
    }

    public BonusWalletDetail(DistributeBonusRequest request, BonusWallet wallet) {
        bonusWalletId = wallet.getId();
        bonusChange = request.getAmount();
        changeType = BonusChangeTypeEnum.DISTRIBUTION.getValue();
        befSumBonus = wallet.getSumBonus();
        befPendingBonus = wallet.getPendingBonus();
        befAvailableBonus = wallet.getAvailableBonus();
        befUsedBonus = wallet.getUsedBonus();

        aftSumBonus = wallet.getSumBonus();
        aftPendingBonus = wallet.getPendingBonus() - request.getAmount();
        aftAvailableBonus = wallet.getAvailableBonus() + request.getAmount();
        aftUsedBonus = wallet.getUsedBonus();

        copyOperationInfo(request);
    }

    public String getSrcCustomerName() {
        return srcCustomerName;
    }

    public void setSrcCustomerName(String srcCustomerName) {
        this.srcCustomerName = srcCustomerName;
    }

    public Integer getSrcCustomerMoneyPay() {
        return srcCustomerMoneyPay;
    }

    public void setSrcCustomerMoneyPay(Integer srcCustomerMoneyPay) {
        this.srcCustomerMoneyPay = srcCustomerMoneyPay;
    }

    public Integer getSrcCustomerMoneyAvailable() {
        return srcCustomerMoneyAvailable;
    }

    public void setSrcCustomerMoneyAvailable(Integer srcCustomerMoneyAvailable) {
        this.srcCustomerMoneyAvailable = srcCustomerMoneyAvailable;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getBonusRate() {
        return bonusRate;
    }

    public void setBonusRate(Integer bonusRate) {
        this.bonusRate = bonusRate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getOrderPayItemId() {
        return orderPayItemId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBonusWalletId() {
        return bonusWalletId;
    }

    public void setBonusWalletId(Integer bonusWalletId) {
        this.bonusWalletId = bonusWalletId;
    }

    public Integer getBonusChange() {
        return bonusChange;
    }

    public void setBonusChange(Integer bonusChange) {
        this.bonusChange = bonusChange;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public Integer getBefSumBonus() {
        return befSumBonus;
    }

    public void setBefSumBonus(Integer befSumBonus) {
        this.befSumBonus = befSumBonus;
    }

    public Integer getBefPendingBonus() {
        return befPendingBonus;
    }

    public void setBefPendingBonus(Integer befPendingBonus) {
        this.befPendingBonus = befPendingBonus;
    }

    public Integer getBefAvailableBonus() {
        return befAvailableBonus;
    }

    public void setBefAvailableBonus(Integer befAvailableBonus) {
        this.befAvailableBonus = befAvailableBonus;
    }

    public Integer getBefUsedBonus() {
        return befUsedBonus;
    }

    public void setBefUsedBonus(Integer befUsedBonus) {
        this.befUsedBonus = befUsedBonus;
    }

    public Integer getAftSumBonus() {
        return aftSumBonus;
    }

    public void setAftSumBonus(Integer aftSumBonus) {
        this.aftSumBonus = aftSumBonus;
    }

    public Integer getAftPendingBonus() {
        return aftPendingBonus;
    }

    public void setAftPendingBonus(Integer aftPendingBonus) {
        this.aftPendingBonus = aftPendingBonus;
    }

    public Integer getAftAvailableBonus() {
        return aftAvailableBonus;
    }

    public void setAftAvailableBonus(Integer aftAvailableBonus) {
        this.aftAvailableBonus = aftAvailableBonus;
    }

    public Integer getAftUsedBonus() {
        return aftUsedBonus;
    }

    public void setAftUsedBonus(Integer aftUsedBonus) {
        this.aftUsedBonus = aftUsedBonus;
    }
}
