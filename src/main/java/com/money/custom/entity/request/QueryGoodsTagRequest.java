package com.money.custom.entity.request;

import com.money.custom.entity.GoodsTag;
import com.money.custom.entity.Group;

import java.util.Map;

public class QueryGoodsTagRequest extends QueryGridRequestBase {

    private GoodsTag goodsTag = new GoodsTag();

    public GoodsTag getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(GoodsTag goodsTag) {
        this.goodsTag = goodsTag;
    }

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("goodsTag", goodsTag);
        return params;
    }
}
