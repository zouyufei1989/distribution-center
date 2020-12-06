package com.money.custom.entity;


import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.framework.base.entity.BaseEntity;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class BonusPlan extends BaseEntity {

    private Integer id;
    @NotBlank(message = "积分方案编号不可为空")
    private String serialNumber;
    @NotBlank(message = "积分方案名称不可为空")
    private String name;
    @NotNull(message = "积分比例不可为空")
    @Max(value = 10000, message = "积分比例最大为100")
    private Integer bonusRate;
    private Integer cashbackFirst;
    @Min(value = 0, message = "返现最小为0")
    private Integer cashbackAmount;
    private String desc;

    private Integer usedCount;

    public String getHistoryType() {
        return HistoryEntityEnum.BONUS_PLAN.getName();
    }


    public String getBonusRate4Show(){
        if (Objects.isNull(this.bonusRate)) {
            return StringUtils.EMPTY;
        }
        return String.format("%.2f", this.bonusRate / 100.0) + "%";
    }

    public Integer getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBonusRate() {
        return bonusRate;
    }

    public void setBonusRate(Integer bonusRate) {
        this.bonusRate = bonusRate;
    }

    public Integer getCashbackFirst() {
        return cashbackFirst;
    }

    public void setCashbackFirst(Integer cashbackFirst) {
        this.cashbackFirst = cashbackFirst;
    }

    public Integer getCashbackAmount() {
        return cashbackAmount;
    }

    public void setCashbackAmount(Integer cashbackAmount) {
        this.cashbackAmount = cashbackAmount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
