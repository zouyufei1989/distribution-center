package com.money.custom.service.impl;

import com.money.custom.dao.HistoryDao;
import com.money.custom.entity.History;
import com.money.custom.entity.request.QueryHistoryRequest;
import com.money.custom.service.HistoryService;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl extends BaseServiceImpl implements HistoryService {

    @Autowired
    HistoryDao dao;

    @Override
    public List<Object> selectSearchList(QueryHistoryRequest request) {
        return dao.selectSearchList(request);
    }

    @Override
    public int selectSearchListCount(QueryHistoryRequest request) {
        return dao.selectSearchListCount(request);
    }

    @Override
    public void addBatch(List<History> list) {
        dao.addBatch(list);
    }

}
