package com.money.custom.entity.request;

import com.money.custom.entity.Role;

import java.util.Map;

public class QueryRoleRequest extends QueryGridRequestBase {

    private Role role = new Role();

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("role", role);
        return params;
    }

}
