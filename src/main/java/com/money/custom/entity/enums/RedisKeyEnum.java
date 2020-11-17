package com.money.custom.entity.enums;

import com.money.custom.entity.Consts;

public enum RedisKeyEnum {

    CITIES(Consts.GROUP_NAME + "_CITIES", 60 * 60),
    ROLES(Consts.GROUP_NAME + "_ROLES", 60 * 60),
    GROUPS(Consts.GROUP_NAME + "_GROUPS", 60 * 60),
    PASSENGER_TYPES(Consts.GROUP_NAME + "_PASSENGER_TYPES", 60 * 60),
    VEHICLE_TYPES(Consts.GROUP_NAME + "_VEHICLE_TYPES", 60 * 60),
    DEPARTMENTS(Consts.GROUP_NAME + "_DEPARTMENTS", 60 * 60),
    DRIVERS(Consts.GROUP_NAME + "_DRIVERS", 60 * 60),
    VEHICLES(Consts.GROUP_NAME + "_VEHICLES", 60 * 60),
    VEHICLE_LEADERS(Consts.GROUP_NAME + "_VEHICLE_LEADERS", 60 * 60),
    ROUTE_TAGS(Consts.GROUP_NAME + "_ROUTE_TAGS", 60 * 60),
    ROUTE_TYPES(Consts.GROUP_NAME + "_ROUTE_TYPES", 60 * 60),
    DEVICES(Consts.GROUP_NAME + "_DEVICES", 60 * 60),
    AREAS(Consts.GROUP_NAME + "_AREAS", 60 * 60),
    VERIFY_CODE(Consts.GROUP_NAME + "_VERIFY_CODE", 3 * 60),
    WECHAT_TOKEN(Consts.GROUP_NAME + "_wechat_token_key", 110 * 60),
    SCHAEFFLER_TOKEN(Consts.GROUP_NAME + "_token_key", 110 * 60),
    SCHEDULE_CONFIG(Consts.GROUP_NAME + "_SCHEDULE_CONFIG", 60 * 10),
    NEW_FEEDBACK_REPLY_COMING("feedback_red_point_reply_", -1);

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
