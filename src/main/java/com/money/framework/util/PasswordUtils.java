package com.money.framework.util;

import java.util.regex.Pattern;

public class PasswordUtils {


    public static String validatePassword(String password) {

        int count = 0;
        int specialChar = 0;
        if (Pattern.matches(".*[a-zA-Z]+.*", password)) {
            count++;
        }

        if (Pattern.matches(".*[0-9]+.*", password)) {
            count++;
        }

        if (Pattern.matches(".*[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]+.*", password)) {
            specialChar = 1;
        }

        if (count == 1) {
            return "请使用混合字符作为密码";
        }

        if (specialChar == 1 && count == 2 && password.length() < 8) {
            return "三种字符密码长度至少8位";
        }

        if (specialChar != 1 && count == 2 && password.length() < 10) {
            return "两种字符密码长度至少10位";
        }

        if (sameStr(password)) {
            return "请不要包含连续相同字符";
        }

        if (LxStr(password)) {
            return "请不要包含连续相邻字符";
        }

        return "";


    }

    static boolean sameStr(String str) {
        char[] arr = str.toCharArray();
        for (int i = 1; i < arr.length - 1; i++) {
            int firstIndex = arr[i - 1];
            int secondIndex = arr[i];
            int thirdIndex = arr[i + 1];
            if ((thirdIndex - secondIndex == 0) && (secondIndex - firstIndex == 0)) {
                return true;
            }
        }
        return false;
    }

    static boolean LxStr(String str) {
        char[] arr = str.toCharArray();
        for (int i = 1; i < arr.length - 1; i++) {
            int firstIndex = arr[i - 1];
            int secondIndex = arr[i];
            int thirdIndex = arr[i + 1];
            if ((thirdIndex - secondIndex == 1) && (secondIndex - firstIndex == 1)) {
                return true;
            }
        }
        return false;
    }

}
