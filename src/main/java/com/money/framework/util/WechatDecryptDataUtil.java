package com.money.framework.util;

import com.money.framework.base.exception.CustomSpecException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;

public class WechatDecryptDataUtil {

    final static Logger logger = LoggerFactory.getLogger(WechatDecryptDataUtil.class);

    public static void main(String[] args) {
        String result = decryptData(
                "5SMttJCNv4mT2m3VRgbFgfvRuFpJ3C4W59EjCTV8KKLmgc5Yyk9w0tyNoXKnmK2k604eFmENseEjkPmDaUo6b2MJDQxJ45xsCXHNz+vPpElSJ3yB3uqrWHqr01euhKHWZlRTsMusKQBQ4gFviAoNMyjfGNmeyNc/8icd9FdENfKby29SR/ITZL0j84D6TTp11wi/f7kEN9zcL4j7uiLewg==",
                "ithu4u6HFWLLgacFidO9Gg==",
                "OsrmLBPHeBUwSN9JcSgSpg=="
        );
        System.out.println("result = " + result);
    }

    public static String decryptData(String encryptDataB64, String sessionKeyB64, String ivB64) {
        logger.info("encryptDataB64:" + encryptDataB64);
        logger.info("sessionKeyB64:" + sessionKeyB64);
        logger.info("ivB64:" + ivB64);
        return new String(
                decryptOfDiyIV(
                        Base64.decode(encryptDataB64),
                        Base64.decode(sessionKeyB64),
                        Base64.decode(ivB64)
                )
        );
    }

    private static final String KEY_ALGORITHM = "AES";
    private static final String ALGORITHM_STR = "AES/CBC/PKCS7Padding";
    private static Key key;
    private static Cipher cipher;

    private static void init(byte[] keyBytes) {
        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        int base = 16;
        if (keyBytes.length % base != 0) {
            int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
            keyBytes = temp;
        }
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        // 转化成JAVA的密钥格式
        key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        try {
            // 初始化cipher
            cipher = Cipher.getInstance(ALGORITHM_STR, "BC");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解密方法
     *
     * @param encryptedData 要解密的字符串
     * @param keyBytes      解密密钥
     * @param ivs           自定义对称解密算法初始向量 iv
     * @return 解密后的字节数组
     */
    private static byte[] decryptOfDiyIV(byte[] encryptedData, byte[] keyBytes, byte[] ivs) {
        byte[] encryptedText = null;
        init(keyBytes);
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivs));
            encryptedText = cipher.doFinal(encryptedData);
        } catch (Exception e) {
            throw new IllegalArgumentException("解密sessionKey失败:" + e.getMessage(), e);
        }
        return encryptedText;
    }
}
