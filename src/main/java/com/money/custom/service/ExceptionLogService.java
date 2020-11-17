package com.money.custom.service;

import com.money.custom.entity.ExceptionLog;
import com.money.custom.entity.request.QueryExceptionLogRequest;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface ExceptionLogService extends BaseService {

    List<ExceptionLog> selectSearchList(QueryExceptionLogRequest request) ;

    List<ExceptionLog> selectSearchListGroupByMethod(QueryExceptionLogRequest request);

    void addBatch(List<ExceptionLog> items);
}
