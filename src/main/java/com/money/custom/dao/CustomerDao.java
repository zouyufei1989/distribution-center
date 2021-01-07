package com.money.custom.dao;

import com.money.framework.base.dao.BaseDao;

public interface CustomerDao extends BaseDao {

    void deleteByOpenId(String id);
}
