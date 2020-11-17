package com.money.custom.config;

import com.money.framework.util.ExcelUtils;
import com.money.framework.util.RSAUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class MyPasswordEncoder implements PasswordEncoder {

    public final static Logger logger = LoggerFactory.getLogger(MyPasswordEncoder.class);

    @Value("${RSA.PRIVATE.KEY}")
    String RSA_PRI_KEY;

    @Override
    public String encode(CharSequence rawPassword) {
        return String.valueOf(rawPassword);
    }

    @Override
    public boolean matches(CharSequence userInputPwd, String pwdInDb) {

        try {
            String pwd = RSAUtils.privateDecrypt(String.valueOf(userInputPwd), RSA_PRI_KEY);
            String userPwd = RSAUtils.privateDecrypt(pwdInDb, RSA_PRI_KEY);
            return StringUtils.equals(pwd, userPwd);
        } catch (NoSuchAlgorithmException | BadPaddingException | InvalidKeyException | NoSuchPaddingException | InvalidKeySpecException | IOException | IllegalBlockSizeException e) {
            logger.error("解密失败", e);
        }
        return false;
    }
}