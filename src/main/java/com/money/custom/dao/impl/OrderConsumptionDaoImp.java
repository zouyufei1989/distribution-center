package com.money.custom.dao.impl;

import com.money.custom.dao.OrderConsumptionDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "OrderConsumption")
public class OrderConsumptionDaoImp extends BaseDaoImpl implements OrderConsumptionDao {

}
