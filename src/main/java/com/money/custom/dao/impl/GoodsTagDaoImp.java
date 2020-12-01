package com.money.custom.dao.impl;

import com.money.custom.dao.GoodsTagDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "GoodsTag")
public class GoodsTagDaoImp extends BaseDaoImpl implements GoodsTagDao {

}
