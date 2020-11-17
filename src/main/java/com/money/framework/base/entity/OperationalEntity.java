package com.money.framework.base.entity;

import com.money.custom.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OperationalEntity implements Serializable {

    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    private Date createDate;
    private String createIp;
    private String creator;

    private Date updateDate;
    private String updateIp;
    private String updater;

    private Integer groupId;
    private String groupName;

    private Integer parentGroupId;
    private User loginUser;

    public Map<String, Object> buildParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("creator", creator);
        params.put("updater", updater);

        params.put("groupId", groupId);

        params.put("createIp", createIp);
        params.put("updateIp", updateIp);

        params.put("createDate", createDate);
        params.put("updateDate", updateDate);

        return params;
    }

    public Integer getParentGroupId() {
        return parentGroupId;
    }

    public void setParentGroupId(Integer parentGroupId) {
        this.parentGroupId = parentGroupId;
    }

    public void setOperationInfo(String name, String ip, Integer groupId) {
        this.creator = name;
        this.updater = name;

        this.createIp = ip;
        this.updateIp = ip;

        this.createDate = new Date();
        this.updateDate = new Date();

        if (groupId != null && this.groupId == null) {
            setGroupId(groupId);
        }
    }

    public void copyOperationInfo(OperationalEntity src) {
        this.creator = src.getCreator();
        this.updater = src.getUpdater();

        this.createIp = src.getCreateIp();
        this.updateIp = src.getUpdateIp();

        this.createDate = src.getCreateDate();
        this.updateDate = src.getUpdateDate();

        setGroupId(src.getGroupId());
    }

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateIp() {
        return updateIp;
    }

    public void setUpdateIp(String updateIp) {
        this.updateIp = updateIp;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}