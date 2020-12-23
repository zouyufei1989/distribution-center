package com.money.h5.entity.dto;

import com.money.custom.entity.Group;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "门店列表信息")
public class H5Group {
    @ApiModelProperty(value = "门店id")
    private Integer id;
    @ApiModelProperty(value = "门店名")
    private String name;
    @ApiModelProperty(value = "顺序")
    private Integer index;
    @ApiModelProperty(value = "描述")
    private String desc;
    @ApiModelProperty(value = "缩略图")
    private String thumbnail;

    public H5Group(Group item) {
        id = item.getId();
        name = item.getName();
        index = item.getIndex();
        desc = item.getDesc();
        thumbnail = item.getThumbnail();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getIndex() {
        return index;
    }

    public String getDesc() {
        return desc;
    }

    public String getThumbnail() {
        return thumbnail;
    }

}
