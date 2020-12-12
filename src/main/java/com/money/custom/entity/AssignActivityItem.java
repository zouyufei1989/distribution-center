package com.money.custom.entity;

import com.money.custom.entity.request.AssignActivityRequest;
import com.money.framework.base.entity.BaseEntity;

public class AssignActivityItem extends BaseEntity {


    private Integer id;
    private Integer assignActivityId;
    private Integer goodsId;
    private Integer customerGroupId;
    private Integer sumCnt;
    private Integer usedCnt;
    private Integer availableCnt;

    public AssignActivityItem() {}

    public AssignActivityItem(AssignActivityRequest request, AssignActivityRequest.AssignItem item) {
        this.goodsId = request.getActivityId();
        this.customerGroupId = item.getCustomerGroupId();
        this.sumCnt = item.getCnt();
        this.usedCnt = 0;
        this.availableCnt = item.getCnt();

        copyOperationInfo(request);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssignActivityId() {
        return assignActivityId;
    }

    public void setAssignActivityId(Integer assignActivityId) {
        this.assignActivityId = assignActivityId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCustomerGroupId() {
        return customerGroupId;
    }

    public void setCustomerGroupId(Integer customerGroupId) {
        this.customerGroupId = customerGroupId;
    }

    public Integer getSumCnt() {
        return sumCnt;
    }

    public void setSumCnt(Integer sumCnt) {
        this.sumCnt = sumCnt;
    }

    public Integer getUsedCnt() {
        return usedCnt;
    }

    public void setUsedCnt(Integer usedCnt) {
        this.usedCnt = usedCnt;
    }

    public Integer getAvailableCnt() {
        return availableCnt;
    }

    public void setAvailableCnt(Integer availableCnt) {
        this.availableCnt = availableCnt;
    }
}
