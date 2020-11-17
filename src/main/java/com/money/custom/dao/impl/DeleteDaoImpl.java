package com.money.custom.dao.impl;

import com.money.custom.dao.DeleteDao;
import com.money.custom.entity.request.DeleteByIdsRequest;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "Delete")
public class DeleteDaoImpl extends BaseDaoImpl implements DeleteDao {

    @Override
    public void deleteByIds(DeleteByIdsRequest request) {
        insert("backup", request.buildParams());
        delete("delete", request.buildParams());
    }
}
