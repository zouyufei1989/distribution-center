package com.money.custom.entity.enums;

public enum ReservationStatusEnum implements IEnumKeyValue {

    SUCCESS(1, "预约成功"),
    CANCEL(2, "已取消");

    private Integer value;
    private String name;

    ReservationStatusEnum(Integer value, String name) {
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
