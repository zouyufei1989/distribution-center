package com.money.custom.dao;

import com.money.custom.entity.City;
import com.money.custom.entity.enums.SerialNumberEnum;
import com.money.framework.base.dao.BaseDao;

import java.util.List;
import java.util.Map;

public interface UtilsDao extends BaseDao {

    List<City> selectOpenCities();

    List<Map<String, Object>> execQuerySql(String sql);

    void execUpdateSql(String sql);

    String generateSerialNumber(SerialNumberEnum type);
}
