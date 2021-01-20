package com.money.h5.entity.dto;

import com.money.custom.entity.OrderPay;
import com.money.framework.util.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@ApiModel(description = "按月聚合支付信息")
public class PayInfoMonth {

    @ApiModelProperty(value = "月份")
    private String month;
    @ApiModelProperty(value = "支付数量")
    private Long payCnt;
    @ApiModelProperty(value = "实际支付总额")
    private Long sumActuallyMoney;
    @ApiModelProperty(value = "总价值")
    private Long sumMoney;

    public PayInfoMonth(String month, List<OrderPay> orderPays) {
        this.month = month;
        this.payCnt = 0L;
        this.sumActuallyMoney = 0L;
        this.sumMoney = 0L;

        orderPays.stream().filter(p -> StringUtils.equals(DateUtils.format(p.getCreateDate(), "yyyy-MM"), month)).forEach(
                p -> {
                    payCnt++;
                    sumActuallyMoney += p.getActuallyMoney();
                    sumMoney += p.getSumMoney();
                }
        );
    }

    public Long getSumActuallyMoney() {
        return sumActuallyMoney;
    }

    public void setSumActuallyMoney(Long sumActuallyMoney) {
        this.sumActuallyMoney = sumActuallyMoney;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Long getPayCnt() {
        return payCnt;
    }

    public void setPayCnt(Long payCnt) {
        this.payCnt = payCnt;
    }

    public Long getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Long sumMoney) {
        this.sumMoney = sumMoney;
    }
}
