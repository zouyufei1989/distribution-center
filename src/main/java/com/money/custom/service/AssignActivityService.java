package com.money.custom.service;

import com.money.custom.entity.AssignActivity;
import com.money.custom.entity.request.AssignActivityRequest;
import com.money.custom.entity.request.QueryAssignActivityRequest;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface AssignActivityService extends BaseService {

    List<AssignActivity> selectSearchList(QueryAssignActivityRequest request);

    int selectSearchListCount(QueryAssignActivityRequest request);

    String add(AssignActivityRequest item);

}
