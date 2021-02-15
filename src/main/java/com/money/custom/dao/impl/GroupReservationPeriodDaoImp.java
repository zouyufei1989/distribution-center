package com.money.custom.dao.impl;

import com.money.custom.dao.GroupReservationPeriodDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "GroupReservationPeriod")
public class GroupReservationPeriodDaoImp extends BaseDaoImpl implements GroupReservationPeriodDao {

}
