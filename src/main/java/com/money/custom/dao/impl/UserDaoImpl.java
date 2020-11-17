package com.money.custom.dao.impl;

import com.money.custom.dao.UserDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "User")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

}
