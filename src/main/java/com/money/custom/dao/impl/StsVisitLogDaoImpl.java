package com.money.custom.dao.impl;

import com.money.custom.dao.StsVisitLogDao;
import com.money.custom.entity.StsVisitLog;
import com.money.custom.entity.request.QueryVisitLogRequest;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SQLContext(nameSpace = "StsVisitLog")
public class StsVisitLogDaoImpl extends BaseDaoImpl implements StsVisitLogDao {

    @Override
    public List<StsVisitLog> visitLogStsByResource(QueryVisitLogRequest request) {
        return selectList("visitLogStsByResource", request.buildParams());
    }

    @Override
    public List<StsVisitLog> visitLogStsByUser(QueryVisitLogRequest request) {
        return selectList("visitLogStsByUser", request.buildParams());
    }
}
