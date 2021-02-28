package com.money.custom.entity.request;

import com.money.framework.util.DateUtils;

import java.util.Map;

public class ShareHolderStatisticsRequest extends StatisticsBaseRequest {

    private String sortType;

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("sortType", sortType);
        return params;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}
