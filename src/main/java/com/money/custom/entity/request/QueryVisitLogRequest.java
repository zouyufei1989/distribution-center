package com.money.custom.entity.request;


import com.money.custom.entity.VisitLog;

import java.util.Map;

public class QueryVisitLogRequest extends QueryGridRequestBase {

    private VisitLog visitLog = new VisitLog();
    private String startDate;
    private String endDate;

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("visitLog", visitLog);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        return params;
    }

    public void setUserName(String userName) {
        this.visitLog.setUserName(userName);
    }

    public void setType(Integer type) {
        this.visitLog.setType(type);
    }

    public void setModuleName(String moduleName) {
        this.visitLog.setModuleName(moduleName);
    }

    public void setResourceName(String resourceName) {
        this.visitLog.setResourceName(resourceName);
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setUserId(Integer userId) {
        this.visitLog.setUserId(userId);
    }
}
