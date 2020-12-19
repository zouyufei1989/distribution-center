package com.money.custom.service.impl;

import com.money.custom.dao.OrderPayItemDao;
import com.money.custom.entity.OrderPayItem;
import com.money.custom.service.OrderPayItemService;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderPayItemServiceImpl extends BaseServiceImpl implements OrderPayItemService {

    @Autowired
    OrderPayItemDao dao;

    @Override
    public String add(OrderPayItem item) {
        dao.add(item);
        return item.toString();
    }
}
