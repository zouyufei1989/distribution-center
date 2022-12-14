package com.money.custom.entity.request;

import com.money.custom.entity.Goods;
import com.money.custom.entity.GoodsItem;
import com.money.custom.entity.enums.GoodsTypeEnum;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class QueryGoodsRequest extends QueryGridRequestBase {

    private GoodsItem goodsItem = new GoodsItem();
    private Goods goods = new Goods();
    private Integer customerGroupId;
    private boolean onlyReservable;

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
        if (this.onlyReservable) {
            params.put("typeList", Arrays.asList(GoodsTypeEnum.ACTIVITY.getValue(), GoodsTypeEnum.PACKAGE.getValue()));
        }
        return params;
    }

    public void setGoodsTagId(Integer tagId) {
        this.goodsItem.setGoodsTagId(tagId);
    }

    public void setGoodsTypeId(GoodsTypeEnum typeId) {
        goods.setType(typeId.getValue());
    }

    public void setOnlyReservable(boolean onlyReservable) {
        this.onlyReservable = onlyReservable;
    }
}
