package com.money.custom.entity.enums;

import com.money.custom.entity.Consts;

public enum RedisKeyEnum {

    CITIES(Consts.GROUP_NAME + "_CITIES", 60 * 60),
    ROLES(Consts.GROUP_NAME + "_ROLES", 60 * 60),
    GROUPS(Consts.GROUP_NAME + "_GROUPS", 60 * 60),
    GOODS_TAGS(Consts.GROUP_NAME + "_GOODS_TAGS", 60 * 60),
    DEPARTMENTS(Consts.GROUP_NAME + "_DEPARTMENTS", 60 * 60),
    VERIFY_CODE(Consts.GROUP_NAME + "_VERIFY_CODE", 3 * 60),

    WECHAT_SESSION_KEY("SESSION_ID_", 0),

    SCHEDULE_CONFIG(Consts.GROUP_NAME + "_SCHEDULE_CONFIG", 60 * 10);

    private Integer timeout;
    private String name;

    /**
     * @param name    keyName
     * @param timeout redis timeout - seconds
     */
    RedisKeyEnum(String name, Integer timeout) {
        this.timeout = timeout;
        this.name = name;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public String getName() {
        return name;
    }

}
