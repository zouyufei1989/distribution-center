package com.money.custom.entity.enums;

public enum OrderRefundTypeEnum implements IEnumKeyValue {

    WALLET(1, "返回钱包"),
    OFFFLINE(2, "线下");

    private Integer value;
    private String name;

    OrderRefundTypeEnum(Integer value, String name) {
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
