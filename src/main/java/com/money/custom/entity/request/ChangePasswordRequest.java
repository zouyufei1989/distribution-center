package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;

import java.util.Map;

public class ChangePasswordRequest extends OperationalEntity {

    private String currentPwd;
    private String newPwd;
    private Integer userId;

    public String getCurrentPwd() {
        return currentPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setCurrentPwd(String currentPwd) {
        this.currentPwd = currentPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("currentPwd", currentPwd);
        params.put("newPwd", newPwd);
        params.put("userId", userId);
        return params;
    }
}
