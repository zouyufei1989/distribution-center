package com.money.custom.dao.impl;

import com.money.custom.dao.ScheduleConfigDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "ScheduleConfig")
public class ScheduleConfigDaoImpl extends BaseDaoImpl implements ScheduleConfigDao {

}
