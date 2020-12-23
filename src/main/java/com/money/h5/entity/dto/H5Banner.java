package com.money.h5.entity.dto;

import com.money.custom.entity.Banner;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class H5Banner {
    @ApiModelProperty(value = "排序位置")
    private Integer index;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "点击跳转地址")
    private String href;
    @ApiModelProperty(value = "图片地址")
    private String url;
    @ApiModelProperty(value = "描述")
    private String desc;

    public H5Banner(Banner banner) {
        index = banner.getIndex();
        url = banner.getUrl();
        desc = banner.getDesc();
        href = banner.getHref();
        title = banner.getTitle();
    }

    public Integer getIndex() {
        return index;
    }

    public String getTitle() {
        return title;
    }

    public String getHref() {
        return href;
    }

    public String getUrl() {
        return url;
    }

    public String getDesc() {
        return desc;
    }
}
