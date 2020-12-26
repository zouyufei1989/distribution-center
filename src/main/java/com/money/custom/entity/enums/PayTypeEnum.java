package com.money.custom.entity.enums;

import java.util.Objects;

public enum PayTypeEnum implements IEnumKeyValue {

    NONE(0, ""),
    MONEY(1, "余额"),
    BONUS(2, "积分"),
    OFFLINE(4, "非余额/线下");

    private Integer value;
    private String name;

    PayTypeEnum(Integer value, String name) {
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

    public boolean pay(Integer type) {
        if (Objects.isNull(type)) {
            return false;
        }
        return (type & this.getValue()) == this.getValue();
    }

    public boolean payOnly(Integer type) {
        return type.equals(this.getValue());
    }


}
