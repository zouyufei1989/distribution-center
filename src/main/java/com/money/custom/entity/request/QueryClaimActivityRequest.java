package com.money.custom.entity.request;

import java.util.Map;

public class QueryClaimActivityRequest extends QueryGridRequestBase {

    private Integer activityAssignId;
    private String openId;

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("activityAssignId", activityAssignId);
        params.put("openId", openId);
        return params;
    }

    public void setActivityAssignId(Integer activityAssignId) {
        this.activityAssignId = activityAssignId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
