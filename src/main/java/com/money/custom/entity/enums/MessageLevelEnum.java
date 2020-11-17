package com.money.custom.entity.enums;

public enum MessageLevelEnum {

    HIGH(1),
    MEDIUM(5),
    LOW(10);

    private int value;

    public int getValue() {
        return value;
    }

    MessageLevelEnum(int value) {
        this.value = value;
    }

}
