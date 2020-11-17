package com.money.framework.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLCodeUtils {
    final static Logger logger = LoggerFactory.getLogger(URLCodeUtils.class);

    final static String CHARSET = "UTF-8";

    public static String encode(String val) {
        if (StringUtils.isEmpty(val)) {
            return "";
        }
        try {
            return URLEncoder.encode(val, CHARSET);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
        return "encode fail.";
    }

    public static String decode(String val) {
        try {
            return URLDecoder.decode(val, CHARSET);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
        return "decode fail.";
    }
}
