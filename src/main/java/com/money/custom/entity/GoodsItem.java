package com.money.custom.entity;

import com.money.custom.entity.enums.GoodsShowPriceEnum;
import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.entity.request.MoAGoods4SingleRequest;
import com.money.framework.base.annotation.IgnoreXss;
import com.money.framework.base.entity.BaseEntity;
import com.money.framework.util.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class GoodsItem extends BaseEntity {

    private Integer id;
    private Integer goodsId;
    private String name;
    private Integer profitRate; // ÷1000
    private Integer goodsTagId;
    private Integer price;   // ÷100
    private Integer showPrice;
    private String unit;
    private String thumbnail;
    private String desc;
    @IgnoreXss
    private String detail;
    private Integer cnt;

    public GoodsItem() {}

    public GoodsItem(MoAGoods4SingleRequest request,Integer goodsId) {
        this.goodsId = goodsId;
        this.name = request.getName();
        this.profitRate = request.getProfitRate();
        this.goodsTagId = request.getGoodsTagId();
        this.price = request.getPrice();
        this.showPrice = request.getShowPrice();
        this.unit = request.getUnit();
        this.thumbnail = request.getThumbnail();
        this.detail = request.getDetail();
        this.desc = request.getDesc();
        this.cnt = 1;
        copyOperationInfo(request);
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    private String goodsTagName;

    public String getProfitRate4Show() {
        if (Objects.isNull(this.profitRate)) {
            return StringUtils.EMPTY;
        }
        return String.format("%.2f", this.profitRate / 100.0) + "%";
    }

    public String getPrice4Show() {
        if (Objects.isNull(price)) {
            return StringUtils.EMPTY;
        }
        return String.format("%.2f", this.price / 100.0);
    }

    public String getShowPriceName() {
        return EnumUtils.getNameByValue(GoodsShowPriceEnum.class, this.showPrice);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(Integer profitRate) {
        this.profitRate = profitRate;
    }

    public Integer getGoodsTagId() {
        return goodsTagId;
    }

    public void setGoodsTagId(Integer goodsTagId) {
        this.goodsTagId = goodsTagId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(Integer showPrice) {
        this.showPrice = showPrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getGoodsTagName() {
        return goodsTagName;
    }

    public void setGoodsTagName(String goodsTagName) {
        this.goodsTagName = goodsTagName;
    }
}
