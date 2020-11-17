package com.money.framework.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtils {

    //生成秘钥对
    public static KeyPair getKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }

    //获取公钥(Base64编码)
    static String getPublicKey(KeyPair keyPair) {
        PublicKey publicKey = keyPair.getPublic();
        byte[] bytes = publicKey.getEncoded();
        return new BASE64Encoder().encode((bytes));
    }

    //获取私钥(Base64编码)
    static String getPrivateKey(KeyPair keyPair) {
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] bytes = privateKey.getEncoded();
        return new BASE64Encoder().encode((bytes));
    }

    //将Base64编码后的公钥转换成PublicKey对象
    static PublicKey string2PublicKey(String pubStr) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = new BASE64Decoder().decodeBuffer((pubStr));
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    //将Base64编码后的私钥转换成PrivateKey对象
    static PrivateKey string2PrivateKey(String priStr) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = new BASE64Decoder().decodeBuffer((priStr));
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    //公钥加密
    public static String publicEncrypt(String content, String publicKeyStr) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        PublicKey publicKey = RSAUtils.string2PublicKey(publicKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(content.getBytes());
        return new BASE64Encoder().encode(bytes);
    }

    //私钥解密
    public static String privateDecrypt(String content, String privateKeyStr) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        PrivateKey privateKey = RSAUtils.string2PrivateKey(privateKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(new BASE64Decoder().decodeBuffer(content));
        return new String(bytes);
    }


        public static void main(String[] args) {
        try {
            //===============生成公钥和私钥，公钥传给客户端，私钥服务端保留==================
            KeyPair keyPair = RSAUtils.getKeyPair();
            String publicKeyStr = RSAUtils.getPublicKey(keyPair);
            String privateKeyStr = RSAUtils.getPrivateKey(keyPair);
            System.out.println("RSA公钥Base64编码:" + publicKeyStr);
            System.out.println("RSA私钥Base64编码:" + privateKeyStr);

            //=================开始加密=================
            String message = "panda";
            //用公钥加密
            String publicEncrypt = RSAUtils.publicEncrypt(message, publicKeyStr);
            System.out.println("公钥加密并Base64编码的结果：" + publicEncrypt);


            //===================开始解密================
            //用私钥解密
            String privateDecrypt = RSAUtils.privateDecrypt(publicEncrypt, privateKeyStr);
            //解密后的明文
            System.out.println("解密后的明文: " + privateDecrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        try {
//            String publicKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3FICUgS6QyAcN3akh7sJ" +
//                    "mTVNYaM+9CAnvZCYCZC5Iwku2BkypQL/WmuOHCSZYGP64PY5Jv+o5jZIp/Jo33V7" +
//                    "mYMTzCU/j1HEmKNABloFg+iWK9iWNTsADn9eqWTta0/Iirdn2Vzdwthn30Tv+Fba" +
//                    "YWFjmsYjTLp7IkPnKolSjU3+icwA4MMDoVdzC0KKMaAsF4w8+EkCm0o3PvInCfLG" +
//                    "KSL0n4tZ7bC6h0OAf8NlOKQnbEktIy34k7c5FinD2YFTncVt3E+FfNWV0+aa49Wa" +
//                    "k4I/F+a7HgXZT+lqVT85x8W3AjsluY9pE2X1bIzrrW0jmLdc3AZ9Vk2CErB4a8ZR" +
//                    "VQIDAQAB";
//            String privateKeyStr = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCZZ/dzp/65OmNEdstjn5bchfMs" +
//                    "bAkBJ5HT9W32kAzo3qlYPJSu0ojSlRdtLxJOU2+asU2q6DH6CeUMzi2krWX4qSnuBM6cdJDrF3Rs" +
//                    "61a5AUlpFE9oCft93X0Ve0dTb2fCrB1PSGmgfq4arFpkBiVm/XWv2wzyqjEVpx3pcgIJ/6gOxowv" +
//                    "Rn0du0aEb5b/STL/kEOUFFws8Ed8e+bYxVbu9V7LZeogzVjVH4VsA9Uv9gOKooosFP/8BlAiSUuV" +
//                    "Ea17zwCRuPtBOSAEgfWn3jJX8bh9itbi8QoKaK5PTNMVJVUg3R7O4xq098qGjHK3ygOS0fzNHn4g" +
//                    "WannSWdklcgTAgMBAAECggEACQANh46SpkFjs4+YrTaevj9pnfCUsqmkp5/dnLqk10DBBLlBK+SU" +
//                    "PG6c2j4Jv+irQpMmcGGBFR9hfdfW27OEqQsbt6g2iv1Xx0MwBHfmNaJ6sRyM8Bit1ZuaDBmYfK+r" +
//                    "kcNRUOZ5s/3arxvvjbDCnwE911xtrQsJKDEO0zRqJDXxXt/Rfw/nknkvVMcdybsHBG+NWCT1NXKw" +
//                    "Ia1rrX783jSzh3BAbjRNV03yqyajXNpJ7Gs2RuLNodJevzEbO/8DH9F+8GxaYQitUu3/BFtclwNY" +
//                    "KmNy33p1JFtSiPtROvqQ+AoRMQ9miou/KW9RzYjJqE92hg6s07GDSgNTnHQsuQKBgQDKlWDei7IT" +
//                    "B3c0k/u1+0WsmfppxKPdnwBECJ2ute2RH96Alx8OgrGvlas+6wzQ0S7vT+yzSxUvUlYTNZEfx5qK" +
//                    "zWtHyJWz78jg83ZWvgBhcsOCzME66N8n+xYq1i42oao4idACrOSt37ctRxkC6rJyDvbxxl4zqYIa" +
//                    "DraC69DcdwKBgQDB2w6zPVpQfr79XlitOgW9KUrle2U4BOw/FXzKBLRT8b1V59uKKPHyHNA5YYTM" +
//                    "WAJ7ts5tee2asQMXmjyR/vwFZbWhnwow6+Z387qYsraJkCmJiFUkvKk+nk4gYcKEVFXDiESfSW+0" +
//                    "RuKfbtLlhjYutC9HHQrNM+gTLFtjyYuERQKBgCjWQee6q1PwrlP3/Mard1ka1QLbM9kqAyizv+2H" +
//                    "GPOnQ/gR/p2zNNRbTGDrivLRKC/voZxm50dZxqYqpy2L1Bhk3lpjEeJq1Mib62AC/xqlwsmsMsuI" +
//                    "Q+vQTqCl+jzjC+hHx5mydoqiTU+gRGd1HjGl2JvWTAkIA4TzMFFl7f1zAoGAWVVSc+TtDrUp3Gay" +
//                    "5LkdVeuGeTQhE+yo1fVX+VyroReAkerz77oorNL8XcUDxos0McCvZiHPDf9OlnzcNsdTakVRqzY4" +
//                    "Iw1CurpVnfKVTdViXt+1pOVP9OxMwn1qf0LB9wZ/4Qudb21PF/uhMmZj56ffNZ07kg6+fr9tKhIB" +
//                    "uiUCgYEAh3UrIVZd176XtvuIzVPpq+Q+3qisXRludMBqXcLkvluN9wQB7oLMA18m/XWdt6mxWLVv" +
//                    "H6QP3noJGqjfnTUlDZeftDSZhipQ79EEm+6dmnyktcOI6986QpMyEkYUvwB6XwTUH3ZZJFBbDrW/" +
//                    "grGAKtWPDnSG7kkzCESY+5Zsbpk=";
//            System.out.println("RSA公钥BaseRSAUtils64编码:" + publicKeyStr);
//            System.out.println("RSA私钥Base64编码:" + privateKeyStr);
//
//            //=================开始加密=================
//            //String message = "panda";
//            //用公钥加密
//            String publicEncrypt = "kSawVrfHj+S8hBsE/kux1X9UHsKvjL4V8ruk2OEjhGE1G6s/CxnrTYj3jxxvN7hMnLMYkzbqiQtrWVtG9NKj/TVQ6PgWZcMjBRsETE53TNQQSoGmDymNs3LmOQFpZMLX040RfhOCe47uwVW2svc3oqsSZSc3HY1Ai4LdNrjwFvJTaKRw+MRqt8WzBQfFNBZ8wG6PRyNHwSEdZOuvgeNICUD2zMRgjnKDBBkLpaaLNx912x8kcwhtXtSwPnUjzk2+rnugG6hQ/K8EHoFR3Mm5HfZWoZPDTz45nD7mPoTFuJZNrdej9IltifSqA3UPFE7YS+6dc124McrZ2G9YujLL3w==";
//            //System.out.println("公钥加密并Base64编码的结果：" + publicEncrypt);
//
//
//            //===================开始解密================
//            //用私钥解密
//            String privateDecrypt = RSAUtils.privateDecrypt(publicEncrypt, privateKeyStr);
//            //解密后的明文
//            System.out.println("解密后的明文: " + privateDecrypt);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
