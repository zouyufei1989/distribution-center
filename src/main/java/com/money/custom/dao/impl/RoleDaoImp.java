package com.money.custom.dao.impl;

import com.money.custom.dao.RoleDao;
import com.money.custom.entity.RoleRight;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@SQLContext(nameSpace = "Role")
public class RoleDaoImp extends BaseDaoImpl implements RoleDao {

	@Override
	public List<Map<String, Object>> queryUserRoleRights(String id)  {
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		return this.selectList("queryUserRoleRights", params);
	}

	@Override
	public List<Map<String, Object>> queryFuncs()  {
		return this.selectList("queryFuncs");
	}

	@Override
	public void addRoleRightBatch(List<RoleRight> roleRights)  {
		Map<String, Object> params = new HashMap<>();
		params.put("roleRights", roleRights);
		insert("addRoleRightBatch", params);
	}

	@Override
	public void deleteRoleRightsByRoleId(int roleId)  {
		Map<String, Object> params = new HashMap<>();
		params.put("roleId", roleId);
		delete("deleteRoleRightsByRoleId", params);
	}

}
