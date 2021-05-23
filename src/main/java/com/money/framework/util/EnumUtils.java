package com.money.framework.util;

import com.money.custom.entity.enums.IEnumKeyValue;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class EnumUtils {

    final static Logger logger = LoggerFactory.getLogger(EnumUtils.class);

    /**
     * 将枚举转换为Map  Entry<value,name>
     *
     * @param clazz
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    public static Map<String, String> getEnumEntriesVN(Class<? extends IEnumKeyValue> clazz) {
        Map<String, String> result = new LinkedHashMap<>();

        Map<String, String> nvMap = getEnumEntriesNV(clazz);
        for (Map.Entry<String, String> kv : nvMap.entrySet()) {
            result.put(kv.getValue(), kv.getKey());
        }

        return result;
    }

    /**
     * 将枚举转换为Map  Entry<name,value>
     *
     * @param clazz
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    public static Map<String, String> getEnumEntriesNV(Class<? extends IEnumKeyValue> clazz) {
        Map<String, String> result = new LinkedHashMap<>();

        try {
            Method getValue = clazz.getDeclaredMethod("getValue");
            Method getName = clazz.getDeclaredMethod("getName");
            Method availableForMap = clazz.getMethod("availableForMap");

            //得到enum的所有实例
            Object[] objs = clazz.getEnumConstants();
            for (Object obj : objs) {
                if ((boolean) availableForMap.invoke(obj)) {
                    result.put(getName.invoke(obj).toString(), getValue.invoke(obj).toString());
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
        return result;
    }

    /**
     * 根据name查询枚举
     *
     * @param clazz
     * @param name
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Optional<IEnumKeyValue> getByName(Class<? extends IEnumKeyValue> clazz, String name) {
        try {
            Method getName = clazz.getMethod("getName");

            Object[] objs = clazz.getEnumConstants();
            for (Object obj : objs) {
                if (StringUtils.equals(getName.invoke(obj).toString(), name)) {
                    return Optional.of((IEnumKeyValue) obj);
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        throw new IllegalArgumentException(name + "不存在");
    }

    /**
     * 根据name查询枚举
     *
     * @param clazz
     * @param name
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Object getValueByName(Class<? extends IEnumKeyValue> clazz, String name) {
        Optional<IEnumKeyValue> opt = getByName(clazz, name);
        return opt.isPresent() ? opt.get().getValue() : IEnumKeyValue.DEFAULT_VALUE;
    }


    /**
     * 根据key 查询枚举
     *
     * @param clazz
     * @param value
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Optional<IEnumKeyValue> getByValue(Class<? extends IEnumKeyValue> clazz, Object value) {
        Objects.requireNonNull(value);

        try {
            Method getValue = clazz.getMethod("getValue");
            Object[] objs = clazz.getEnumConstants();
            for (Object obj : objs) {
                if (Objects.nonNull(getValue.invoke(obj)) && getValue.invoke(obj).equals(value)) {
                    return Optional.of((IEnumKeyValue) obj);
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        throw new IllegalArgumentException(value + "不存在");
    }

    public static String getNameByValue(Class<? extends IEnumKeyValue> clazz, Object value) {
        if (value == null) {
            return "";
        }
        Optional<IEnumKeyValue> opt = null;
        opt = getByValue(clazz, value);
        return opt.isPresent() ? opt.get().getName() : IEnumKeyValue.DEFAULT_NAME;
    }

}
