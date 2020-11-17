package com.money.custom.service.impl;

import com.money.custom.dao.KeyValueDao;
import com.money.custom.entity.KeyValue;
import com.money.custom.entity.request.QueryKeyValueRequest;
import com.money.custom.service.KeyValueService;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class KeyValueServiceImp extends BaseServiceImpl implements KeyValueService {

    @Autowired
    KeyValueDao dao;

    @Override
    public List<KeyValue> selectSearchList(QueryKeyValueRequest request)  {
        return dao.selectSearchList(request);
    }

    @Override
    public int selectSearchListCount(QueryKeyValueRequest request)  {
        return dao.selectSearchListCount(request);
    }

    @Override
    public KeyValue findById(String id)  {
        return dao.findById(id);
    }

    @Override
    public void add(KeyValue item)  {
        dao.add(item);
    }

    @Override
    public void edit(KeyValue item)  {
        dao.edit(item);
    }

    @Override
    public KeyValue getValueByKey(String key) {
        QueryKeyValueRequest queryKeyValueRequest = new QueryKeyValueRequest(key);
        Optional<KeyValue> valueOptional = selectSearchList(queryKeyValueRequest).stream().filter(i -> StringUtils.equals(key, i.getKey())).findFirst();
        Assert.isTrue(valueOptional.isPresent(), "key不存在");
        if (valueOptional.isPresent()) {
            return valueOptional.get();
        }
        return null;
    }
}
