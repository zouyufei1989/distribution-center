package com.money.custom.service.impl;

import com.money.custom.dao.VisitLogDao;
import com.money.custom.entity.VisitLog;
import com.money.custom.entity.request.QueryVisitLogRequest;
import com.money.custom.service.VisitLogService;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitLogServiceImp extends BaseServiceImpl implements VisitLogService {

    @Autowired
    VisitLogDao dao;


    @Override
    public List<VisitLog> selectSearchList(QueryVisitLogRequest request) {
        return dao.selectSearchList(request);
    }

    @Override
    public int selectSearchListCount(QueryVisitLogRequest request) {
        return dao.selectSearchListCount(request);
    }

    @Override
    public void add(VisitLog item) {
        dao.add(item);
    }
}
