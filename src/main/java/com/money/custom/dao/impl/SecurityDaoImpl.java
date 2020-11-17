package com.money.custom.dao.impl;

import com.money.custom.dao.SecurityDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by songruimin on 14/12/16.
 */
@Repository
@SQLContext(nameSpace = "Security")
public class SecurityDaoImpl extends BaseDaoImpl implements SecurityDao {

}
