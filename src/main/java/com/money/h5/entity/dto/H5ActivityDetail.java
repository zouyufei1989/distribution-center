package com.money.h5.entity.dto;

import com.money.custom.entity.GoodsItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "活动详情")
public class H5ActivityDetail {

    @ApiModelProperty(value = "项目名称")
    private String name;
    @ApiModelProperty(value = "项目描述")
    private String desc;
    @ApiModelProperty(value = "单价")
    private Long price;
    @ApiModelProperty(value = "单项总价")
    private Long sumPrice;
    @ApiModelProperty(value = "次数")
    private Integer cnt;
    @ApiModelProperty(value = "单位")
    private String unit;
    @ApiModelProperty(value = "缩略图")
    private String thumbnail;

    public H5ActivityDetail(){}

    public H5ActivityDetail(GoodsItem goodsItem){
        this.name = goodsItem.getName();
        this.desc = goodsItem.getDesc();
        this.price = goodsItem.getPrice();
        this.cnt = goodsItem.getCnt();
        this.unit = goodsItem.getUnit();
        this.sumPrice = goodsItem.getPrice() * goodsItem.getCnt();
        this.thumbnail = goodsItem.getThumbnail();
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Long getSumPrice() {
        return sumPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
