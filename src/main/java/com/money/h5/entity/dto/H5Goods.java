package com.money.h5.entity.dto;

import com.money.custom.entity.Goods;
import com.money.custom.entity.GoodsTag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "商品类别")
public class H5Goods {
    @ApiModelProperty(value = "商品id")
    private Integer id;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "价格-分")
    private Integer price;
    @ApiModelProperty(value = "缩略图")
    private String thumbnail;
    @ApiModelProperty(value = "描述")
    private String desc;

    public H5Goods(Goods item) {
        id = item.getId();
        name = item.getName();
        price = item.getItems().get(0).getPrice();
        thumbnail = item.getItems().get(0).getThumbnail();
        desc = item.getDesc();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getDesc() {
        return desc;
    }
}
