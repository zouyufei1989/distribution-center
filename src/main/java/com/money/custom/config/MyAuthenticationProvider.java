package com.money.custom.config;

import com.money.custom.entity.enums.ConstsEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class MyAuthenticationProvider extends DaoAuthenticationProvider {

    @Value("${spring.profiles.active}")
    String ENV;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (!StringUtils.equals(ENV, "dev")) {
            String code = req.getParameter("verifyCode");
            String verify_code = (String) req.getSession().getAttribute(ConstsEnum.LOGIN_VERIFY_CODE.getName());
            if (Objects.isNull(code) || Objects.isNull(verify_code) || !StringUtils.equals(code.toLowerCase(), verify_code.toLowerCase())) {
                logger.error(String.format("sys:%s user:%s", verify_code, code));
                throw new AuthenticationServiceException("验证码错误");
            }
        }
        super.additionalAuthenticationChecks(userDetails, authentication);
    }
}