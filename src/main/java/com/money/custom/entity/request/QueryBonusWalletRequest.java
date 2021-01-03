package com.money.custom.entity.request;

import java.util.Map;

public class QueryBonusWalletRequest extends QueryGridRequestBase {

    private String name;
    private String serialNumber;
    private String phone;
    private Integer search4Distribution;
    private String openId;

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("name", name);
        params.put("serialNumber", serialNumber);
        params.put("phone", phone);
        params.put("search4Distribution", search4Distribution);
        params.put("openId", openId);
        return params;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public void setSearch4Distribution(Integer search4Distribution) {
        this.search4Distribution = search4Distribution;
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
