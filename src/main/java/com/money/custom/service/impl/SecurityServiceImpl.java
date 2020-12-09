package com.money.custom.service.impl;

import com.money.custom.service.LoginService;
import com.money.framework.base.exception.PandabusSpecException;
import com.money.framework.base.service.BaseService;
import com.money.framework.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SecurityServiceImpl extends BaseServiceImpl implements UserDetailsService {

    @Autowired
    LoginService loginService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails userDetails = loginService.findUserByName(s);
        if (Objects.isNull(userDetails)) {
            throw new UsernameNotFoundException("Username " + s + " not found");
        }
        return userDetails;
    }
}
