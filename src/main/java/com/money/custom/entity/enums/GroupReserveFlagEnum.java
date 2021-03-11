package com.money.custom.entity.enums;

public enum GroupReserveFlagEnum implements IEnumKeyValue {

    NO(0, "不可预约"),
    YES(1, "可预约");

    private Integer value;
    private String name;

    GroupReserveFlagEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getName() {
        return name;
    }

}
