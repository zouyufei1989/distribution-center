package com.money.custom.entity.request;

import java.util.List;
import java.util.Map;

public class QueryEmployeeCustomerRequest extends QueryGridRequestBase {

    private Integer employeeId;
    private List<Integer> customerGroupIds;
    private List<String> employeeIds;
    private Integer status;


    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("employeeId", employeeId);
        params.put("customerGroupIds", customerGroupIds);
        params.put("employeeIds", employeeIds);
        params.put("status", status);
        return params;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public void setCustomerGroupIds(List<Integer> customerGroupIds) {
        this.customerGroupIds = customerGroupIds;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setEmployeeIds(List<String> employeeIds) {
        this.employeeIds = employeeIds;
    }
}
