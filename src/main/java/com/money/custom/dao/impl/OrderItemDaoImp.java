package com.money.custom.dao.impl;

import com.money.custom.dao.BannerDao;
import com.money.custom.dao.OrderItemDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "OrderItem")
public class OrderItemDaoImp extends BaseDaoImpl implements OrderItemDao {

}
