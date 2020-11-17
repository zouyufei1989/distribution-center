package com.money.custom.dao.impl;

import com.money.custom.dao.HttpLogDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "HttpLog")
public class HttpLogDaoImp extends BaseDaoImpl implements HttpLogDao {

}
