package com.money.custom.service.impl;

import com.money.custom.dao.StsVisitLogDao;
import com.money.custom.entity.StsVisitLog;
import com.money.custom.entity.request.QueryVisitLogRequest;
import com.money.custom.service.StsVisitLogService;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StsVisitLogServiceImpl extends BaseServiceImpl implements StsVisitLogService {

    @Autowired
    StsVisitLogDao dao;

    @Override
    public List<StsVisitLog> visitLogStsByResource(QueryVisitLogRequest request) {
        return dao.visitLogStsByResource(request);
    }

    @Override
    public List<StsVisitLog> visitLogStsByUser(QueryVisitLogRequest request) {
        return dao.visitLogStsByUser(request);
    }
}
