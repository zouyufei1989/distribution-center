package com.money.custom.service;

import com.money.custom.entity.Group;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.custom.entity.request.QueryGridRequestBase;
import com.money.framework.base.service.BaseService;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface GroupService extends BaseService {

    List<Group> selectSearchList(QueryGridRequestBase request) ;

    int selectSearchListCount(QueryGridRequestBase request) ;

    Group findById(String id) ;

    String add(Group item) ;

    String edit(Group item) ;

    List<String> changeStatus(ChangeStatusRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

}
