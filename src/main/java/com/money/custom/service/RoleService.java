package com.money.custom.service;

import com.money.custom.entity.Role;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.custom.entity.request.QueryRoleRequest;
import com.money.custom.entity.request.SaveRoleRightsRequest;
import com.money.framework.base.service.BaseService;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface RoleService extends BaseService {

	List<Role> selectSearchList(QueryRoleRequest request) ;

	Role findById(String id) ;

	void add(SaveRoleRightsRequest item) ;

	void edit(SaveRoleRightsRequest item) ;

	void changeStatus(ChangeStatusRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

	List<Map<String, Object>> queryUserRoleRights(String id) ;

	List<Map<String, Object>> queryFuncs() ;

}
