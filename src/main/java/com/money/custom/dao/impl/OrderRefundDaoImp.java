package com.money.custom.dao.impl;

import com.money.custom.dao.OrderRefundDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "OrderRefund")
public class OrderRefundDaoImp extends BaseDaoImpl implements OrderRefundDao {

}
