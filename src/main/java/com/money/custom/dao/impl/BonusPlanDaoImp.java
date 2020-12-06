package com.money.custom.dao.impl;

import com.money.custom.dao.BonusPlanDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "BonusPlan")
public class BonusPlanDaoImp extends BaseDaoImpl implements BonusPlanDao {

}
