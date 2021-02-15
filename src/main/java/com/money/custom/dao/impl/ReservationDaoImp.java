package com.money.custom.dao.impl;

import com.money.custom.dao.ReservationDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "Reservation")
public class ReservationDaoImp extends BaseDaoImpl implements ReservationDao {

}
