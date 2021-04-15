package com.money.custom.entity.enums;

public enum ResponseCodeEnum {

    ERROR(-1, "服务器异常"),
    INVALID_ARGUMENT(-2, "参数异常"),


    CUSTOMER_GROUP_EXISTS(1, "客户已注册该店铺"),
    GROUP_ID_NEEDED(2, "当前账号无所属门店"),
    LOG_TOO_LARGE(3, "日志过大"),
    ILLEGAL_STATUS(4, "非法状态"),
    WEEK_PASSWORD(5, "密码由数字、大小写字母和特殊字符组成，最短长度8位"),
    UPLOAD_FAIL(6, "上传失败"),
    LOGIN_FAIL(7, "用户名、密码错误"),
    WRONG_VERIFY_CODE(8, "验证码错误"),
    ASK_4_USER_INFO(10, "需要获取微信用户信息"),
    TOO_FREQUENCY_4_SMS_VERIFY_CODE(11, "频繁获取短信验证码"),
    WRONG_SMS_VERIFY_CODE(12, "短信验证码错误"),

    SUCCESS(0, "成功");

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
