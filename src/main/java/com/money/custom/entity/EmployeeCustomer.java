package com.money.custom.entity;

import com.money.framework.base.entity.BaseEntity;

public class EmployeeCustomer extends BaseEntity {

    private Integer id;
    private Integer parentId;

    private Integer employeeId;
    private Integer parentEmployeeId;
    private Integer customerGroupId;

    private String inheritChain;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getParentEmployeeId() {
        return parentEmployeeId;
    }

    public void setParentEmployeeId(Integer parentEmployeeId) {
        this.parentEmployeeId = parentEmployeeId;
    }

    public Integer getCustomerGroupId() {
        return customerGroupId;
    }

    public void setCustomerGroupId(Integer customerGroupId) {
        this.customerGroupId = customerGroupId;
    }

    public String getInheritChain() {
        return inheritChain;
    }

    public void setInheritChain(String inheritChain) {
        this.inheritChain = inheritChain;
    }
}
