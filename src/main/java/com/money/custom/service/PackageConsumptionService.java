package com.money.custom.service;

import com.money.custom.entity.dto.PackageConsumption;
import com.money.custom.entity.request.QueryPackageConsumptionRequest;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface PackageConsumptionService extends BaseService {

    List<PackageConsumption> selectSearchList(QueryPackageConsumptionRequest request);

    int selectSearchListCount(QueryPackageConsumptionRequest request);

}
