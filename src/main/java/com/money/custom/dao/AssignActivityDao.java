package com.money.custom.dao;

import com.money.custom.entity.AssignActivityItem;
import com.money.custom.entity.CustomerActivity;
import com.money.framework.base.dao.BaseDao;
import com.money.h5.entity.H5GridRequestBase;

import java.util.List;
import java.util.Map;

public interface AssignActivityDao extends BaseDao {

    List<Map<String, Object>> querySummary();

    List<CustomerActivity> selectCustomerActivityList(H5GridRequestBase request);

    Integer selectCustomerActivityCount(H5GridRequestBase request);

    AssignActivityItem findAssignActivityItemById(String id);

    void claimActivity(Integer id);
}
