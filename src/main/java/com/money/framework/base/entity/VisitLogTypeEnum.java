package com.money.framework.base.entity;

import com.money.custom.entity.enums.IEnumKeyValue;

public enum VisitLogTypeEnum implements IEnumKeyValue {

    NONE(-1, ""),
    READ(1, "查看"),
    EDIT(2, "编辑"),
    IMPORT(3, "导入"),
    EXPORT(4, "导出");


    private Integer value;
    private String name;

    VisitLogTypeEnum(Integer value, String name) {
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

    @Override
    public boolean availableForMap() {
        return this != VisitLogTypeEnum.NONE;
    }


}
