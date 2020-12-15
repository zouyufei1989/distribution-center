package com.money.custom.entity.enums;

public enum OrderStatusEnum implements IEnumKeyValue {

    PENDING_PAY(1, "待支付"),
    NOT_USED(2, "已支付，未使用"),
    USING(2, "已支付，使用中"),
    USED(2, "已支付，已用光"),
    CANCEL(2, "已取消");

    private Integer value;
    private String name;

    OrderStatusEnum(Integer value, String name) {
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
