package com.money.custom.dao.impl;

import com.money.custom.dao.BonusWalletDao;
import com.money.custom.dao.CustomerDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "Customer")
public class CustomerDaoImp extends BaseDaoImpl implements CustomerDao {

}
