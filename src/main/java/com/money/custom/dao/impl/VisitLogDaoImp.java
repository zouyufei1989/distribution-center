package com.money.custom.dao.impl;

import com.money.custom.dao.VisitLogDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "VisitLog")
public class VisitLogDaoImp extends BaseDaoImpl implements VisitLogDao {

}
