package com.money.custom.entity.enums;

public enum ActivityScopeEnum implements IEnumKeyValue {

    ONLY_NEW(1, "仅新用户"),
    ALL(2, "全部");

    private Integer value;
    private String name;

    ActivityScopeEnum(Integer value, String name) {
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
