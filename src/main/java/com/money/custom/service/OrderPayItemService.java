package com.money.custom.service;

import com.money.custom.entity.OrderPayItem;
import com.money.framework.base.service.BaseService;

public interface OrderPayItemService extends BaseService {

    String add(OrderPayItem item);
}
