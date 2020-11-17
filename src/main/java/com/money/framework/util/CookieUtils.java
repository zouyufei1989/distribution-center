package com.money.framework.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

public class CookieUtils {

    public static final String COOKIE_USER = "cookie_user";
    public static final String COOKIE_GUID = "cookie_guid";

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(CookieUtils.class);

    private CookieUtils() {}

    public static void setCookie(HttpServletResponse response, String key, Object value) {
        logger.debug(String.format("set cookie: %s - %s", key, JSON.toJSONString(value)));
        Cookie cookie = new Cookie(key, JSON.toJSONString(value));
        cookie.setMaxAge(60 * 30);
        response.addCookie(cookie);
    }


    public static <T> T getCookie(HttpServletRequest request, String key, Class<T> cls) {
        for (Cookie cookie : request.getCookies()) {
            if (StringUtils.equals(key, cookie.getName())) {
                logger.debug(String.format("get cookie: %s - %s", key, cookie.getValue()));
                return JSON.parseObject(cookie.getValue(), cls);
            }
        }
        logger.debug(String.format("get cookie: %s - %s", key, ""));
        return null;
    }

    public static String getCookie(HttpServletRequest request, String key) {
        if (Objects.isNull(request.getCookies())) {
            return null;
        }
        for (Cookie cookie : request.getCookies()) {
            if (StringUtils.equals(key, cookie.getName())) {
                logger.debug(String.format("get cookie: %s - %s", key, cookie.getValue()));
                return cookie.getValue();
            }
        }
        logger.debug(String.format("get cookie: %s - %s", key, ""));
        return null;
    }

    public static <T> List<T> getCookieArr(HttpServletRequest request, String key, Class<T> cls) {
        for (Cookie cookie : request.getCookies()) {
            if (StringUtils.equals(key, cookie.getName())) {
                logger.debug(String.format("get cookie: %s - %s", key, cookie.getValue()));
                return JSON.parseArray(cookie.getValue(), cls);
            }
        }
        logger.debug(String.format("get cookie: %s - %s", key, ""));
        return null;
    }

}

