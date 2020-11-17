package com.money.custom.aspectj;

import com.money.custom.entity.enums.RedisKeyEnum;
import com.money.framework.base.annotation.RedisDel;
import com.money.framework.util.RedisUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RedisControlAspectJ {

    final static Logger logger = LoggerFactory.getLogger(RedisControlAspectJ.class);

    @Autowired
    RedisUtils redisUtils;

    @Pointcut("execution(* com.money.custom.service.impl.*.*(..))")
    public void doing() {
    }

    @AfterReturning("doing() && @annotation(redisDel)")
    public void process(JoinPoint point, RedisDel redisDel) {

        for (RedisKeyEnum key : redisDel.redisKey()) {
            redisDeleteKey(key);
        }

    }

    void redisDeleteKey(RedisKeyEnum keyEnum) {
        String key = keyEnum.getName();
        redisUtils.delete(key);

        logger.info("delete redis ->{}", key);
    }

}