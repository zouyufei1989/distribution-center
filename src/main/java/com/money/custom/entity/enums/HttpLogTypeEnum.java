package com.money.custom.entity.enums;

public enum HttpLogTypeEnum implements IEnumKeyValue {

    WECHAT_LOGIN(1, "小程序单点登录，获取openId");

    private Integer value;
    private String name;

    HttpLogTypeEnum(Integer value, String name) {
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
