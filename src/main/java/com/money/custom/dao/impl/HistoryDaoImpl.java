package com.money.custom.dao.impl;

import com.money.custom.dao.HistoryDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "History")
public class HistoryDaoImpl extends BaseDaoImpl implements HistoryDao {
}
