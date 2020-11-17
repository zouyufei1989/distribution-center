package com.money.framework.util;

import com.money.framework.base.annotation.IgnoreXss;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Objects;

public class XssUtils {

    final static Logger logger = LoggerFactory.getLogger(XssUtils.class);

    static String[] REGS = {"<", ">", "'", ";", "\"", "\\(", "\\)", "\\\\", "script", "svg", "alert", "confirm", "prompt", "onload", "onmouseover", "onfocus", "onerror", "xss"};

    public static String cleanXSS(String value) {
        if (StringUtils.isEmpty(value)) {
            return value;
        }

        String src = value;
        for (String reg : REGS) {
            value = value.replaceAll(reg, "*");
        }
        logger.debug("xss: {} -> {}:", src, value);
        return value;
    }

    public static void cleanXSS(Object arg) throws IllegalAccessException {
        if (Objects.isNull(arg)) {
            return;
        }
        if (arg instanceof String) {
            arg = XssUtils.cleanXSS(arg.toString());
        } else if (arg instanceof Collection) {
            cleanXSS((Collection) arg);
        } else {
            Class<?> cls = arg.getClass();
            Field[] declaredFields = cls.getDeclaredFields();
            if (Objects.isNull(declaredFields)) {
                return;
            }

            if (cls.isAnnotationPresent(IgnoreXss.class)) {
                return;
            }
            for (Field field : declaredFields) {
                if (field.isAnnotationPresent(IgnoreXss.class)) {
                    continue;
                }
                if (Modifier.isFinal(field.getModifiers())) {
                    continue;
                }
                field.setAccessible(true);
                Object srcValue = field.get(arg);
                if (srcValue instanceof String) {
                    String filterValue = cleanXSS(srcValue.toString());
                    field.set(arg, filterValue);
                }
            }
        }
    }

    public static void cleanXSS(Collection collection) throws IllegalAccessException {
        if (Objects.isNull(collection)) {
            return;
        }
        if (CollectionUtils.isEmpty(collection)) {
            return;
        }

        for (Object arg : collection) {
            cleanXSS(arg);
        }

    }

}
