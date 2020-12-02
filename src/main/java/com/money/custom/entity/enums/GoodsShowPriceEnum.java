package com.money.custom.entity.enums;

public enum GoodsShowPriceEnum implements IEnumKeyValue {

    HIDE(0, "隐藏"),
    SHOW(1, "展示");

    private Integer value;
    private String name;

    GoodsShowPriceEnum(Integer value, String name) {
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
