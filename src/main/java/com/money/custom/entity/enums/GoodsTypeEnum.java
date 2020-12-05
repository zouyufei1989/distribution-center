package com.money.custom.entity.enums;

public enum GoodsTypeEnum implements IEnumKeyValue {

    SINGLE(1, "单品"),
    PACKAGE(2, "套餐"),
    ACTIVITY(3, "活动");

    private Integer value;
    private String name;

    GoodsTypeEnum(Integer value, String name) {
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
