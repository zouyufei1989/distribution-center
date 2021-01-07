package com.money.custom.entity.enums;

public enum SmsTypeEnum implements IEnumKeyValue {

    VERIFY_CODE(1, "" ),
    BONUS_NOTIFY(2, "");

    private Integer value;
    private String name;

    SmsTypeEnum(Integer value, String name) {
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
