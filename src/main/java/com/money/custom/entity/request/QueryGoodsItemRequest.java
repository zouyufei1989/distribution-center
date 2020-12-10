package com.money.custom.entity.request;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class QueryGoodsItemRequest extends QueryGridRequestBase {

    private Set<String> goodsIdSet = new HashSet<>();
    private Set<String> idSet = new HashSet<>();

    public QueryGoodsItemRequest() {}

    public QueryGoodsItemRequest(String... goodsIds) {
        this.goodsIdSet = Sets.newHashSet(goodsIds);
    }

    public QueryGoodsItemRequest(Set<String> goodsIdSet) {
        this.goodsIdSet = goodsIdSet;
    }

    public QueryGoodsItemRequest(List<String> goodsIdSet) {
        this.goodsIdSet = Sets.newHashSet(goodsIdSet);
    }

    public void setIdSet(Set<String> idSet) {
        this.idSet = idSet;
    }

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("goodsIdSet", goodsIdSet);
        params.put("idSet", idSet);
        return params;
    }
}
