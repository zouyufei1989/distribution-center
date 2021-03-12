package com.money.custom.entity.request;

import java.util.Map;

public class QueryGroupReservationPeriodRequest extends QueryGridRequestBase {

    private Integer goodsId;

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("goodsId", goodsId);
        return params;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
}
