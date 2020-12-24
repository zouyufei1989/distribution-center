package com.money.test;

import java.io.File;

public class Test {

    public static void main(String[] args){
        String path = "/Deskstop/aaa";
        File folder = new File(path);
        if (!folder.exists()) {
            boolean res = folder.mkdirs();
            System.out.println(res);
        }
    }

}
