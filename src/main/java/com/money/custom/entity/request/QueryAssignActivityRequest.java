package com.money.custom.entity.request;

import java.util.Map;

public class QueryAssignActivityRequest extends QueryGridRequestBase {

    private String startDate;
    private String endDate;
    private String goodsName;
    private String creatorName;


    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        params.put("creatorName", creatorName);
        params.put("goodsName", goodsName);
        return params;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
