package com.money.custom.aspectj;

import com.money.framework.util.XssUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Aspect
public class XssAspectJ implements ThrowsAdvice {

    final static Logger logger = LoggerFactory.getLogger(XssAspectJ.class);

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    @Order(1)
    public void anyMethod() {
    }


    @Around(value = "anyMethod()")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        // 访问目标方法的参数：
        Object[] args = point.getArgs();
        if (Objects.nonNull(args)) {
            for (Object arg : args) {
                XssUtils.cleanXSS(arg);
            }
        }

        Object returnValue = point.proceed(args);
        XssUtils.cleanXSS(returnValue);
        return returnValue;
    }

}