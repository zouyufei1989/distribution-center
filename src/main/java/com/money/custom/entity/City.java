package com.money.custom.entity;


import com.money.framework.base.entity.OperationalEntity;

public class City extends OperationalEntity {

    private Integer id;
    private Double cityLat;
    private Double cityLng;
    private String cityChName;
    private String cityName;
    private String pinyinShort;
    private String cityCode;
    private String comment;
    private String amapProvinceName;
    private String amapProvinceCode;
    private String amapCityName;
    private String amapCityCode;
    private String amapCityTel;
    private String amapDistrictName;
    private String amapDistrictCode;
    private Integer hot;
    private Integer open;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCityLat() {
        return cityLat;
    }

    public void setCityLat(Double cityLat) {
        this.cityLat = cityLat;
    }

    public Double getCityLng() {
        return cityLng;
    }

    public void setCityLng(Double cityLng) {
        this.cityLng = cityLng;
    }

    public String getCityChName() {
        return cityChName;
    }

    public void setCityChName(String cityChName) {
        this.cityChName = cityChName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPinyinShort() {
        return pinyinShort;
    }

    public void setPinyinShort(String pinyinShort) {
        this.pinyinShort = pinyinShort;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAmapProvinceName() {
        return amapProvinceName;
    }

    public void setAmapProvinceName(String amapProvinceName) {
        this.amapProvinceName = amapProvinceName;
    }

    public String getAmapProvinceCode() {
        return amapProvinceCode;
    }

    public void setAmapProvinceCode(String amapProvinceCode) {
        this.amapProvinceCode = amapProvinceCode;
    }

    public String getAmapCityName() {
        return amapCityName;
    }

    public void setAmapCityName(String amapCityName) {
        this.amapCityName = amapCityName;
    }

    public String getAmapCityCode() {
        return amapCityCode;
    }

    public void setAmapCityCode(String amapCityCode) {
        this.amapCityCode = amapCityCode;
    }

    public String getAmapCityTel() {
        return amapCityTel;
    }

    public void setAmapCityTel(String amapCityTel) {
        this.amapCityTel = amapCityTel;
    }

    public String getAmapDistrictName() {
        return amapDistrictName;
    }

    public void setAmapDistrictName(String amapDistrictName) {
        this.amapDistrictName = amapDistrictName;
    }

    public String getAmapDistrictCode() {
        return amapDistrictCode;
    }

    public void setAmapDistrictCode(String amapDistrictCode) {
        this.amapDistrictCode = amapDistrictCode;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }
}
