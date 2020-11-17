package com.money.custom.entity.request;

import java.util.Map;

public class QueryKeyValueRequest extends QueryGridRequestBase {

    private String key;

    public QueryKeyValueRequest() {}

    public QueryKeyValueRequest(String key) {this.key = key;}


    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("key", key);
        return params;
    }


    public void setKey(String key) {
        this.key = key;
    }
}
