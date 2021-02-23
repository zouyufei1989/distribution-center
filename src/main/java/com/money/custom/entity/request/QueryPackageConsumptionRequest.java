package com.money.custom.entity.request;

import java.util.Map;

public class QueryPackageConsumptionRequest extends QueryGridRequestBase {

    private String customerName;
    private String phone;
    private String startDate;
    private String endDate;

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("customerName", customerName);
        params.put("phone", phone);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        return params;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
