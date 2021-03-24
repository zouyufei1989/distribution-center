package com.money.test;

import com.money.framework.util.DateUtils;

import java.io.File;

public class Test {

    public static void main(String[] args){
        System.out.println(DateUtils.parse("2020-01-01 01:02:00", "yyyy-MM-dd HH:mm").getTime());
        System.out.println(DateUtils.parse("2020-01-01 01:02:10", "yyyy-MM-dd HH:mm").getTime());
        System.out.println(DateUtils.parse("2020-01-01 01:02:11", "yyyy-MM-dd HH:mm").getTime());
        System.out.println(DateUtils.format(DateUtils.nextNDay(-31*4),"yyyy-MM-dd"));
        System.out.println(DateUtils.nowDate());
    }

}
