package com.money.framework.util;

import java.util.Map;

public class MapReader {

    public static String getString(Map<String, Object> row, String key) {
        Object val = row.get(key);
        if (val == null) {
            return "";
        }

        return val.toString();
    }

    public static Long getLong(Map<String, Object> row, String key) {
        Object val = row.get(key);
        if (val == null) {
            return 0L;
        }

        return (Long) val;
    }
}
