package com.money.custom.entity.enums;

public enum DeletableTableNameEnum {

    CUSTOMER_GROUP("d_customer_group"),
    EMPLOYEE("d_employee");

    private String name;

    DeletableTableNameEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
