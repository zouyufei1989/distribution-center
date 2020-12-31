package com.money.custom.entity;

import com.money.framework.base.entity.BaseEntity;

public class ActivityClaimRecord extends BaseEntity {


    private Integer id;
    private Integer activityAssignId;
    private Integer srcCustomerGroupId;
    private Integer claimCustomerGroupId;
    private String srcOpenId;
    private String claimOpenId;
    private Integer goodsId;
    private String goodsName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityAssignId() {
        return activityAssignId;
    }

    public void setActivityAssignId(Integer activityAssignId) {
        this.activityAssignId = activityAssignId;
    }

    public Integer getSrcCustomerGroupId() {
        return srcCustomerGroupId;
    }

    public void setSrcCustomerGroupId(Integer srcCustomerGroupId) {
        this.srcCustomerGroupId = srcCustomerGroupId;
    }

    public Integer getClaimCustomerGroupId() {
        return claimCustomerGroupId;
    }

    public void setClaimCustomerGroupId(Integer claimCustomerGroupId) {
        this.claimCustomerGroupId = claimCustomerGroupId;
    }

    public String getSrcOpenId() {
        return srcOpenId;
    }

    public void setSrcOpenId(String srcOpenId) {
        this.srcOpenId = srcOpenId;
    }

    public String getClaimOpenId() {
        return claimOpenId;
    }

    public void setClaimOpenId(String claimOpenId) {
        this.claimOpenId = claimOpenId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
