package com.money.custom.dao;

import com.money.custom.entity.BonusWalletDetail;
import com.money.custom.entity.request.QueryBonusWalletDetailRequest;
import com.money.framework.base.dao.BaseDao;

import java.util.List;

public interface BonusWalletDao extends BaseDao {

    int addDetail(BonusWalletDetail detail);

    List<BonusWalletDetail> selectSearchList4Detail(QueryBonusWalletDetailRequest request);

    int selectSearchListCount4Detail(QueryBonusWalletDetailRequest request);


}
