package com.money.custom.entity.request;

import com.money.custom.entity.GoodsItem;

import java.util.Map;

public class QueryGoodsRequest extends QueryGridRequestBase {

    private GoodsItem goods = new GoodsItem();

    public GoodsItem getGoods() {
        return goods;
    }

    public void setGoods(GoodsItem goods) {
        this.goods = goods;
    }

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("goods", goods);
        return params;
    }
}
