package com.money.custom.service;

import com.money.custom.entity.CustomerGroup;
import com.money.custom.entity.request.AssignBonusPlanRequest;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface CustomerGroupService extends BaseService {

    CustomerGroup findById(String id);

    String add(CustomerGroup item);

    String edit(CustomerGroup item);

    List<String> changeStatus(ChangeStatusRequest request);

}
