package com.money.custom.service.impl;

import com.money.custom.dao.PackageConsumptionDao;
import com.money.custom.entity.dto.PackageConsumption;
import com.money.custom.entity.request.QueryPackageConsumptionRequest;
import com.money.custom.service.PackageConsumptionService;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageConsumptionServiceImpl extends BaseServiceImpl implements PackageConsumptionService {

    @Autowired
    PackageConsumptionDao dao;

    @Override
    public List<PackageConsumption> selectSearchList(QueryPackageConsumptionRequest request) {
        return dao.selectSearchList(request);
    }

    @Override
    public int selectSearchListCount(QueryPackageConsumptionRequest request) {
        return dao.selectSearchListCount(request);
    }

}
