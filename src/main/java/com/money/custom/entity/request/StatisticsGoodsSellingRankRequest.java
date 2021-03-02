package com.money.custom.entity.request;

import java.util.Map;

public class StatisticsGoodsSellingRankRequest extends StatisticsBaseRequest {

    private Integer goodsTypeId;

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("goodsTypeId", goodsTypeId);
        return params;
    }

    public void setGoodsTypeId(Integer goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }
}
