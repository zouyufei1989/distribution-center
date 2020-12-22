package com.money.custom.dao.impl;

import com.money.custom.dao.BonusWalletDao;
import com.money.custom.entity.BonusWalletDetail;
import com.money.custom.entity.request.QueryBonusWalletDetailRequest;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SQLContext(nameSpace = "BonusWallet")
public class BonusWalletDaoImp extends BaseDaoImpl implements BonusWalletDao {

    @Override
    public int addDetail(BonusWalletDetail detail) {
        return insert("addDetail", detail);
    }

    @Override
    public List<BonusWalletDetail> selectSearchList4Detail(QueryBonusWalletDetailRequest request) {
        return selectList("selectSearchList4Detail", request.buildParams());
    }

    @Override
    public int selectSearchListCount4Detail(QueryBonusWalletDetailRequest request) {
        return selectOne("selectSearchListCount4Detail", request.buildParams());
    }
}
