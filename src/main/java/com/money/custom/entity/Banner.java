package com.money.custom.entity;

import com.money.custom.entity.enums.CommonStatusEnum;
import com.money.framework.base.entity.BaseEntity;
import com.money.framework.util.EnumUtils;

public class Banner extends BaseEntity {

    private Integer id;
    private String title;
    private String href;
    private String url;
    private String desc;
    private Integer index;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName(){
        return EnumUtils.getNameByValue(CommonStatusEnum.class,this.status);
    }
}
