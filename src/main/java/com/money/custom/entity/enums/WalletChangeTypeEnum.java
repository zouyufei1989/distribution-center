package com.money.custom.entity.enums;

public enum WalletChangeTypeEnum implements IEnumKeyValue {

    RECHARGE(1, "充值"),
    CONSUME(-1, "消费");

    private Integer value;
    private String name;

    WalletChangeTypeEnum(Integer value, String name) {
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
