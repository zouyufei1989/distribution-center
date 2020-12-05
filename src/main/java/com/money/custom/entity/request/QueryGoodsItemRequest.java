package com.money.custom.entity.request;

import org.assertj.core.util.Sets;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class QueryGoodsItemRequest extends QueryGridRequestBase {

    private Set<String> goodsIdSet = new HashSet<>();

    public QueryGoodsItemRequest() {}

    public QueryGoodsItemRequest(String... goodsIds) {
        this.goodsIdSet = Sets.newLinkedHashSet(goodsIds);
    }

    public QueryGoodsItemRequest(Set<String> goodsIdSet) {
        this.goodsIdSet = goodsIdSet;
    }

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("goodsIdSet", goodsIdSet);
        return params;
    }
}
