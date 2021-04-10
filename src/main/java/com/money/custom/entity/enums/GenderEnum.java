package com.money.custom.entity.enums;

public enum GenderEnum implements IEnumKeyValue {

    SECRET(0, "保密"),
    MALE(1, "男"),
    FEMALE(2, "女");

    private Integer value;
    private String name;

    GenderEnum(Integer value, String name) {
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
