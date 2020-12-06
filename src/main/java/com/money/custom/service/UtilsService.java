package com.money.custom.service;

import com.money.custom.entity.*;
import com.money.custom.entity.enums.SerialNumberEnum;
import com.money.custom.entity.request.*;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface UtilsService extends BaseService {

    List<City> selectOpenCities();

    List<Role> selectRoles();

    List<ScheduleConfig> selectScheduleConfig();

    String generateSerialNumber(SerialNumberEnum type);
}
