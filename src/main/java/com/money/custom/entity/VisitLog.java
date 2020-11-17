package com.money.custom.entity;


import com.money.framework.base.entity.OperationalEntity;
import com.money.framework.base.entity.VisitLogTypeEnum;
import com.money.framework.util.EnumUtils;

public class VisitLog extends OperationalEntity {

    private Integer id;
    private String url;
    private String moduleName;
    private String resourceName;
    private Integer type;
    private Integer userId;
    private String userName;
    private String userIp;
    private Integer duration;

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getUserName() {
        return userName;
    }

    public String getTypeName() {
        return EnumUtils.getNameByValue(VisitLogTypeEnum.class, this.type);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
