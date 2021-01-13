package com.money.custom.entity;


import com.money.custom.entity.request.BonusRechargeRequest;
import com.money.custom.entity.request.DeductionRequest;
import com.money.custom.entity.request.DistributeBonusRequest;
import com.money.framework.base.entity.OperationalEntity;
import com.money.framework.util.DateUtils;

import java.util.Date;

public class BonusWallet extends OperationalEntity {

    private Integer id;
    private Long sumBonus;
    private Long pendingBonus;
    private Long usedBonus;
    private Long availableBonus;
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
        wallet.setSumBonus(0L);
        wallet.setPendingBonus(0L);
        wallet.setSumBonus(0L);
        wallet.setAvailableBonus(0L);
        wallet.setUsedBonus(0L);
        wallet.copyOperationInfo(operationEntry);
        return wallet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getSumBonus() {
        return sumBonus;
    }

    public void setSumBonus(Long sumBonus) {
        this.sumBonus = sumBonus;
    }

    public Long getPendingBonus() {
        return pendingBonus;
    }

    public void setPendingBonus(Long pendingBonus) {
        this.pendingBonus = pendingBonus;
    }

    public Long getUsedBonus() {
        return usedBonus;
    }

    public void setUsedBonus(Long usedBonus) {
        this.usedBonus = usedBonus;
    }

    public Long getAvailableBonus() {
        return availableBonus;
    }

    public void setAvailableBonus(Long availableBonus) {
        this.availableBonus = availableBonus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
