package com.money.custom.entity;

import com.money.custom.entity.request.ConsumeRequest;
import com.money.framework.base.entity.BaseEntity;
import org.springframework.util.Assert;

public class OrderItemConsumption extends BaseEntity {

    private Integer id;
    private Integer orderId;
    private Integer orderItemId;
    private Integer orderConsumptionId;
    private Integer befSumCnt;
    private Integer befUsedCnt;
    private Integer befAvailableCnt;
    private Integer cnt;
    private Integer aftSumCnt;
    private Integer aftUsedCnt;
    private Integer aftAvailableCnt;

    private String goodsName;
    private Integer goodsPrice;
    private String goodsUnit;

    public OrderItemConsumption() {}

    public OrderItemConsumption(OrderConsumption consumption, OrderItem orderItem, ConsumeRequest request) {
        orderId = consumption.getOrderId();
        orderItemId = orderItem.getId();
        orderConsumptionId = consumption.getId();
        befSumCnt = orderItem.getCnt();
        befUsedCnt = orderItem.getCntUsed();
        befAvailableCnt = befSumCnt - befUsedCnt;
        cnt = request.getCnt();
        aftSumCnt = befSumCnt;
        aftUsedCnt = befUsedCnt + cnt;
        aftAvailableCnt = befAvailableCnt - cnt;
        Assert.isTrue(aftAvailableCnt >= 0, "次数不足");

        copyOperationInfo(request);
    }

    public Integer getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Integer goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getOrderConsumptionId() {
        return orderConsumptionId;
    }

    public void setOrderConsumptionId(Integer orderConsumptionId) {
        this.orderConsumptionId = orderConsumptionId;
    }

    public Integer getBefSumCnt() {
        return befSumCnt;
    }

    public void setBefSumCnt(Integer befSumCnt) {
        this.befSumCnt = befSumCnt;
    }

    public Integer getBefUsedCnt() {
        return befUsedCnt;
    }

    public void setBefUsedCnt(Integer befUsedCnt) {
        this.befUsedCnt = befUsedCnt;
    }

    public Integer getBefAvailableCnt() {
        return befAvailableCnt;
    }

    public void setBefAvailableCnt(Integer befAvailableCnt) {
        this.befAvailableCnt = befAvailableCnt;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public Integer getAftSumCnt() {
        return aftSumCnt;
    }

    public void setAftSumCnt(Integer aftSumCnt) {
        this.aftSumCnt = aftSumCnt;
    }

    public Integer getAftUsedCnt() {
        return aftUsedCnt;
    }

    public void setAftUsedCnt(Integer aftUsedCnt) {
        this.aftUsedCnt = aftUsedCnt;
    }

    public Integer getAftAvailableCnt() {
        return aftAvailableCnt;
    }

    public void setAftAvailableCnt(Integer aftAvailableCnt) {
        this.aftAvailableCnt = aftAvailableCnt;
    }
}
