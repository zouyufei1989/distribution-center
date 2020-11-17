package com.money.custom.entity;

import com.money.custom.entity.enums.CommonStatusEnum;
import com.money.framework.base.annotation.IgnoreXss;
import com.money.framework.base.entity.BaseEntity;
import com.money.framework.util.EnumUtils;

public class Group extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 8726473870936673008L;

    private Integer id;
    private String name;
    private Integer status;
    private String address;
    private String phone;
    private String cityCode;
    private String ownerName;
    private String ownerPhone;
    @IgnoreXss
    private String announcementUrl;
    private Double companyLat;
    private Double companyLng;

    private String cityName;

    public Double getCompanyLat() {
        return companyLat;
    }

    public void setCompanyLat(Double companyLat) {
        this.companyLat = companyLat;
    }

    public Double getCompanyLng() {
        return companyLng;
    }

    public void setCompanyLng(Double companyLng) {
        this.companyLng = companyLng;
    }

    public String getAnnouncementUrl() {
        return announcementUrl;
    }

    public void setAnnouncementUrl(String announcementUrl) {
        this.announcementUrl = announcementUrl;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getStatusName() {
        return EnumUtils.getNameByValue(CommonStatusEnum.class, status);
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
