package com.money.custom.dao.impl;

import com.money.custom.dao.GroupDao;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
@SQLContext(nameSpace = "Group")
public class GroupDaoImp extends BaseDaoImpl implements GroupDao {

}
