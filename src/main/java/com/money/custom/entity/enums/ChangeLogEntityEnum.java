package com.money.custom.entity.enums;

import org.apache.commons.lang3.StringUtils;

public enum ChangeLogEntityEnum {

    GROUP(1, "GROUP"),
    BANNER(2, "BANNER"),
    GOODS_TAG(3, "GOODS_TAG"),
    GOODS(4, "GOODS"),
    USER(97, "USER"),
    KEY_VALUE(98, "KEY_VALUE"),
    SCHEDULE_CONFIG(99, "SCHEDULE_CONFIG");

    private Integer value;
    private String name;

    ChangeLogEntityEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static ChangeLogEntityEnum get(String name) {
        for (ChangeLogEntityEnum item : ChangeLogEntityEnum.values()) {
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
