package com.money.custom.entity.enums;

public enum EmployeeLevelEnum implements IEnumKeyValue {

    CLERK(1, "店员"),
    TEAM_LEADER(2, "组长"),
    SHOP_OWNER(3, "店长");

    private Integer value;
    private String name;

    EmployeeLevelEnum(Integer value, String name) {
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
