package com.money.custom.entity.enums;

public enum SerialNumberEnum {

    BONUS_PLAN("BP", "积分方案"),
    CUSTOMER("CS", "客户"),
    ACTIVITY("AC", "活动");

    private String value;
    private String desc;

    SerialNumberEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
