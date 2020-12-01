package com.money.custom.service.impl;

import com.money.custom.dao.ExceptionLogDao;
import com.money.custom.entity.ExceptionLog;
import com.money.custom.entity.request.QueryExceptionLogRequest;
import com.money.custom.service.ExceptionLogService;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExceptionLogServiceImpl extends BaseServiceImpl implements ExceptionLogService {

    @Autowired
    ExceptionLogDao dao;

    @Override
    public List<ExceptionLog> selectSearchList(QueryExceptionLogRequest request) {
        return dao.selectSearchList(request);
    }

    @Override
    public List<ExceptionLog> selectSearchListGroupByMethod(QueryExceptionLogRequest request) {
        return dao.selectSearchListGroupByMethod(request);
    }

    @Override
    public void addBatch(List<ExceptionLog> items) {
        dao.addBatch(items);
    }
}

