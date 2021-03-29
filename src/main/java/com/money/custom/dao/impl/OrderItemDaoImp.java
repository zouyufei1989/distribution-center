package com.money.custom.dao.impl;

import com.money.custom.dao.OrderItemDao;
import com.money.custom.entity.OrderItem;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@SQLContext(nameSpace = "OrderItem")
public class OrderItemDaoImp extends BaseDaoImpl implements OrderItemDao {

    @Override
    public void consumeCnt(List<Integer> orderItemIds, Integer cnt) {
        Map<String, Object> params = new HashMap<>();
        params.put("orderItemIds", orderItemIds);
        params.put("cnt", cnt);
        update("consumeCnt", params);
    }

    @Override
    public List<OrderItem> selectOrderItemsOfOrder(Integer orderId) {
        return selectList("selectOrderItemsOfOrder", orderId);
    }
}
