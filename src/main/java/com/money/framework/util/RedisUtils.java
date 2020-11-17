package com.money.framework.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public final static Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    public <T> T getObject(String key, Class<T> type) {
        return JSON.parseObject(stringRedisTemplate.opsForValue().get(key), type);
    }

    public <T> List<T> getObjectArray(String key, Class<T> type) {
        return JSON.parseArray(stringRedisTemplate.opsForValue().get(key), type);
    }

    public void setObject(String key, Object value, int expireSeconds) {
        stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(value), expireSeconds, TimeUnit.SECONDS);
    }

    public void setObject(String key, Object value) {
        stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(value));
    }

    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    public List<String> multiGet(Set<String> keys) {
        return stringRedisTemplate.opsForValue().multiGet(keys);
    }

    public Set<String> keys(String pattern) {
        return stringRedisTemplate.keys(pattern);
    }

    public boolean setObjectNx(String key, String value) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public void setExpiredSec(String key, int sec) {
        stringRedisTemplate.expire(key, sec, TimeUnit.SECONDS);
    }
}
