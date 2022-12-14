package com.money.custom.entity.enums;

public enum CommonStatusEnum implements IEnumKeyValue {

    ENABLE(1, "启用"),
    DISABLE(0, "禁用"),
    DELETED(-2, "删除");

    private Integer value;
    private String name;

    CommonStatusEnum(Integer value, String name) {
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

    @Override
    public boolean availableForMap() {
        return this != CommonStatusEnum.DELETED;
    }

}
