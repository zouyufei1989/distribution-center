package com.money.custom.dao;

import com.money.custom.entity.CustomerActivity;
import com.money.framework.base.dao.BaseDao;
import com.money.h5.entity.request.QueryByIdRequest;

import java.util.List;
import java.util.Map;

public interface AssignActivityDao extends BaseDao {

    List<Map<String, Object>> querySummary();

    List<CustomerActivity> selectCustomerActivityList(QueryByIdRequest request);

    Integer selectCustomerActivityCount(QueryByIdRequest request);
}
