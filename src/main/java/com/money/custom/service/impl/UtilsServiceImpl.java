package com.money.custom.service.impl;

import com.money.custom.dao.GoodsTagDao;
import com.money.custom.dao.GroupDao;
import com.money.custom.dao.RoleDao;
import com.money.custom.dao.UtilsDao;
import com.money.custom.entity.*;
import com.money.custom.entity.enums.RedisKeyEnum;
import com.money.custom.entity.enums.SerialNumberEnum;
import com.money.custom.entity.request.QueryGoodsTagRequest;
import com.money.custom.entity.request.QueryGridRequestBase;
import com.money.custom.entity.request.QueryGroupRequest;
import com.money.custom.entity.request.QueryRoleRequest;
import com.money.custom.service.ScheduleConfigService;
import com.money.custom.service.UtilsService;
import com.money.framework.base.entity.OperationalEntity;
import com.money.framework.base.service.impl.BaseServiceImpl;
import com.money.framework.util.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UtilsServiceImpl extends BaseServiceImpl implements UtilsService {

    final static Logger logger = LoggerFactory.getLogger(UtilsServiceImpl.class);

    @Autowired
    UtilsDao dao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    GroupDao groupDao;
    @Autowired
    GoodsTagDao goodsTagDao;
    @Autowired
    ScheduleConfigService scheduleConfigService;
    @Autowired
    RedisUtils redisUtils;


    @Override
    public List<City> selectOpenCities() {
        return queryRedisItems(City.class, null, RedisKeyEnum.CITIES, uselessParam -> {
            try {
                return dao.selectOpenCities();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            return new ArrayList<>();
        });
    }

    @Override
    public List<Role> selectRoles() {
        return roleDao.selectSearchList(new QueryRoleRequest());
    }

    @Override
    public List<ScheduleConfig> selectScheduleConfig() {
        return queryRedisItems(ScheduleConfig.class, null, RedisKeyEnum.SCHEDULE_CONFIG, uselessParam -> {
            try {
                return scheduleConfigService.selectSearchList(new QueryGridRequestBase());
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            return new ArrayList<>();
        });
    }

    @Override
    public String generateSerialNumber(SerialNumberEnum type) {
        return dao.generateSerialNumber(type);
    }


    /**
     * @param clazz        返回的Item 类型
     * @param groupId      公司id
     * @param redisKeyEnum redis中存储的Key
     * @param queryDbFunc  如果redis不存在，DB中查询的方法
     * @param <T>
     * @return
     */
    <T extends OperationalEntity> List<T> queryRedisItems(Class<T> clazz, Integer groupId, RedisKeyEnum redisKeyEnum, Function<Object, List<T>> queryDbFunc) {
        String key = redisKeyEnum.getName();
        logger.info("query from redis ->{}", key);

        List<T> redisItems = redisUtils.getObjectArray(key, clazz);
        if (CollectionUtils.isEmpty(redisItems)) {
            logger.info("update redis ->{}", key);
            redisItems = queryDbFunc.apply(null);
            redisUtils.setObject(key, redisItems, redisKeyEnum.getTimeout());
        }

        Stream<T> stream = redisItems.stream();
        if (groupId != null) {
            logger.info("filter {} by groupId = {}", key, groupId);
            stream = stream.filter(i -> groupId.equals(i.getGroupId()));
        }
        return stream.collect(Collectors.toList());

    }
}
