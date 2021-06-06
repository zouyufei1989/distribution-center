package com.money.framework.util;

import org.slf4j.Logger;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncrytUtils {

    public final static Logger logger = org.slf4j.LoggerFactory.getLogger(EncrytUtils.class);

    public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception {
        byte[] data = encryptKey.getBytes("UTF-8");
        // 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, "HmacSHA1");
        // 生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance("HmacSHA1");
        // 用给定密钥初始化 Mac 对象
        mac.init(secretKey);

        byte[] text = encryptText.getBytes("UTF-8");
        // 完成 Mac 操作
        return mac.doFinal(text);
    }
}
