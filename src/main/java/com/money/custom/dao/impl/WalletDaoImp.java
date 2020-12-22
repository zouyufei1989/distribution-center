package com.money.custom.dao.impl;

import com.money.custom.dao.WalletDao;
import com.money.custom.entity.WalletDetail;
import com.money.custom.entity.request.QueryWalletDetailRequest;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SQLContext(nameSpace = "Wallet")
public class WalletDaoImp extends BaseDaoImpl implements WalletDao {

    @Override
    public List<WalletDetail> selectSearchList4Detail(QueryWalletDetailRequest request) {
        return selectList("selectSearchList4Detail", request.buildParams());
    }

    @Override
    public int selectSearchListCount4Detail(QueryWalletDetailRequest request) {
        return selectOne("selectSearchListCount4Detail", request.buildParams());
    }

    @Override
    public int addDetail(WalletDetail item) {
        return insert("addDetail", item);
    }
}
