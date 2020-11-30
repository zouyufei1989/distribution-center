package com.money.custom.dao.impl;

import com.money.custom.dao.BannerDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "Banner")
public class BannerDaoImp extends BaseDaoImpl implements BannerDao {

}
