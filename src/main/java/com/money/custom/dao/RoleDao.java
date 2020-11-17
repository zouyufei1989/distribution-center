package com.money.custom.dao;

import com.money.custom.entity.RoleRight;
import com.money.framework.base.dao.BaseDao;

import java.util.List;
import java.util.Map;

public interface RoleDao extends BaseDao {

	List<Map<String, Object>> queryFuncs() ;

	List<Map<String, Object>> queryUserRoleRights(String id) ;

	void addRoleRightBatch(List<RoleRight> roleRights) ;

	void deleteRoleRightsByRoleId(int roleId) ;

}
