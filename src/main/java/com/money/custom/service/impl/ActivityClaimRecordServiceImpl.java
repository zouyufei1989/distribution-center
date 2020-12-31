package com.money.custom.service.impl;

import com.money.custom.dao.ActivityClaimRecordDao;
import com.money.custom.entity.ActivityClaimRecord;
import com.money.custom.service.ActivityClaimRecordService;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityClaimRecordServiceImpl extends BaseServiceImpl implements ActivityClaimRecordService {

    @Autowired
    ActivityClaimRecordDao dao;

    @Override
    public String add(ActivityClaimRecord item) {
        dao.add(item);
        return item.getId().toString();
    }
}
