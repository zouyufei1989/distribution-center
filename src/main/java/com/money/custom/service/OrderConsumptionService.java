package com.money.custom.service;

import com.money.custom.entity.OrderItemConsumption;
import com.money.custom.entity.request.ConsumeRequest;
import com.money.custom.entity.request.QueryOrderConsumptionRequest;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface OrderConsumptionService extends BaseService {

    List<OrderItemConsumption> queryOrderConsumptions(QueryOrderConsumptionRequest request);

    void consume(ConsumeRequest request);


}
