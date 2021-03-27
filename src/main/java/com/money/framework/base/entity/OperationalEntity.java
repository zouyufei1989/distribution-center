package com.money.framework.base.entity;

import com.money.custom.entity.User;
import com.money.h5.entity.H5RequestBase;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        if (StringUtils.isEmpty(this.creator)) {
            this.creator = name;
        }
        this.updater = name;

        this.createIp = ip;
        this.updateIp = ip;

        this.createDate = new Date();
        this.updateDate = new Date();

        if (Objects.nonNull(groupId) && Objects.isNull(this.groupId)) {
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

        if (Objects.isNull(this.groupId)) {
            setGroupId(src.getGroupId());
        }
    }

    public void ofH5(H5RequestBase requestBase) {
        this.creator = requestBase.getOpenId();
        this.updater = requestBase.getOpenId();

        this.createIp = "phone";
        this.updateIp = "phone";

        this.createDate = new Date();
        this.updateDate = new Date();
    }

    public void ofTask(Object task) {
        this.creator = task.getClass().getSimpleName();
        this.updater = task.getClass().getSimpleName();

        this.createIp = "server";
        this.updateIp = "server";

        this.createDate = new Date();
        this.updateDate = new Date();
    }

    public void ofH5(String openId) {
        this.creator = openId;
        this.updater = openId;

        this.createIp = "phone";
        this.updateIp = "phone";

        this.createDate = new Date();
        this.updateDate = new Date();

    }

    public void copyOperationInfo(H5RequestBase h5RequestBase) {
        this.creator = h5RequestBase.getOpenId();
        this.updater = h5RequestBase.getOpenId();

        this.createIp = "0.0.0.0";
        this.updateIp = "0.0.0.0";

        this.createDate = new Date();
        this.updateDate = new Date();
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
