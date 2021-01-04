package com.money.custom.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class StringFormatUtils {

    public static String moneyFen2Yuan(Integer fen) {
        if (Objects.isNull(fen)) {
            return StringUtils.EMPTY;
        }
        return String.format("%.2f", fen / 100.0);
    }

    public static String percent(Integer percent){
        if (Objects.isNull(percent)) {
            return StringUtils.EMPTY;
        }
        return String.format("%.2f", percent / 100.0) + "%";
    }
}
