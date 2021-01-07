package com.money.custom.dao;

import com.money.custom.entity.Sms;
import com.money.framework.base.dao.BaseDao;

import java.util.List;

public interface SmsDao extends BaseDao {

    void updateSmsPushResult(Sms sms);

    List<Sms> querySmsToSend(int countLimit);

    Sms querySmsDetail(Integer id);

}
