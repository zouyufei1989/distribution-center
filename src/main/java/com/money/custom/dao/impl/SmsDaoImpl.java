package com.money.custom.dao.impl;

import com.money.custom.dao.SmsDao;
import com.money.custom.entity.Sms;
import com.money.framework.base.annotation.SQLContext;
import com.money.framework.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SQLContext(nameSpace = "Sms")
public class SmsDaoImpl extends BaseDaoImpl implements SmsDao {


    @Override
    public void updateSmsPushResult(Sms sms) {
        update("updateSmsPushResult", sms);
    }

    @Override
    public List<Sms> querySmsToSend(int countLimit) {
        return selectList("querySmsToSend",countLimit);
    }

    @Override
    public Sms querySmsDetail(Integer id) {
        return selectOne("querySmsDetail", id);
    }
}
