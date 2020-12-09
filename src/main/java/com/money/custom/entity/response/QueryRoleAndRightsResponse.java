package com.money.custom.entity.response;

import com.money.custom.entity.Role;
import com.money.custom.entity.dto.FileUploaded;
import com.money.framework.base.entity.ResponseBase;

public class QueryRoleAndRightsResponse extends ResponseBase {

    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
