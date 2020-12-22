package com.money.custom.entity;


import com.money.custom.entity.request.BonusRechargeRequest;
import com.money.custom.entity.request.DeductionRequest;
import com.money.custom.entity.request.DistributeBonusRequest;
import com.money.framework.base.entity.OperationalEntity;
import com.money.framework.util.DateUtils;

import java.util.Date;

public class BonusWallet extends OperationalEntity {

    private Integer id;
    private Integer sumBonus;
    private Integer pendingBonus;
    private Integer usedBonus;
    private Integer availableBonus;
    private String lastDistributionDate;

    private Customer customer;

    public void recharge(BonusRechargeRequest rechargeRequest) {
        this.sumBonus += rechargeRequest.getAmount();
        this.pendingBonus += rechargeRequest.getAmount();
    }

    public void deduction(DeductionRequest request) {
        this.usedBonus += request.getAmount();
        this.availableBonus -= request.getAmount();
        copyOperationInfo(request);
    }

    public void distribution(DistributeBonusRequest request) {
        this.pendingBonus -= request.getAmount();
        this.availableBonus += request.getAmount();
        this.lastDistributionDate = DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        copyOperationInfo(request);
    }

    public String getLastDistributionDate() {
        return lastDistributionDate;
    }

    public void setLastDistributionDate(String lastDistributionDate) {
        this.lastDistributionDate = lastDistributionDate;
    }

    public static BonusWallet totalNew(OperationalEntity operationEntry) {
        BonusWallet wallet = new BonusWallet();
        wallet.setSumBonus(0);
        wallet.setPendingBonus(0);
        wallet.setSumBonus(0);
        wallet.setAvailableBonus(0);
        wallet.copyOperationInfo(operationEntry);
        return wallet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSumBonus() {
        return sumBonus;
    }

    public void setSumBonus(Integer sumBonus) {
        this.sumBonus = sumBonus;
    }

    public Integer getPendingBonus() {
        return pendingBonus;
    }

    public void setPendingBonus(Integer pendingBonus) {
        this.pendingBonus = pendingBonus;
    }

    public Integer getUsedBonus() {
        return usedBonus;
    }

    public void setUsedBonus(Integer usedBonus) {
        this.usedBonus = usedBonus;
    }

    public Integer getAvailableBonus() {
        return availableBonus;
    }

    public void setAvailableBonus(Integer availableBonus) {
        this.availableBonus = availableBonus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
