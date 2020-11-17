package com.money.custom.entity.request;

import java.util.List;

import com.money.custom.entity.Role;
import com.money.custom.entity.RoleRight;
import com.money.framework.base.entity.OperationalEntity;

public class SaveRoleRightsRequest extends OperationalEntity {

	private Role role;
	private List<RoleRight> roleRights;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<RoleRight> getRoleRights() {
		return roleRights;
	}

	public void setRoleRights(List<RoleRight> roleRights) {
		this.roleRights = roleRights;
	}

}
