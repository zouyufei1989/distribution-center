package com.money.custom.interceptor;

import com.money.custom.entity.Consts;
import com.money.custom.entity.Resource;
import com.money.custom.entity.User;
import com.money.custom.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Configuration
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    public final static Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        resetSessionAttrs(request);
        setPageAuth(request);
        return true;
    }

    private void resetSessionAttrs(HttpServletRequest request) {
        request.getSession().setAttribute("ResourceFuncIds", 0);
        request.getSession().setAttribute("RoleFuncIds", 0);
        request.getSession().setAttribute("msg", "");
    }

    private void setPageAuth(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Consts.LOGIN_USER);
        if (Objects.isNull(user)) {
            return;
        }
        String reqUrl = request.getRequestURL().toString();
        String url = reqUrl.substring(reqUrl.lastIndexOf(request.getContextPath()) + request.getContextPath().length());
        List<Resource> userResources = loginService.getUserResources(user);
        Optional<Resource> optionalResource = userResources.stream().filter(i -> StringUtils.equals(i.getUrl(), url)).findFirst();
        if (optionalResource.isPresent()) {
            Resource resource = optionalResource.get();
            request.getSession().setAttribute("ResourceFuncIds", resource.getResourceFuncIds());
            request.getSession().setAttribute("RoleFuncIds", resource.getRoleFuncIds());
        }
    }
}
