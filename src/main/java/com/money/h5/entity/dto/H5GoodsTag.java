package com.money.h5.entity.dto;

import com.money.custom.entity.GoodsTag;
import com.money.custom.entity.Group;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "商品类别")
public class H5GoodsTag {
    @ApiModelProperty(value = "商品类别id")
    private Integer id;
    @ApiModelProperty(value = "类别名称")
    private String name;

    public H5GoodsTag(GoodsTag item) {
        id = item.getId();
        name = item.getName();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
