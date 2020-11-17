package com.money.custom.task;

import com.money.custom.entity.Consts;
import com.money.framework.base.entity.OperationalEntity;
import com.money.framework.util.DateUtils;
import com.money.framework.util.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class TaskBase {

    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass().getName());
    }

    @Autowired
    RedisUtils redisUtils;


    OperationalEntity getOperationInfo() {
        OperationalEntity entity = new OperationalEntity();
        entity.setUpdater(this.getClass().getSimpleName());
        entity.setCreator(this.getClass().getSimpleName());
        return entity;
    }

    private <T extends TaskBase> boolean validateExecutorByRedis(T cls, String frequency) {
        String key = Consts.GROUP_NAME + "." + cls.getClass().toString() + DateUtils.format(new Date(), frequency);
        if (redisUtils.setObjectNx(key, DateUtils.now())) {
            redisUtils.setExpiredSec(key, 60 * 5);
            return true;
        }

        return false;
    }

    protected <T extends TaskBase> boolean validateExecutorPerDay(T cls) {
        return validateExecutorByRedis(cls, "yyyy-MM-dd");
    }

    <T extends TaskBase> boolean validateExecutorPerMinute(T cls) {
        return validateExecutorByRedis(cls, "yyyy-MM-dd_HH:mm");
    }

}
