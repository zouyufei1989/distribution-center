package com.money.custom.entity.enums;

public enum ResponseCodeEnum {

    ERROR(-1, "失败"), SUCCESS(0, "成功");

    private Integer value;
    private String name;

    ResponseCodeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
