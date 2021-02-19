package com.money.h5.entity.dto;

import com.money.custom.entity.Group;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

@ApiModel(description = "门店详情")
public class H5GroupDetail extends H5Group {
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "联系电话")
    private String phone;
    @ApiModelProperty(value = "描述")
    private String desc;
    @ApiModelProperty(value = "详情封面图")
    private String detailCoverImg;
    @ApiModelProperty(value = "详情图集合 ;分割")
    private String detailImg;
    @ApiModelProperty(value = "介绍视频，废弃，保留该字段兼容老数据接口")
    private String video;
    @ApiModelProperty(value = "介绍视频集合 JSON集合")
    private String videoList;

    @ApiModelProperty(value = "营业时间")
    private String openRules;
    @ApiModelProperty(value = "地理位置 经度")
    private Double lng;
    @ApiModelProperty(value = "地理位置 纬度")
    private Double lat;

    public H5GroupDetail(Group group) {
        super(group);
        address = group.getAddress();
        phone = group.getOwnerPhone();
        desc = group.getDesc();
        detailCoverImg = group.getDetailCoverImg();
        detailImg = group.getDetailImg();
        video = group.getVideo();
        videoList = group.getVideoList();

        openRules = group.getOpenRules();
        if (StringUtils.isNotEmpty(openRules) && openRules.contains("@")) {
            openRules = openRules.replace("@", " ").replaceFirst("-", "至");
        }

        lng = group.getLng();
        lat = group.getLat();
    }

    public String getVideoList() {
        return videoList;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public String getDetailCoverImg() {
        return detailCoverImg;
    }

    public String getDetailImg() {
        return detailImg;
    }

    public String getVideo() {
        return video;
    }

    public String getOpenRules() {
        return openRules;
    }

    public Double getLng() {
        return lng;
    }

    public Double getLat() {
        return lat;
    }
}
