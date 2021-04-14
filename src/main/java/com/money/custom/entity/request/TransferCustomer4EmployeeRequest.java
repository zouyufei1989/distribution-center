package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;

import javax.validation.constraints.NotNull;

public class TransferCustomer4EmployeeRequest extends OperationalEntity {

    @NotNull(message = "请指定原员工id")
    private Integer srcEmployeeId;
    @NotNull(message = "请指定员工id")
    private Integer employeeId;

    public Integer getSrcEmployeeId() {
        return srcEmployeeId;
    }

    public void setSrcEmployeeId(Integer srcEmployeeId) {
        this.srcEmployeeId = srcEmployeeId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
