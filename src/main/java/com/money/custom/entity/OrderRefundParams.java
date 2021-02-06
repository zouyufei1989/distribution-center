package com.money.custom.entity;

import com.money.framework.base.entity.BaseEntity;

public class OrderRefundParams extends BaseEntity {


    private Integer customerGroupId;
    private Integer parentCustomerGroupId;
    private String parentCustomerName;
    private Long bonusGenerated;
    private Long availableBonus;

    private Long orderActualMoney;
    private Integer orderStatus;

    public Integer getCustomerGroupId() {
        return customerGroupId;
    }

    public void setCustomerGroupId(Integer customerGroupId) {
        this.customerGroupId = customerGroupId;
    }

    public Long getAvailableBonus() {
        return availableBonus;
    }

    public void setAvailableBonus(Long availableBonus) {
        this.availableBonus = availableBonus;
    }

    public Integer getParentCustomerGroupId() {
        return parentCustomerGroupId;
    }

    public void setParentCustomerGroupId(Integer parentCustomerGroupId) {
        this.parentCustomerGroupId = parentCustomerGroupId;
    }

    public String getParentCustomerName() {
        return parentCustomerName;
    }

    public void setParentCustomerName(String parentCustomerName) {
        this.parentCustomerName = parentCustomerName;
    }

    public Long getBonusGenerated() {
        return bonusGenerated;
    }

    public void setBonusGenerated(Long bonusGenerated) {
        this.bonusGenerated = bonusGenerated;
    }

    public Long getOrderActualMoney() {
        return orderActualMoney;
    }

    public void setOrderActualMoney(Long orderActualMoney) {
        this.orderActualMoney = orderActualMoney;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
}
