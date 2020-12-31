package com.money.custom.dao.impl;

import com.money.custom.dao.AssignActivityDao;
import com.money.custom.entity.AssignActivity;
import com.money.custom.entity.AssignActivityItem;
import com.money.custom.entity.CustomerActivity;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import com.money.h5.entity.H5GridRequestBase;
import com.money.h5.entity.H5RequestBase;
import com.money.h5.entity.request.QueryByIdRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@SQLContext(nameSpace = "AssignActivity")
public class AssignActivityDaoImpl extends BaseDaoImpl implements AssignActivityDao {

    @Override
    public List<Map<String, Object>> querySummary() {
        return selectList("querySummary");
    }

    @Override
    public List<CustomerActivity> selectCustomerActivityList(H5GridRequestBase request) {
        return selectList("selectCustomerActivityList", request.buildParams());
    }

    @Override
    public Integer selectCustomerActivityCount(H5GridRequestBase request) {
        return selectOne("selectCustomerActivityCount", request.buildParams());
    }

    @Override
    public AssignActivityItem findAssignActivityItemById(String id) {
        return selectOne("findAssignActivityItemById", id);
    }

    @Override
    public void claimActivity(Integer id) {
        update("claimActivity", id);
    }

}
