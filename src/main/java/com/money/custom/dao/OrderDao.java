package com.money.custom.dao;

import com.money.custom.entity.OrderRefundParams;
import com.money.framework.base.dao.BaseDao;

public interface OrderDao extends BaseDao {

    OrderRefundParams queryOrderInfo4Refund(Integer orderId);
}
