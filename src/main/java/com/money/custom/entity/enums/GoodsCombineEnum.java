package com.money.custom.entity.enums;

public enum GoodsCombineEnum implements IEnumKeyValue {

    SINGLE(0, "单品消费"),
    COMBINE(1, "组合消费");

    private Integer value;
    private String name;

    GoodsCombineEnum(Integer value, String name) {
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
