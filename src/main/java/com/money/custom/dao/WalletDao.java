package com.money.custom.dao;

import com.money.custom.entity.WalletDetail;
import com.money.custom.entity.request.QueryBonusWalletDetailRequest;
import com.money.custom.entity.request.QueryWalletDetailRequest;
import com.money.framework.base.dao.BaseDao;

import java.util.List;

public interface WalletDao extends BaseDao {

    List<WalletDetail> selectSearchList4Detail(QueryWalletDetailRequest request);

    int selectSearchListCount4Detail(QueryWalletDetailRequest request);

    int addDetail(WalletDetail item);
}
