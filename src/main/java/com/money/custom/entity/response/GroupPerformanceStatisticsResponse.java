package com.money.custom.entity.response;

import com.money.custom.entity.dto.GroupPerformance;
import com.money.custom.entity.enums.StatisticsTypeEnum;
import com.money.custom.entity.request.StatisticsGroupPerformanceRequest;
import com.money.framework.util.DateUtils;
import org.assertj.core.util.Sets;

import java.util.*;

public class GroupPerformanceStatisticsResponse {

    private Map<String, Long> result = new TreeMap<>();

    public GroupPerformanceStatisticsResponse(List<GroupPerformance> items, StatisticsGroupPerformanceRequest request) {
        for (GroupPerformance gp : items) {
            String key = getKey(gp, request.getType());
            if (!result.containsKey(key)) {
                result.put(key, 0L);
            }
            result.put(key, result.get(key) + gp.getSumMoney());
        }
    }

    public Set<String> getXaxisData() {
        if (result.size() > 0) {
            return result.keySet();
        }
        return Sets.newHashSet();
    }

    public Collection<Long> getYaxisData() {
        if (result.size() > 0) {
            return result.values();
        }
        return Sets.newHashSet();
    }


    private String getKey(GroupPerformance item, Integer type) {
        if (type.equals(StatisticsTypeEnum.BY_GROUP.getValue())) {
            return item.getGroupId().toString() + "@" + item.getGroupName();
        }
        if (type.equals(StatisticsTypeEnum.BY_DAY.getValue())) {
            return item.getDate();
        }
        if (type.equals(StatisticsTypeEnum.BY_MONTH.getValue())) {
            return DateUtils.format(item.getDate(), "yyyy-MM-dd", "yyyy-MM");
        }
        return null;
    }

}
