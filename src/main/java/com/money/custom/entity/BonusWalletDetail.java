package com.money.custom.entity;


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
