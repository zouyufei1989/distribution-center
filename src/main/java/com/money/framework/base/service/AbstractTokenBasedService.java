package com.money.framework.base.service;

import com.alibaba.fastjson.JSON;
import com.money.custom.entity.enums.RedisKeyEnum;
import com.money.framework.base.entity.AbstractTokenRetrievableResponse;
import com.money.framework.base.exception.PandabusSpecException;
import com.money.framework.base.service.impl.BaseServiceImpl;
import com.money.framework.util.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.function.Supplier;

@Component
public abstract class AbstractTokenBasedService extends BaseServiceImpl {

    @Autowired
    RedisUtils redisUtils;

    protected String getAccessToken(boolean forceGetNew, RedisKeyEnum tokenRedisKey) {
        if (forceGetNew) {
            delAccessTokenInRedis(tokenRedisKey);
            getLogger().warn("获取新token! 【每日获取Access_token 次数有限，请尽量使用缓存】");
        }

        String accessToken = tryFromRedis(tokenRedisKey);
        if (StringUtils.isNotEmpty(accessToken)) {
            return accessToken;
        }

        accessToken = getNewAccessToke();
        setAccessTokenToRedis(accessToken, tokenRedisKey);

        return accessToken;
    }

    protected <T extends AbstractTokenRetrievableResponse> T retryIfInvalidToken(Function<Object, T> func, Object param, Supplier tokenBuilder) {
        T response = func.apply(param);
        if (response.isSuccess()) {
            return response;
        }

        if (response.invalidToken()) {
            tokenBuilder.get();
            return func.apply(param);
        }

        throw new PandabusSpecException(JSON.toJSONString(response));
    }

    protected abstract String getNewAccessToke();

    private String tryFromRedis(RedisKeyEnum tokenRedisKey) {
        return redisUtils.getObject(tokenRedisKey.getName(), String.class);
    }

    private void setAccessTokenToRedis(String token, RedisKeyEnum tokenRedisKey) {
        redisUtils.setObject(tokenRedisKey.getName(), token, tokenRedisKey.getTimeout());
    }

    private void delAccessTokenInRedis(RedisKeyEnum tokenRedisKey) {
        redisUtils.delete(tokenRedisKey.getName());
    }


}
