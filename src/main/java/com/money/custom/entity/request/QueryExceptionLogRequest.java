package com.money.custom.entity.request;

import com.money.custom.entity.ExceptionLog;

import java.util.Map;

public class QueryExceptionLogRequest extends QueryGridRequestBase {

    private ExceptionLog exceptionLog = new ExceptionLog();
    private String startDate;
    private String endDate;

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("exceptionLog", exceptionLog);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        return params;
    }

    public void setMethod(String method) {
        this.exceptionLog.setMethod(method);
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
