package com.money.custom.service.impl;

import com.google.common.collect.Sets;
import com.money.custom.dao.ScheduleConfigDao;
import com.money.custom.entity.ScheduleConfig;
import com.money.custom.entity.enums.RedisKeyEnum;
import com.money.custom.entity.request.QueryGridRequestBase;
import com.money.custom.service.ScheduleConfigService;
import com.money.custom.service.UtilsService;
import com.money.framework.base.annotation.RedisDel;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScheduleConfigServiceImp extends BaseServiceImpl implements ScheduleConfigService {

    @Autowired
    ScheduleConfigDao dao;
    @Autowired
    UtilsService utilsService;

    @Override
    public List<ScheduleConfig> selectSearchList(QueryGridRequestBase request) {
        return dao.selectSearchList(request);
    }

    @Override
    public int selectSearchListCount(QueryGridRequestBase request) {
        return dao.selectSearchListCount(request);
    }

    @Override
    public ScheduleConfig findById(String id) {
        return dao.findById(id);
    }

    @RedisDel(redisKey = RedisKeyEnum.SCHEDULE_CONFIG)
    @Override
    public void add(ScheduleConfig item) {
        dao.add(item);
    }

    @RedisDel(redisKey = RedisKeyEnum.SCHEDULE_CONFIG)
    @Override
    public void edit(ScheduleConfig item) {
        dao.edit(item);
    }

    @Override
    public List<ScheduleConfig> runnable(String fullClass) {
        List<ScheduleConfig> scheduleConfigs = utilsService.selectScheduleConfig();
        if (CollectionUtils.isEmpty(scheduleConfigs)) {
            getLogger().info("无任务配置");
            return null;
        }

        scheduleConfigs = scheduleConfigs.stream().filter(i -> StringUtils.equals(i.getFullClass(), fullClass)).collect(Collectors.toList());
        if (org.apache.commons.collections4.CollectionUtils.isEmpty(scheduleConfigs)) {
            getLogger().info("{} 配置不存在", fullClass);
            return null;
        }

        Iterator<ScheduleConfig> iterator = scheduleConfigs.iterator();
        while (iterator.hasNext()) {
            ScheduleConfig config = iterator.next();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());

            boolean result = timeUnitMatch(config.getYear(), Calendar.YEAR, calendar, fullClass);
            result = result && timeUnitMatch(config.getMonth(), Calendar.MONTH, calendar, fullClass);
            result = result && timeUnitMatch(config.getDay(), Calendar.DAY_OF_MONTH, calendar, fullClass);
            result = result && timeUnitMatch(config.getDay(), Calendar.DAY_OF_WEEK, calendar, fullClass);
            result = result && timeUnitMatch(config.getHour(), Calendar.HOUR_OF_DAY, calendar, fullClass);
            result = result && timeUnitMatch(config.getMinute(), Calendar.MINUTE, calendar, fullClass);

            if (!result) {
                iterator.remove();
            }
        }

        return scheduleConfigs;
    }


    boolean timeUnitMatch(String value, int timeUnit, Calendar calendar, String fullClass) {
        if (StringUtils.equals("*", value) || StringUtils.isEmpty(value)) {
            return true;
        }

        HashSet<String> configSet = Sets.newHashSet(value.split("&"));

        int current = calendar.get(timeUnit);
        if (timeUnit == Calendar.MONTH) {
            current++;
        }
        if (!configSet.contains(String.valueOf(current))) {
            getLogger().info("{} not contains {}, {} stop", configSet, current, fullClass);
            return false;
        }
        return true;
    }


}
