package com.money.custom.dao.impl;

import com.money.custom.dao.CustomerGroupDao;
import com.money.custom.entity.CustomerGroup;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SQLContext(nameSpace = "CustomerGroup")
public class CustomerGroupDaoImp extends BaseDaoImpl implements CustomerGroupDao {

    @Override
    public List<CustomerGroup> findByOpenId(String openId) {
        return selectList("findByOpenId", openId);
    }
}
