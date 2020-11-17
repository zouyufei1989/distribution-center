package com.money.custom.entity.request;

import com.money.custom.entity.History;

import java.util.Map;

public class QueryHistoryRequest extends QueryGridRequestBase {

    private History history = new History();

    @Override
    public Map<String, Object> buildParams() {
        Map<String, Object> params = super.buildParams();
        params.put("history", history);
        return params;
    }

    public History getHistory() {
        return history;
    }

    public void setKey(String key) {
        this.history.setKey(key);
    }

    public void setType(String type) {
        this.history.setType(type);
    }
}
