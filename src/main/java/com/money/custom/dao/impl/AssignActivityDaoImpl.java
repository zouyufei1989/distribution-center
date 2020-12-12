package com.money.custom.dao.impl;

import com.money.custom.dao.AssignActivityDao;
import com.money.custom.entity.AssignActivityItem;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
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

}
