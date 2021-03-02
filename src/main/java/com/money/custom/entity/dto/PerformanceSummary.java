package com.money.custom.entity.dto;

import com.money.custom.utils.StringFormatUtils;
import com.money.framework.base.entity.OperationalEntity;

import java.util.Objects;

public class PerformanceSummary extends OperationalEntity {

    private Integer allCnt;
    private Integer monthCnt;
    private Integer consumedCnt;

    public String getConsumeRate() {
        if (Objects.isNull(allCnt) || allCnt == 0) {
            return "0%";
        }
        return StringFormatUtils.percent((long) (consumedCnt * 10000.0 / allCnt));
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

    public Integer getConsumedCnt() {
        return consumedCnt;
    }

    public void setConsumedCnt(Integer consumedCnt) {
        this.consumedCnt = consumedCnt;
    }
}
