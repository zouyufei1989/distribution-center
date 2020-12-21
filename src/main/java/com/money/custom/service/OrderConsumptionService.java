package com.money.custom.service;

import com.money.custom.entity.request.ConsumeRequest;
import com.money.framework.base.service.BaseService;

public interface OrderConsumptionService extends BaseService {

    void consume(ConsumeRequest request);
}
