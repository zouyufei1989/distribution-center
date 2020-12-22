package com.money.custom.entity.request;

import com.money.custom.entity.enums.BonusChangeTypeEnum;

import java.util.Map;

public class QueryBonusDistributionRequest extends QueryGridRequestBase {

    private String name;
    private String serialNumber;
    private String phone;
    private String startDate;
    private String endDate;

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("changeType", BonusChangeTypeEnum.DISTRIBUTION.getValue());
        params.put("name", "name");
        params.put("serialNumber", "serialNumber");
        params.put("phone", "phone");
        params.put("startDate", "startDate");
        params.put("endDate", "endDate");
        return params;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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
