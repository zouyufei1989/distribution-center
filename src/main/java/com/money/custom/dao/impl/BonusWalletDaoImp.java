package com.money.custom.dao.impl;

import com.money.custom.dao.BonusWalletDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "BonusWallet")
public class BonusWalletDaoImp extends BaseDaoImpl implements BonusWalletDao {

}
