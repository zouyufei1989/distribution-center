package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;
import com.money.framework.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Map;

public class StatisticsBaseRequest extends OperationalEntity {

    private String month;
    private String startDate;
    private String endDate;

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("groupId", getGroupId());
        if(StringUtils.isNotEmpty(month)){
            params.put("startDate", month + "-01");
            params.put("endDate", DateUtils.getMonthEnd(month));
        }
        if(StringUtils.isNotEmpty(startDate)){
            params.put("startDate", startDate);
        }
        if(StringUtils.isNotEmpty(endDate)){
            params.put("startDate", endDate);
        }

        return params;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setMonth(String month) {
        this.month = month;
    }

}
