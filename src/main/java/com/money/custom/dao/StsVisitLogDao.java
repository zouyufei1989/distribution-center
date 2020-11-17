package com.money.custom.dao;


import com.money.custom.entity.StsVisitLog;
import com.money.custom.entity.request.QueryVisitLogRequest;
import com.money.framework.base.dao.BaseDao;

import java.util.List;

public interface StsVisitLogDao extends BaseDao {

    List<StsVisitLog> visitLogStsByResource(QueryVisitLogRequest request);

    List<StsVisitLog> visitLogStsByUser(QueryVisitLogRequest request);
}
