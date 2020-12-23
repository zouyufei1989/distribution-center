package com.money.custom.entity.enums;

public enum CashBackTypeEnum implements IEnumKeyValue {

    NONE(0, "无"),
    CASHBACK(1, "首次消费返现");

    private Integer value;
    private String name;

    CashBackTypeEnum(Integer value, String name) {
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
