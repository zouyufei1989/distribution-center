package com.money.custom.dao;

import com.money.custom.entity.BonusWalletDetail;
import com.money.framework.base.dao.BaseDao;

public interface BonusWalletDao extends BaseDao {

    int addDetail(BonusWalletDetail detail);

}
