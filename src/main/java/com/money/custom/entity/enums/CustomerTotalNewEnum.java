package com.money.custom.entity.enums;

public enum CustomerTotalNewEnum implements IEnumKeyValue {

    NEW(1, "新注册"),
    OLD(0, "已消费");

    private Integer value;
    private String name;

    CustomerTotalNewEnum(Integer value, String name) {
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
        return (type & this.getValue()) == this.getValue();
    }

    public boolean payOnly(Integer type) {
        return type.equals(this.getValue());
    }


}
