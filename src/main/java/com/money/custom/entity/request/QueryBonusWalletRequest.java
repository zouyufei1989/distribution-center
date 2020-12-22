package com.money.custom.entity.request;

import java.util.Map;

public class QueryBonusWalletRequest extends QueryGridRequestBase {

    private String name;
    private String serialNumber;
    private String phone;

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("name", name);
        params.put("serialNumber", serialNumber);
        params.put("phone", phone);
        return params;
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
