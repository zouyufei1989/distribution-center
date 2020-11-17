package com.money.custom.dao.impl;

import com.money.custom.dao.KeyValueDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "KeyValue")
public class KeyValueDaoImp extends BaseDaoImpl implements KeyValueDao {

}
