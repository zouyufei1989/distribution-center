package com.money.custom.entity.enums;

public interface IEnumKeyValue {

    String DEFAULT_NAME = "";
    Integer DEFAULT_VALUE = -9999;

    Object getValue();

    String getName();

    default boolean availableForMap() {
        return true;
    }
}
