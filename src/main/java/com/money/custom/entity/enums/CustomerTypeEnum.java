package com.money.custom.entity.enums;

public enum CustomerTypeEnum implements IEnumKeyValue {

    NORMAL(1, "普客"),
    SHARE_HOLDER(2, "股东");

    private Integer value;
    private String name;

    CustomerTypeEnum(Integer value, String name) {
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
