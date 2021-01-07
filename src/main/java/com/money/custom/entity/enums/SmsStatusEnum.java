package com.money.custom.entity.enums;

public enum SmsStatusEnum implements IEnumKeyValue {

    PENDING(0, "待发送"),
    SENDING(1, "发送中"),
    SEND(2, "发送成功"),
    FAIL(-1, "发送失败");

    private Integer value;
    private String name;

    SmsStatusEnum(Integer value, String name) {
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
