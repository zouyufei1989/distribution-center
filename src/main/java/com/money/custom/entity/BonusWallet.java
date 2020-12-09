package com.money.custom.entity;


import com.money.framework.base.entity.OperationalEntity;

public class BonusWallet extends OperationalEntity {

    private Integer id;
    private Integer sumBonus;
    private Integer pendingBonus;
    private Integer usedBonus;
    private Integer availableBonus;


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
}
