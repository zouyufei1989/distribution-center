package com.money.custom.service;

import com.money.custom.entity.OrderConsumption;
import com.money.custom.entity.OrderItemConsumption;
import com.money.custom.entity.request.ConsumeRequest;
import com.money.custom.entity.request.QueryOrderConsumptionRequest;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface OrderConsumptionService extends BaseService {

    List<OrderConsumption> selectSearchList(QueryOrderConsumptionRequest request);

    int selectSearchListCount(QueryOrderConsumptionRequest request);

    List<OrderItemConsumption> queryOrderConsumptions(QueryOrderConsumptionRequest request);

    void consume(ConsumeRequest request);


}
