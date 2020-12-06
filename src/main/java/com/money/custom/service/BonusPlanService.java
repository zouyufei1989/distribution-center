package com.money.custom.service;

import com.money.custom.entity.Banner;
import com.money.custom.entity.BonusPlan;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.custom.entity.request.QueryBonusPlanRequest;
import com.money.custom.entity.request.QueryGridRequestBase;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface BonusPlanService extends BaseService {

    List<BonusPlan> selectSearchList(QueryBonusPlanRequest request);

    int selectSearchListCount(QueryBonusPlanRequest request);

    BonusPlan findById(String id);

    String add(BonusPlan item);

    String edit(BonusPlan item);

    List<String> changeStatus(ChangeStatusRequest request);

}
