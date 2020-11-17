package com.money.framework.util;

import java.util.List;

public interface IRedisUtils {

    <T> T getObject(String key, Class<T> type);

    <T> List<T> getObjectArray(String key, Class<T> type);

    void setObject(String key, Object value, int expiretime);

    void delkeyObject(String key);

}
