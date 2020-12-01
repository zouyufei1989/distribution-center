package com.money.custom.service.impl;

import com.money.custom.dao.RoleDao;
import com.money.custom.entity.Role;
import com.money.custom.entity.RoleRight;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.custom.entity.request.QueryRoleRequest;
import com.money.custom.entity.request.SaveRoleRightsRequest;
import com.money.custom.service.RoleService;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> selectSearchList(QueryRoleRequest request)  {
        List<Role> roles = this.roleDao.selectSearchList(request);
        return pickupFamilyMembers(roles,request.getGroupId());
    }


    @Override
    public Role findById(String id)  {
        return roleDao.findById(id);
    }

    @Override
    public void changeStatus(ChangeStatusRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        roleDao.changeStatus(request);
    }

    @Override
    @Transactional
    public void add(SaveRoleRightsRequest request)  {
        roleDao.add(request.getRole());
        if(CollectionUtils.isEmpty(request.getRoleRights())){
            return;
        }
        for (RoleRight item : request.getRoleRights()) {
            item.setRoleId(request.getRole().getId());
        }
        this.roleDao.addRoleRightBatch(request.getRoleRights());
    }

    @Transactional
    @Override
    public void edit(SaveRoleRightsRequest request)  {
        this.roleDao.deleteRoleRightsByRoleId(request.getRole().getId());
        this.roleDao.edit(request.getRole());
        if(CollectionUtils.isEmpty(request.getRoleRights())){
            return;
        }
        this.roleDao.addRoleRightBatch(request.getRoleRights());
    }

    @Override
    public List<Map<String, Object>> queryUserRoleRights(String id)  {
        return roleDao.queryUserRoleRights(id);
    }

    @Override
    public List<Map<String, Object>> queryFuncs()  {
        return roleDao.queryFuncs();
    }

}
