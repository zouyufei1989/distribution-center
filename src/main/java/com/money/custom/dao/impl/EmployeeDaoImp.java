package com.money.custom.dao.impl;

import com.money.custom.dao.EmployeeDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "Employee")
public class EmployeeDaoImp extends BaseDaoImpl implements EmployeeDao {

}
