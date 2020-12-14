package com.money.custom.entity.request;

import com.money.custom.entity.Goods;
import com.money.custom.entity.GoodsItem;

import java.util.Map;

public class QueryGoodsRequest extends QueryGridRequestBase {

    private GoodsItem goodsItem = new GoodsItem();
    private Goods goods = new Goods();
    private Integer customerGroupId;

    public GoodsItem getGoodsItem() {
        return goodsItem;
    }

    public void setGoodsItem(GoodsItem goodsItem) {
        this.goodsItem = goodsItem;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Integer getCustomerGroupId() {
        return customerGroupId;
    }

    public void setCustomerGroupId(Integer customerGroupId) {
        this.customerGroupId = customerGroupId;
    }

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("goodsItem", goodsItem);
        params.put("goods", goods);
        return params;
    }
}
