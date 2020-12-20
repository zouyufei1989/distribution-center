package com.money.custom.entity.enums;

public enum BonusChangeTypeEnum implements IEnumKeyValue {

    GAIN(1, "获得积分"),
    DEDUCTION(-1, "扣除积分");

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
