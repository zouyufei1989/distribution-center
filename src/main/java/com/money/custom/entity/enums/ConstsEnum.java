package com.money.custom.entity.enums;

public enum ConstsEnum {

    LOGIN_VERIFY_CODE("login_code"),
    SMS_PARAM_SPLIT(",");

    private String name;

    ConstsEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
