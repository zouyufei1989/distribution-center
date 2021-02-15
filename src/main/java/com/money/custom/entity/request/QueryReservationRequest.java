package com.money.custom.entity.request;

import java.util.Map;

public class QueryReservationRequest extends QueryGridRequestBase {

    private String openId;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private Integer status;

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("openId", openId);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("status", status);
        return params;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
