package com.money.custom.interceptor;

import com.money.custom.entity.Consts;
import com.money.custom.entity.User;
import com.money.custom.entity.VisitLog;
import com.money.custom.rabbitmq.QueueConsts;
import com.money.custom.utils.RabbitMqUtils;
import com.money.framework.base.annotation.VisitLogFlag;
import com.money.framework.base.entity.VisitLogTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Configuration
@Component
public class VisitLogInterceptor extends HandlerInterceptorAdapter {

    public final static Logger logger = LoggerFactory.getLogger(VisitLogInterceptor.class);

    @Autowired
    RabbitMqUtils rabbitMqUtils;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String url = request.getRequestURI();
        String module = "";
        String resource = "";
        VisitLogTypeEnum typeEnum = VisitLogTypeEnum.NONE;

        if (handlerMethod.getMethod().getDeclaringClass().isAnnotationPresent(VisitLogFlag.class)) {
            VisitLogFlag annotation = handlerMethod.getMethod().getDeclaringClass().getAnnotation(VisitLogFlag.class);
            module = annotation.module();
            resource = annotation.resource();
            typeEnum = annotation.type();
        }

        if (handlerMethod.getMethod().isAnnotationPresent(VisitLogFlag.class)) {
            VisitLogFlag annotation = handlerMethod.getMethod().getAnnotation(VisitLogFlag.class);
            if (StringUtils.isNotEmpty(annotation.module())) {
                module = annotation.module();
            }

            if (StringUtils.isNotEmpty(annotation.resource())) {
                resource = annotation.resource();
            }

            if (annotation.type() != VisitLogTypeEnum.NONE) {
                typeEnum = annotation.type();
            }
        }

        User loginUser = (User) request.getSession().getAttribute(Consts.LOGIN_USER);

        if (typeEnum != VisitLogTypeEnum.NONE && Objects.nonNull(loginUser)) {
            VisitLog log = new VisitLog();
            log.setModuleName(module);
            log.setResourceName(resource);
            log.setType(typeEnum.getValue());
            log.setUrl(url);
            log.setUserId(loginUser.getId());
            log.setUserIp(request.getRemoteAddr());
            Object method_exec_mm = request.getAttribute("method_exec_mm");
            if (Objects.nonNull(method_exec_mm)) {
                log.setDuration(((Long) method_exec_mm).intValue());
            }

            rabbitMqUtils.send(QueueConsts.VISIT_LOG_QUEUE, log,"visitLog");
        }
    }

}
