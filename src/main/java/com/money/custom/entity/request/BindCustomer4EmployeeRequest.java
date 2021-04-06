package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class BindCustomer4EmployeeRequest extends OperationalEntity {

    @NotEmpty(message = "未选中股东")
    private List<Integer> customerGroupIds;
    @NotNull(message = "请指定员工id")
    private Integer employeeId;

    public List<Integer> getCustomerGroupIds() {
        return customerGroupIds;
    }

    public void setCustomerGroupIds(List<Integer> customerGroupIds) {
        this.customerGroupIds = customerGroupIds;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
