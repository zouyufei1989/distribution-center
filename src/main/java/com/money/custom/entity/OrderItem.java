package com.money.custom.entity;

import com.money.custom.entity.request.AddOrderRequest;
import com.money.framework.base.entity.BaseEntity;

public class OrderItem extends BaseEntity {

    private Integer id;
    private Integer orderId;

    private Integer goodsId;
    private Integer goodsItemId;
    private String goodsName;
    private Long goodsProfitRate;
    private Integer goodsTagId;
    private String goodsTagName;
    private Long goodsPrice;
    private Integer goodsShowPrice;
    private String goodsUnit;
    private String goodsThumbnail;
    private String goodsDesc;
    private String goodsDetail;
    private Integer cnt;
    private Integer cntUsed;

    private Integer status;

    public OrderItem() {}

    public OrderItem(GoodsItem goodsItem, Integer orderId, AddOrderRequest request) {
        this.orderId = orderId;
        goodsId = goodsItem.getGoodsId();
        goodsItemId = goodsItem.getId();
        goodsName = goodsItem.getName();
        goodsProfitRate = goodsItem.getProfitRate();
        goodsTagId = goodsItem.getGoodsTagId();
        goodsTagName = goodsItem.getGoodsTagName();
        goodsPrice = goodsItem.getPrice();
        goodsShowPrice = goodsItem.getShowPrice();
        goodsUnit = goodsItem.getUnit();
        goodsThumbnail = goodsItem.getThumbnail();
        goodsDesc = goodsItem.getDesc();
        cnt = goodsItem.getCnt() * request.getCnt();
        cntUsed = 0;

        copyOperationInfo(request);
    }

    public Integer calProfit() {
        long price = this.goodsPrice;
        long rate = this.goodsProfitRate;
        long profit = price * rate * cnt / 10000;
        return (int) profit;
    }

    public Integer getCntUsed() {
        return cntUsed;
    }

    public void setCntUsed(Integer cntUsed) {
        this.cntUsed = cntUsed;
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

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsItemId() {
        return goodsItemId;
    }

    public void setGoodsItemId(Integer goodsItemId) {
        this.goodsItemId = goodsItemId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Long getGoodsProfitRate() {
        return goodsProfitRate;
    }

    public void setGoodsProfitRate(Long goodsProfitRate) {
        this.goodsProfitRate = goodsProfitRate;
    }

    public Integer getGoodsTagId() {
        return goodsTagId;
    }

    public void setGoodsTagId(Integer goodsTagId) {
        this.goodsTagId = goodsTagId;
    }

    public String getGoodsTagName() {
        return goodsTagName;
    }

    public void setGoodsTagName(String goodsTagName) {
        this.goodsTagName = goodsTagName;
    }

    public Long getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Long goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsShowPrice() {
        return goodsShowPrice;
    }

    public void setGoodsShowPrice(Integer goodsShowPrice) {
        this.goodsShowPrice = goodsShowPrice;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public String getGoodsThumbnail() {
        return goodsThumbnail;
    }

    public void setGoodsThumbnail(String goodsThumbnail) {
        this.goodsThumbnail = goodsThumbnail;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public void setStatus(Integer status) {
        this.status = status;
    }
}
