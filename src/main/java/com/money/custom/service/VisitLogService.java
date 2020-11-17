package com.money.custom.service;

import com.money.custom.entity.VisitLog;
import com.money.custom.entity.request.QueryVisitLogRequest;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface VisitLogService extends BaseService {

    List<VisitLog> selectSearchList(QueryVisitLogRequest request);

    int selectSearchListCount(QueryVisitLogRequest request);

    void add(VisitLog item);


}
