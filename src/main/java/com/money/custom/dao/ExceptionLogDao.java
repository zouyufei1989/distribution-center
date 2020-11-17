package com.money.custom.dao;

import com.money.custom.entity.ExceptionLog;
import com.money.custom.entity.request.QueryExceptionLogRequest;
import com.money.framework.base.dao.BaseDao;

import java.util.List;

public interface ExceptionLogDao extends BaseDao {

    List<ExceptionLog> selectSearchListGroupByMethod(QueryExceptionLogRequest request);
}
