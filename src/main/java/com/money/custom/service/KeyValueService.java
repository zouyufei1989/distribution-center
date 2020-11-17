package com.money.custom.service;

import com.money.custom.entity.KeyValue;
import com.money.custom.entity.request.QueryKeyValueRequest;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface KeyValueService extends BaseService {

    List<KeyValue> selectSearchList(QueryKeyValueRequest request);

    int selectSearchListCount(QueryKeyValueRequest request);

    KeyValue findById(String id);

    void add(KeyValue item);

    void edit(KeyValue item);

    KeyValue getValueByKey(String key);
}