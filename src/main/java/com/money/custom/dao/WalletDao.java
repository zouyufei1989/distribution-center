package com.money.custom.dao;

import com.money.custom.entity.WalletDetail;
import com.money.framework.base.dao.BaseDao;

public interface WalletDao extends BaseDao {

    int addDetail(WalletDetail item);
}
