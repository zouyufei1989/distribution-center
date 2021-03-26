package com.money.custom.entity.enums;

public enum OrderStatusEnum implements IEnumKeyValue {

    PENDING_PAY(1, "待支付"),
    USING(2, "已支付，使用中"),
    USED(3, "已支付，已用光"),
    CANCEL(4, "已取消"),
    REFUND(5, "已退款"),
    REFUND_BONUS(6, "已退积分");

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

    public static boolean orderAvailable(Integer orderStatus){
        return orderStatus.equals(OrderStatusEnum.USING.getValue());
    }
}
