package com.money.custom.service;

import com.money.custom.entity.GroupReservationPeriod;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.custom.entity.request.QueryGridRequestBase;
import com.money.custom.entity.request.SaveGroupReservationPeriodsRequest;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface GroupReservationPeriodService extends BaseService {

    List<GroupReservationPeriod> selectSearchList(QueryGridRequestBase request);

    int selectSearchListCount(QueryGridRequestBase request);

    List<String> add(SaveGroupReservationPeriodsRequest request);

}
