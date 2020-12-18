package com.money.custom.dao.impl;

import com.money.custom.dao.OrderPayDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "OrderPay")
public class OrderPayDaoImp extends BaseDaoImpl implements OrderPayDao {

}
