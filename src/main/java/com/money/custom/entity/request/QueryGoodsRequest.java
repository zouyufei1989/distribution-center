package com.money.custom.entity.request;

import com.money.custom.entity.Goods;
import com.money.custom.entity.GoodsTag;

import java.util.Map;

public class QueryGoodsRequest extends QueryGridRequestBase {

    private Goods goods = new Goods();

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("goods", goods);
        return params;
    }
}
