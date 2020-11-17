package com.money.custom.entity.enums;

public enum MessagePushedEnum {

    WAITING(0),
    PUSHED(1),
    READ(2),
    FAILED(-1);

    private int value;

    public int getValue() {
        return value;
    }

    MessagePushedEnum(int value) {
        this.value = value;
    }

}
