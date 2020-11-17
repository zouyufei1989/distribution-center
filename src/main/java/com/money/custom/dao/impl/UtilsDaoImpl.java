package com.money.custom.dao.impl;

import com.money.custom.dao.UtilsDao;
import com.money.custom.entity.City;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@SQLContext(nameSpace = "Utils")
public class UtilsDaoImpl extends BaseDaoImpl implements UtilsDao {

    @Override
    public List<City> selectOpenCities()  {
        return selectList("selectOpenCities");
    }

    @Override
    public List<Map<String, Object>> execQuerySql(String sql)  {
        Map<String, Object> params = new HashMap<>();
        params.put("sql", sql);
        return this.selectList("execQuerySql",params);
    }

    @Override
    public void execUpdateSql(String sql)  {
        Map<String, Object> params = new HashMap<>();
        params.put("sql", sql);
        this.update("execUpdateSql",params);
    }
}
