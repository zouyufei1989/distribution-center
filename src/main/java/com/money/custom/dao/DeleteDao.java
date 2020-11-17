package com.money.custom.dao;

import com.money.custom.entity.request.DeleteByIdsRequest;
import com.money.framework.base.dao.BaseDao;

public interface DeleteDao extends BaseDao {

    void deleteByIds(DeleteByIdsRequest request);

}
