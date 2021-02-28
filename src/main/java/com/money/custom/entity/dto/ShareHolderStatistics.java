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

    public Long getAvailableBonus() {
        return availableBonus;
    }

    public String getAvailableBonus4Show() {
        return StringFormatUtils.moneyFen2Yuan(availableBonus);
    }

    public void setAvailableBonus(Long availableBonus) {
        this.availableBonus = availableBonus;
    }

    public Long getConsumeRate() {
        if (Objects.isNull(consumeCnt) || Objects.isNull(allCnt)) {
            return 0L;
        }
        return Math.round(consumeCnt * 10000.0 / allCnt);
    }

    public String getConsumeRate4Show() {
        return StringFormatUtils.percent(getConsumeRate());
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
