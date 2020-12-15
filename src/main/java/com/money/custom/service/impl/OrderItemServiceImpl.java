package com.money.custom.service.impl;

import com.money.custom.dao.OrderDao;
import com.money.custom.dao.OrderItemDao;
import com.money.custom.entity.Order;
import com.money.custom.entity.OrderItem;
import com.money.custom.entity.enums.HistoryEntityEnum;
import com.money.custom.entity.request.AddOrderRequest;
import com.money.custom.entity.request.ChangeOrderStatusRequest;
import com.money.custom.service.OrderItemService;
import com.money.custom.service.OrderService;
import com.money.framework.base.annotation.AddHistoryLog;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl extends BaseServiceImpl implements OrderItemService {

    @Autowired
    OrderItemDao dao;


    @Override
    public OrderItem findById(String id) {
        return dao.findById(id);
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.ORDER_ITEM)
    @Override
    public List<String> addBatch(List<OrderItem> items) {
        dao.addBatch(items);
        return items.stream().map(i -> i.getId().toString()).collect(Collectors.toList());
    }

    @AddHistoryLog(historyLogEntity = HistoryEntityEnum.ORDER_ITEM)
    @Override
    public List<String> changeStatus(ChangeOrderStatusRequest request) {
        dao.changeStatus(request);
        return request.getIds();
    }

}
