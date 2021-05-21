package com.money.custom.entity;


import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.utils.StringFormatUtils;
import com.money.framework.base.entity.BaseEntity;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

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
    @Length(max = 20,message = "积分方案名称不可超过20个字符")
    private String name;
    @NotNull(message = "积分比例不可为空")
    @Max(value = 10000, message = "积分比例最大为100")
    private Long bonusRate;
    private Integer cashbackFirst;
    @Min(value = 0, message = "返现最小为0")
    private Long cashbackAmount;

    @Length(max = 200,message = "积分方案描述不可超过200个字符")
    private String desc;

    private Integer usedCount;

    public String getHistoryType() {
        return HistoryEntityEnum.BONUS_PLAN.getName();
    }


    public String getBonusRate4Show(){
        if (Objects.isNull(this.bonusRate)) {
            return StringUtils.EMPTY;
        }
        return StringFormatUtils.percent(this.bonusRate);
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


    public Integer getCashbackFirst() {
        return cashbackFirst;
    }

    public void setCashbackFirst(Integer cashbackFirst) {
        this.cashbackFirst = cashbackFirst;
    }

    public Long getBonusRate() {
        return bonusRate;
    }

    public void setBonusRate(Long bonusRate) {
        this.bonusRate = bonusRate;
    }

    public Long getCashbackAmount() {
        return cashbackAmount;
    }

    public void setCashbackAmount(Long cashbackAmount) {
        this.cashbackAmount = cashbackAmount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
