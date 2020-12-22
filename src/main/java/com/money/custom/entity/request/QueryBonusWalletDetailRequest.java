package com.money.custom.entity.request;

import java.util.Map;

public class QueryBonusWalletDetailRequest extends QueryGridRequestBase {

    private String name;
    private String serialNumber;
    private String phone;
    private String startDate;
    private String endDate;
    private Integer changeType;

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("name", name);
        params.put("serialNumber", serialNumber);
        params.put("phone", phone);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        params.put("changeType", changeType);
        return params;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
