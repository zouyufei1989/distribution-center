package com.money.custom.entity.enums;

public enum HttpLogTypeEnum implements IEnumKeyValue {

    SCHAEFFLER_ACCESS_TOKEN(1, "获取Schaeffler token"),
    WECHAT_ACCESS_TOKEN(2, "获取wechat token"),
    SCHAEFFLER_CHANGE_LOG(3, "获取舍弗勒员工变化log"),
    SCHAEFFLER_PERSON_NUMBERS(4, "根据雇员ID查询员工信息"),
    WECHAT_MSG(4, "发送企业微信消息");

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
