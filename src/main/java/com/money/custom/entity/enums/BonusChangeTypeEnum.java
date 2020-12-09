package com.money.custom.entity.enums;

public enum BonusChangeTypeEnum implements IEnumKeyValue {

    GRANT(1, "下发积分"),
    CONSUME(2, "消费积分");

    private Integer value;
    private String name;

    BonusChangeTypeEnum(Integer value, String name) {
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
