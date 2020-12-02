package com.money.custom.dao.impl;

import com.money.custom.dao.GoodsDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "Goods")
public class GoodsDaoImp extends BaseDaoImpl implements GoodsDao {

}
