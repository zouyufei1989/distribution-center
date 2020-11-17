package com.money.custom.dao.impl;

import com.money.custom.dao.ExceptionLogDao;
import com.money.custom.entity.ExceptionLog;
import com.money.custom.entity.request.QueryExceptionLogRequest;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SQLContext(nameSpace = "ExceptionLog")
public class ExceptionLogDaoImp extends BaseDaoImpl implements ExceptionLogDao {

    @Override
    public List<ExceptionLog> selectSearchListGroupByMethod(QueryExceptionLogRequest request) {
        return selectList("selectSearchListGroupByMethod", request.buildParams());
    }
}
