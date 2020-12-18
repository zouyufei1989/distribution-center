package com.money.custom.dao.impl;

import com.money.custom.dao.OrderPayItemDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "OrderPayItem")
public class OrderPayItemDaoImp extends BaseDaoImpl implements OrderPayItemDao {

}
