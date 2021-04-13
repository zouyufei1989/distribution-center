package com.money.custom.entity.enums;

import com.money.framework.base.entity.VisitLogTypeEnum;

public enum EmployeeStatusEnum implements IEnumKeyValue {

    ENABLE(1, "在职"),
    DISABLE(0, "离职"),
    DELETED(-2, "删除");

    private Integer value;
    private String name;

    EmployeeStatusEnum(Integer value, String name) {
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
        return this != EmployeeStatusEnum.DELETED;
    }


}
