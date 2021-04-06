package com.money.custom.dao.impl;

import com.money.custom.dao.BannerDao;
import com.money.custom.dao.EmployeeCustomerDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "EmployeeCustomer")
public class EmployeeCustomerDaoImp extends BaseDaoImpl implements EmployeeCustomerDao {

}
