package com.money.custom.entity.enums;

public enum DeletableTableNameEnum {

    DRIVER("d_driver");

    private String name;

    DeletableTableNameEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
