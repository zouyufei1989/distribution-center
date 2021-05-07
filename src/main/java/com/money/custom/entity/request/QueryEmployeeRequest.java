package com.money.custom.entity.request;

import com.money.custom.entity.Employee;

import java.util.Map;

public class QueryEmployeeRequest extends QueryGridRequestBase {

   private Employee employee = new Employee();

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("employee", employee);
        return params;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setOpenId(String openId){
        this.employee.setOpenId(openId);
    }
}
