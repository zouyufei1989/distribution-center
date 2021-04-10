package com.money.custom.entity.enums;

public enum SerialNumberEnum {

    BP("BP", "积分方案"),
    CS("CS", "客户"),
    GS("GS", "单品"),
    GP("GP", "套餐"),
    GA("GA", "活动"),
    EM("EM", "员工");

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
