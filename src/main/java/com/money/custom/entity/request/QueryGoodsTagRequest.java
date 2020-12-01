package com.money.custom.entity.request;

import com.money.custom.entity.GoodsTag;
import com.money.custom.entity.Group;

import java.util.Map;

public class QueryGoodsTagRequest extends QueryGridRequestBase {

    private GoodsTag goodsTag = new GoodsTag();

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("goodsTag", goodsTag);
        return params;
    }

    public void setName(String name) {
        goodsTag.setName(name);
    }

    public void setStatus(Integer status) {
        goodsTag.setStatus(status);
    }

    public void setGroupId(Integer groupId) {
        goodsTag.setGroupId(groupId);
    }
}
