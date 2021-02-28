package com.money.custom.entity.enums;

public enum StatisticsTypeEnum implements IEnumKeyValue {

    BY_GROUP(1, ""),
    BY_DAY(2, ""),
    BY_MONTH(3, "");

    private Integer value;
    private String name;

    StatisticsTypeEnum(Integer value, String name) {
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
