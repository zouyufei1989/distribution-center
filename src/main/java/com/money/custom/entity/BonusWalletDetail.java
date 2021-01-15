package com.money.custom.entity;


import com.money.custom.entity.enums.BonusChangeTypeEnum;
import com.money.custom.entity.request.BonusRechargeRequest;
import com.money.custom.entity.request.DeductionRequest;
import com.money.custom.entity.request.DistributeBonusRequest;
import com.money.framework.base.entity.OperationalEntity;

import java.util.UUID;

public class BonusWalletDetail extends OperationalEntity {

    private Integer id;
    private String batchId;
    private Integer bonusWalletId;
    private Long bonusChange;
    private Integer changeType;
    private Long befSumBonus;
    private Long befDistributeBonus;
    private Long befAvailableBonus;
    private Long befUsedBonus;
    private Long aftSumBonus;
    private Long aftDistributeBonus;
    private Long aftAvailableBonus;
    private Long aftUsedBonus;
    private Integer orderPayItemId;
    private Long bonusRate;

    private Customer customer;
    private Long payAmount;

    private Long srcCustomerMoneyPay;
    private Long srcCustomerMoneyAvailable;
    private String srcCustomerName;


    public BonusWalletDetail() {}

    public BonusWalletDetail(BonusRechargeRequest request, BonusWallet wallet) {
        this.batchId = request.getBatchId();
        bonusRate = request.getBonusRate();
        bonusWalletId = wallet.getId();
        orderPayItemId = request.getOrderPayItemId();
        bonusChange = request.getAmount();
        changeType = request.getChangeTypeEnum().getValue();
        befSumBonus = wallet.getSumBonus();
        befDistributeBonus = wallet.getDistributeBonus();
        befAvailableBonus = wallet.getAvailableBonus();
        befUsedBonus = wallet.getUsedBonus();

        aftSumBonus = wallet.getSumBonus() + bonusChange;
        aftDistributeBonus = wallet.getDistributeBonus();
        aftAvailableBonus = wallet.getAvailableBonus() + bonusChange;
        aftUsedBonus = wallet.getUsedBonus();

        srcCustomerMoneyPay = request.getSrcCustomerMoneyPay();
        srcCustomerMoneyAvailable = request.getSrcCustomerMoneyAvailable() - request.getSrcCustomerMoneyPay();
        if (srcCustomerMoneyAvailable < 0L) {
            getLogger().warn("srcCustomerMoneyAvailable {} <0, reset to 0", srcCustomerMoneyAvailable);
            srcCustomerMoneyAvailable = 0L;
        }

        copyOperationInfo(request);
    }

    public BonusWalletDetail(DeductionRequest request, BonusWallet wallet) {
        batchId = UUID.randomUUID().toString();
        bonusWalletId = wallet.getId();
        orderPayItemId = request.getOrderPayItemId();
        bonusChange = request.getAmount();
        changeType = BonusChangeTypeEnum.DEDUCTION.getValue();
        befSumBonus = wallet.getSumBonus();
        befDistributeBonus = wallet.getDistributeBonus();
        befAvailableBonus = wallet.getAvailableBonus();
        befUsedBonus = wallet.getUsedBonus();

        aftSumBonus = wallet.getSumBonus();
        aftDistributeBonus = wallet.getDistributeBonus();
        aftAvailableBonus = wallet.getAvailableBonus() - request.getAmount();
        aftUsedBonus = wallet.getUsedBonus() + request.getAmount();

        copyOperationInfo(request);
    }

    public BonusWalletDetail(DistributeBonusRequest request, BonusWallet wallet) {
        batchId = UUID.randomUUID().toString();
        bonusWalletId = wallet.getId();
        bonusChange = request.getAmount();
        changeType = BonusChangeTypeEnum.DISTRIBUTION.getValue();
        befSumBonus = wallet.getSumBonus();
        befDistributeBonus = wallet.getDistributeBonus();
        befAvailableBonus = wallet.getAvailableBonus();
        befUsedBonus = wallet.getUsedBonus();

        aftSumBonus = wallet.getSumBonus();
        aftDistributeBonus = wallet.getDistributeBonus() + request.getAmount();
        aftAvailableBonus = wallet.getAvailableBonus() - request.getAmount();
        aftUsedBonus = wallet.getUsedBonus();

        copyOperationInfo(request);
    }

    public String getBatchId() {
        return batchId;
    }

    public String getSrcCustomerName() {
        return srcCustomerName;
    }

    public void setSrcCustomerName(String srcCustomerName) {
        this.srcCustomerName = srcCustomerName;
    }

    public Long getSrcCustomerMoneyPay() {
        return srcCustomerMoneyPay;
    }

    public void setSrcCustomerMoneyPay(Long srcCustomerMoneyPay) {
        this.srcCustomerMoneyPay = srcCustomerMoneyPay;
    }

    public Long getSrcCustomerMoneyAvailable() {
        return srcCustomerMoneyAvailable;
    }

    public void setSrcCustomerMoneyAvailable(Long srcCustomerMoneyAvailable) {
        this.srcCustomerMoneyAvailable = srcCustomerMoneyAvailable;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public Long getBonusRate() {
        return bonusRate;
    }

    public void setBonusRate(Long bonusRate) {
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

    public Long getBonusChange() {
        return bonusChange;
    }

    public void setBonusChange(Long bonusChange) {
        this.bonusChange = bonusChange;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public Long getBefSumBonus() {
        return befSumBonus;
    }

    public void setBefSumBonus(Long befSumBonus) {
        this.befSumBonus = befSumBonus;
    }

    public Long getBefDistributeBonus() {
        return befDistributeBonus;
    }

    public void setBefDistributeBonus(Long befDistributeBonus) {
        this.befDistributeBonus = befDistributeBonus;
    }

    public Long getAftDistributeBonus() {
        return aftDistributeBonus;
    }

    public void setAftDistributeBonus(Long aftDistributeBonus) {
        this.aftDistributeBonus = aftDistributeBonus;
    }

    public Long getBefAvailableBonus() {
        return befAvailableBonus;
    }

    public void setBefAvailableBonus(Long befAvailableBonus) {
        this.befAvailableBonus = befAvailableBonus;
    }

    public Long getBefUsedBonus() {
        return befUsedBonus;
    }

    public void setBefUsedBonus(Long befUsedBonus) {
        this.befUsedBonus = befUsedBonus;
    }

    public Long getAftSumBonus() {
        return aftSumBonus;
    }

    public void setAftSumBonus(Long aftSumBonus) {
        this.aftSumBonus = aftSumBonus;
    }

    public Long getAftAvailableBonus() {
        return aftAvailableBonus;
    }

    public void setAftAvailableBonus(Long aftAvailableBonus) {
        this.aftAvailableBonus = aftAvailableBonus;
    }

    public Long getAftUsedBonus() {
        return aftUsedBonus;
    }

    public void setAftUsedBonus(Long aftUsedBonus) {
        this.aftUsedBonus = aftUsedBonus;
    }
}
