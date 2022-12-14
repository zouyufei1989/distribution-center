package com.money.custom.entity;

import com.money.custom.entity.enums.GroupReserveFlagEnum;
import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.entity.request.ChangeGroupReserveFlagRequest;
import com.money.custom.entity.request.ModifyGroupDetailImgsRequest;
import com.money.custom.entity.request.ModifyGroupVedioesRequest;
import com.money.framework.base.annotation.IgnoreXss;
import com.money.framework.base.entity.BaseEntity;
import com.money.framework.util.EnumUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Group extends BaseEntity {

    private Integer id;
    private Integer parentId;
    @Length(max = 50, message = "门店名称不可超过50个字符")
    @NotBlank(message = "请输入门店名称")
    private String name;
    @NotBlank(message = "请选择所在城市")
    private String cityCode;
    @Length(max = 255, message = "详细地址不可超过255个字符")
    @NotBlank(message = "请输入详细地址")
    private String address;
    @NotBlank(message = "请输入负责人姓名")
    @Length(max = 10, message = "负责人姓名不可超过10个字符")
    private String ownerName;
    @NotBlank(message = "请输入店铺电话")
    @Length(max = 20, message = "请输入店铺电话不可超过20个字符")
    private String ownerPhone;
    @Length(max = 500, message = "门店描述不可超过500个字符")
    private String desc;
    @NotBlank(message = "请上传缩略图")
    private String thumbnail;
    @NotBlank(message = "请上传详情封面")
    private String detailCoverImg;
    @IgnoreXss
    private String detailImg;
    private String video;
    @NotNull(message = "请输入门店排序")
    private Integer index;
    @NotBlank(message = "请选择营业时间")
    @Length(max = 500, message = "营业时间不可超过500个字符")
    private String openRules;
    @NotNull(message = "门店经纬度不可为空")
    private Double lng;
    @NotNull(message = "门店经纬度不可为空")
    private Double lat;
    private String videoCoverImg;
    @IgnoreXss
    private String videoList;
    private Integer reserveFlag;
    private Integer reserveDays;

    private String cityName;

    public Group() {}

    public Group(ChangeGroupReserveFlagRequest request) {
        setId(request.getGroupId());
        setReserveFlag(request.getReserveFlag());
        copyOperationInfo(request);
    }

    public Group(ModifyGroupDetailImgsRequest request) {
        Assert.notNull(request.getGroupId(), "请指明门店");
        setId(request.getGroupId());
        setDetailImg(request.getDetailCoverImg());
        copyOperationInfo(request);
    }

    public Group(ModifyGroupVedioesRequest request) {
        Assert.notNull(request.getGroupId(), "请指明门店");
        setId(request.getGroupId());
        setVideoList(request.getVideoList());
        copyOperationInfo(request);
    }

    public Integer getReserveDays() {
        return reserveDays;
    }

    public void setReserveDays(Integer reserveDays) {
        this.reserveDays = reserveDays;
    }

    public String getReserveFlagName() {
        return EnumUtils.getNameByValue(GroupReserveFlagEnum.class, reserveFlag);
    }

    public Integer getReserveFlag() {
        return reserveFlag;
    }

    public void setReserveFlag(Integer reserveFlag) {
        this.reserveFlag = reserveFlag;
    }

    public String getVideoCoverImg() {
        return videoCoverImg;
    }

    public void setVideoCoverImg(String videoCoverImg) {
        this.videoCoverImg = videoCoverImg;
    }

    public String getVideoList() {
        return videoList;
    }

    public void setVideoList(String videoList) {
        this.videoList = videoList;
    }

    public String getHistoryType() {
        return HistoryEntityEnum.GROUP.getName();
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getOpenRules4Show() {
        if (StringUtils.isEmpty(openRules)) {
            return "";
        }
        String[] weekdayAndTimespan = openRules.split("@");
        String[] weekdays = weekdayAndTimespan[0].split("-");
        String[] timeSpans = weekdayAndTimespan[1].split("-");
        return String.format("%s 至 %s<br>%s - %s", weekdays[0], weekdays[1], timeSpans[0], timeSpans[1]);
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDetailCoverImg() {
        return detailCoverImg;
    }

    public void setDetailCoverImg(String detailCoverImg) {
        this.detailCoverImg = detailCoverImg;
    }

    public String getDetailImg() {
        return detailImg;
    }

    public void setDetailImg(String detailImg) {
        this.detailImg = detailImg;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getOpenRules() {
        return openRules;
    }

    public void setOpenRules(String openRules) {
        this.openRules = openRules;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


}
