package com.money.custom.service;

import com.money.custom.entity.AssignActivity;
import com.money.custom.entity.CustomerActivity;
import com.money.custom.entity.request.AssignActivityRequest;
import com.money.custom.entity.request.QueryAssignActivityRequest;
import com.money.framework.base.service.BaseService;
import com.money.h5.entity.H5GridRequestBase;
import com.money.h5.entity.H5RequestBase;
import com.money.h5.entity.request.QueryByIdRequest;

import java.util.List;
import java.util.Map;

public interface AssignActivityService extends BaseService {

    List<AssignActivity> selectSearchList(QueryAssignActivityRequest request);

    int selectSearchListCount(QueryAssignActivityRequest request);

    List<CustomerActivity> selectCustomerActivityList(H5GridRequestBase request);

    Integer selectCustomerActivityCount(H5GridRequestBase request);

    String add(AssignActivityRequest item);

    List<Map<String, Object>> querySummary();
}
