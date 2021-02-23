package com.money.custom.dao.impl;

import com.money.custom.dao.PackageConsumptionDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "PackageConsumption")
public class PackageConsumptionDaoImp extends BaseDaoImpl implements PackageConsumptionDao {
}
