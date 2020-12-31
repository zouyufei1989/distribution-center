package com.money.custom.dao;

import com.money.custom.entity.CustomerGroup;
import com.money.framework.base.dao.BaseDao;

import java.util.List;

public interface CustomerGroupDao extends BaseDao {

    List<CustomerGroup> findByOpenId(String openId);
}
