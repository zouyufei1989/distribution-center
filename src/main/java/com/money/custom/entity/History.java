package com.money.custom.entity;

import com.money.framework.base.annotation.IgnoreXss;
import com.money.framework.base.entity.OperationalEntity;
import com.money.framework.util.DateUtils;

@IgnoreXss
public class History extends OperationalEntity {

    private Integer id;
    private String key;
    private String type;
    private String detail;
    private String updater;

    public History() {}

    public History(String key, String type, String detail, String updater, String createDate) {
        this.key = key;
        this.type = type;
        this.detail = detail;
        this.updater = updater;
        setCreateDate(DateUtils.parse(createDate, "yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public String getUpdater() {
        return updater;
    }

    @Override
    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
