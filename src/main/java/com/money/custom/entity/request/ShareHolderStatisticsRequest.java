package com.money.custom.entity.request;

import com.money.framework.base.entity.OperationalEntity;
import com.money.framework.util.DateUtils;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Map;

public class ShareHolderStatisticsRequest extends OperationalEntity {

    @NotBlank(message = "请选择月份")
    private String month;
    private String sortType;

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("sortType", sortType);
        params.put("startDate", month + "-01");
        params.put("endDate", DateUtils.getMonthEnd(month));
        return params;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}
