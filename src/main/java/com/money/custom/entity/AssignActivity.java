package com.money.custom.entity;

import com.money.custom.entity.enums.CommonStatusEnum;
import com.money.custom.entity.request.AssignActivityRequest;
import com.money.custom.utils.StringFormatUtils;
import com.money.framework.base.entity.BaseEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class AssignActivity extends BaseEntity {


    private Integer id;
    private Integer goodsId;
    private String goodsName;
    private Integer containGoodsCnt;
    private Integer sumPrice;
    private Integer assignCustomerCnt;
    private Integer assignSumCnt;

    public AssignActivity() {}

    public AssignActivity(AssignActivityRequest request) {
        this.goodsId = request.getActivityId();
        this.assignCustomerCnt = request.getItems().size();
        this.assignSumCnt = request.getItems().stream().mapToInt(AssignActivityRequest.AssignItem::getCnt).sum();
        setStatus(CommonStatusEnum.ENABLE.getValue());
        copyOperationInfo(request);
    }

    public String getSumPrice4Show() {
        return StringFormatUtils.moneyFen2Yuan(this.sumPrice);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getContainGoodsCnt() {
        return containGoodsCnt;
    }

    public void setContainGoodsCnt(Integer containGoodsCnt) {
        this.containGoodsCnt = containGoodsCnt;
    }

    public Integer getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Integer sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Integer getAssignCustomerCnt() {
        return assignCustomerCnt;
    }

    public void setAssignCustomerCnt(Integer assignCustomerCnt) {
        this.assignCustomerCnt = assignCustomerCnt;
    }

    public Integer getAssignSumCnt() {
        return assignSumCnt;
    }

    public void setAssignSumCnt(Integer assignSumCnt) {
        this.assignSumCnt = assignSumCnt;
    }
}
