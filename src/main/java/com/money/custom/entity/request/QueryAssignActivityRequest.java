package com.money.custom.entity.request;

import com.money.custom.entity.Customer;

import java.util.Map;

public class QueryAssignActivityRequest extends QueryGridRequestBase {

    private String startDate;
    private String endDate;
    private String creator;
    private String name;


    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        params.put("creator", creator);
        params.put("name", name);
        return params;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setName(String name) {
        this.name = name;
    }
}
