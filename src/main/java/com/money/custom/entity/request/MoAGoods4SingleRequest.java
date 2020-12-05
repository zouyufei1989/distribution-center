package com.money.custom.entity.request;

import com.money.framework.base.annotation.IgnoreXss;
import com.money.framework.base.entity.OperationalEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MoAGoods4SingleRequest extends OperationalEntity {

    private Integer id;
    @Length(max = 20, message = "商品名称不可超过20个字符")
    @NotBlank(message = "请输入商品名称")
    private String name;
    @Min(value = 0, message = "毛利率需大于0")
    @NotNull(message = "请输入毛利率")
    private Integer profitRate; // ÷1000
    @NotNull(message = "请选择商品标签")
    private Integer goodsTagId;
    @Min(value = 0, message = "单价需大于0")
    @NotNull(message = "请输入单价")
    private Integer price;   // ÷100
    @NotNull(message = "请选择是否展示价格")
    private Integer showPrice;
    @Length(max = 20, message = "单位不可超过20个字符")
    @NotBlank(message = "请输入单位")
    private String unit;
    private String thumbnail;
    @Length(max = 500, message = "描述不可超过500个字符")
    private String desc;
    @IgnoreXss
    private String detail;
    @NotNull(message = "请选择状态")
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
