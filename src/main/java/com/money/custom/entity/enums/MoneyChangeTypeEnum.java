package com.money.custom.entity.enums;

public enum MoneyChangeTypeEnum implements IEnumKeyValue {

    CHARGE(1, "充值"),
    CONSUME(2, "消费");

    private Integer value;
    private String name;

    MoneyChangeTypeEnum(Integer value, String name) {
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
