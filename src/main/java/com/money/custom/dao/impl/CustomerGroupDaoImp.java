package com.money.custom.dao.impl;

import com.money.custom.dao.CustomerGroupDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "CustomerGroup")
public class CustomerGroupDaoImp extends BaseDaoImpl implements CustomerGroupDao {

}
