package com.money.custom.entity.enums;

import org.apache.commons.lang3.StringUtils;

public enum HistoryEntityEnum {

    GROUP(1, "GROUP"),
    BANNER(2, "BANNER"),
    GOODS_TAG(3, "GOODS_TAG"),
    GOODS(4, "GOODS"),
    BONUS_PLAN(5, "BONUS_PLAN"),
    CUSTOMER(6, "CUSTOMER"),
    CUSTOMER_GRUOP(7, "CUSTOMER_GROUP"),
    WALLET(8, "WALLET"),
    BONUS_WALLET(9, "BONUS_WALLET"),
    ASSIGN_ACTIVITY(10, "ASSIGN_ACTIVITY"),
    ORDER(11, "ORDER"),
    ORDER_ITEM(12, "ORDER_ITEM"),
    RESERVATION(13, "RESERVATION"),
    USER(97, "USER"),
    KEY_VALUE(98, "KEY_VALUE"),
    SCHEDULE_CONFIG(99, "SCHEDULE_CONFIG");

    private Integer value;
    private String name;

    HistoryEntityEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static HistoryEntityEnum get(String name) {
        for (HistoryEntityEnum item : HistoryEntityEnum.values()) {
            if (StringUtils.equals(item.getName(), name)) {
                return item;
            }
        }
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
