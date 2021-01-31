package com.money.custom.entity.enums;

public enum FirstCashBackTypeEnum implements IEnumKeyValue {

    NONE(0, "无返现"),
    CASHBACK(1, "返现");

    private Integer value;
    private String name;

    FirstCashBackTypeEnum(Integer value, String name) {
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

    public boolean pay(Integer type) {
        return (type & this.getValue()) == this.getValue();
    }

    public boolean payOnly(Integer type) {
        return type.equals(this.getValue());
    }


}
