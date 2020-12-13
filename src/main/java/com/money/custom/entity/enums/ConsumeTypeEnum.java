package com.money.custom.entity.enums;

public enum ConsumeTypeEnum implements IEnumKeyValue {

    MONEY(1, "余额"),
    BOUNS(2, "积分"),
    OFFLINE(3, "非余额/线下");

    private Integer value;
    private String name;

    ConsumeTypeEnum(Integer value, String name) {
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
