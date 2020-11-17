package com.money.custom.entity.enums;

public enum RabbitQueueNameEnum {

    QUEUE1("QUEUE1"),
    QUEUE2("QUEUE2");

    private String name;

    RabbitQueueNameEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
