package com.money.custom.entity.response;

import com.alibaba.fastjson.JSONObject;
import com.gexin.rp.sdk.base.IPushResult;

public class PushMessageResponse {
    IPushResult pushResult;
    JSONObject params;

    public PushMessageResponse(IPushResult pushResult, JSONObject params) {
        this.pushResult = pushResult;
        this.params = params;
    }

    public IPushResult getPushResult() {
        return pushResult;
    }

    public JSONObject getParams() {
        return params;
    }
}
