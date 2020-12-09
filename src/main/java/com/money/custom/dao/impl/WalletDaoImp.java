package com.money.custom.dao.impl;

import com.money.custom.dao.WalletDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "Wallet")
public class WalletDaoImp extends BaseDaoImpl implements WalletDao {

}
