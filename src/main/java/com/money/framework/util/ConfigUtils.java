package com.money.framework.util;

import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

/**
 * Created by Zouyufei on 2017年12月20日.
 */
public class ConfigUtils {

    static final String DEFAULT_CONFIG_NAME = "webserver/props/config";

    public static String getConfig(String configPath, String propertyName) {
        ResourceBundle resource = ResourceBundle.getBundle(configPath);
        Enumeration<String> keys = resource.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            if (propertyName.equals(key)) {
                return resource.getString(key);
            }
        }
        throw new NoSuchElementException(propertyName);
    }

    /**
     * use default property file 'webserver/props/config.properties'
     *
     * @param propertyName
     * @return
     */
    public static String getConfig(String propertyName) {
        return getConfig(DEFAULT_CONFIG_NAME, propertyName);
    }

    public static int getConfigInt(String propertyName) {
        return Integer.parseInt(getConfig(DEFAULT_CONFIG_NAME, propertyName));
    }
}
