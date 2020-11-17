package com.money.custom.service;

import com.money.custom.entity.ScheduleConfig;
import com.money.custom.entity.request.QueryGridRequestBase;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface ScheduleConfigService extends BaseService {

    List<ScheduleConfig> selectSearchList(QueryGridRequestBase request);

    int selectSearchListCount(QueryGridRequestBase request);

    ScheduleConfig findById(String id);

    void add(ScheduleConfig item);

    void edit(ScheduleConfig item);

    List<ScheduleConfig> runnable(String fullClass);
}