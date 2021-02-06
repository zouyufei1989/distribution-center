package com.money.custom.dao.impl;

import com.money.custom.dao.OrderDao;
import com.money.custom.entity.OrderRefundParams;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "Order")
public class OrderDaoImp extends BaseDaoImpl implements OrderDao {

    @Override
    public OrderRefundParams queryOrderInfo4Refund(Integer orderId) {
        return selectOne("queryOrderInfo4Refund", orderId);
    }
}
