package com.money.custom.dao;

import com.money.custom.entity.OrderItemConsumption;
import com.money.framework.base.dao.BaseDao;

import java.util.List;

public interface OrderConsumptionDao extends BaseDao {

    List<OrderItemConsumption> selectConsumptionItemsByConsumptionId(Integer orderId);
}
