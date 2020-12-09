package com.money.custom.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.money.custom.entity.Consts;
import com.money.custom.entity.User;
import com.money.custom.entity.enums.ResponseCodeEnum;
import com.money.custom.entity.response.LoginResponse;
import com.money.custom.entity.response.ResponseBase;
import com.money.custom.service.RoleService;
import com.money.custom.service.impl.SecurityServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SecurityServiceImpl securityService;
    @Autowired
    MyPasswordEncoder myPasswordEncoder;
    @Autowired
    private RoleService roleService;

    static List<String> SKIP_URL;

    static {
        SKIP_URL = new ArrayList<>();
        SKIP_URL.add("/history/**");
        SKIP_URL.add("/test/**");

        SKIP_URL.add("/timeOut");
        SKIP_URL.add("/notUniqueLogin");
        SKIP_URL.add("/forceQuite");
        SKIP_URL.add("/isTimeout");
        SKIP_URL.add("/verifyCode");
        SKIP_URL.add("/info");
        SKIP_URL.add("/403");
        SKIP_URL.add("/utils/getFile");
        SKIP_URL.add("/utils/uploadFile4Balance");
    }

    @Bean
    HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring().antMatchers("/script/**", "/resource/**", "/css/**")
                .and()
                .ignoring().antMatchers("/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/webjars/**", "/v2/*")
                .and()
                .ignoring().mvcMatchers(SKIP_URL.toArray(new String[0]));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
        securityLogin(http);
        securityLogout(http);
        securityUniqueLogin(http);
        securityCsrf(http);
    }

    private void securityCsrf(HttpSecurity http) throws Exception {
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

    private void securityUniqueLogin(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/notUniqueLogin");
    }

    private void securityLogout(HttpSecurity http) throws Exception {
        http
                .logout()
                .logoutSuccessUrl("/login")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .deleteCookies()
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .permitAll();
    }

    private void securityLogin(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/getLogin")
                .successHandler((req, resp, auth) -> {
                    User loginUser = (User) auth.getPrincipal();
                    loginUser.setPassword(StringUtils.EMPTY);
                    setSessionAttr(req, loginUser);
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(new LoginResponse()));
                    out.flush();
                    out.close();
                })
                .failureHandler((req, resp, e) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    if (e instanceof BadCredentialsException) {
                        out.write(new ObjectMapper().writeValueAsString(new ResponseBase("用户名、密码错误", ResponseCodeEnum.LOGIN_FAIL)));
                    } else {
                        out.write(new ObjectMapper().writeValueAsString(new ResponseBase(e.getMessage(), ResponseCodeEnum.LOGIN_FAIL)));
                    }
                    out.flush();
                    out.close();
                })
                .permitAll();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(myAuthenticationProvider()));
    }

    @Bean
    MyAuthenticationProvider myAuthenticationProvider() {
        MyAuthenticationProvider myAuthenticationProvider = new MyAuthenticationProvider();
        myAuthenticationProvider.setPasswordEncoder(myPasswordEncoder);
        myAuthenticationProvider.setUserDetailsService(securityService);
        return myAuthenticationProvider;
    }

    private void setSessionAttr(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute(Consts.LOGIN_USER, user);
        session.setAttribute("ResourceFuncIds", 0);
        session.setAttribute("RoleFuncIds", 0);
        session.setAttribute("msg", "");
        session.setAttribute(Consts.LOGIN_USER, user);

        session.setAttribute("funcList", com.alibaba.fastjson.JSON.toJSONString(roleService.queryFuncs()));
    }
}
