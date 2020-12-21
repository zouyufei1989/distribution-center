package com.money.custom.dao;

import com.money.framework.base.dao.BaseDao;

import java.util.List;

public interface OrderItemDao extends BaseDao {

    void consumeCnt(List<Integer> orderItemIds, Integer cnt);

}
