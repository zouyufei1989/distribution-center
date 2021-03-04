package com.money.custom.entity.dto;

import com.money.custom.utils.StringFormatUtils;
import com.money.framework.base.entity.BaseEntity;

import java.util.Objects;

public class ShareHolderStatistics extends BaseEntity {

    private Integer id;
    private String shareHolderName;
    private String phone;
    private Integer allCnt;
    private Integer monthCnt;
    private Integer consumeCnt;
    private Long availableBonus;
    private Double consumeRate;

    public Long getAvailableBonus() {
        return availableBonus;
    }

    public String getAvailableBonus4Show() {
        return StringFormatUtils.moneyFen2Yuan(availableBonus);
    }

    public void setAvailableBonus(Long availableBonus) {
        this.availableBonus = availableBonus;
    }

    public Double getConsumeRate() {
        return consumeRate;
    }

    public void setConsumeRate(Double consumeRate) {
        this.consumeRate = consumeRate;
    }

    public String getConsumeRate4Show() {
        if (Objects.isNull(consumeRate)) {
            return "0%";
        }
        return StringFormatUtils.percent((long) (consumeRate * 10000));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShareHolderName() {
        return shareHolderName;
    }

    public void setShareHolderName(String shareHolderName) {
        this.shareHolderName = shareHolderName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAllCnt() {
        return allCnt;
    }

    public void setAllCnt(Integer allCnt) {
        this.allCnt = allCnt;
    }

    public Integer getMonthCnt() {
        return monthCnt;
    }

    public void setMonthCnt(Integer monthCnt) {
        this.monthCnt = monthCnt;
    }

    public Integer getConsumeCnt() {
        return consumeCnt;
    }

    public void setConsumeCnt(Integer consumeCnt) {
        this.consumeCnt = consumeCnt;
    }
}
