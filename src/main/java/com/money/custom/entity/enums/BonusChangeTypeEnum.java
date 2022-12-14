package com.money.custom.entity.enums;

public enum BonusChangeTypeEnum implements IEnumKeyValue {

    GAIN(1, "获得积分"),
    DISTRIBUTION(2, "下发积分"),
    BONUSBACK(3, "首次消费返积分"),
    DEDUCTION(-1, "消费积分"),
    DEDUCTION_BY_REFUND(-2,"扣除积分");


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
