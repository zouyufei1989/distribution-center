package com.money.custom.dao;

import com.money.custom.entity.AssignActivityItem;
import com.money.framework.base.dao.BaseDao;

import java.util.List;
import java.util.Map;

public interface AssignActivityDao extends BaseDao {

    List<Map<String, Object>> querySummary();
}
