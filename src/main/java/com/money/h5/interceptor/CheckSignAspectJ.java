package com.money.h5.interceptor;

import com.money.framework.util.MD5Utils;
import com.money.h5.entity.H5RequestBase;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

//@Component
//@Aspect
public class CheckSignAspectJ {

    final static Logger logger = LoggerFactory.getLogger(CheckSignAspectJ.class);
    final static Long TIMESTAMP_TIMEOUT_MMS = 10 * 60 * 1000L;

    /**
     * 对所有H5 controller切片 (方法说明描述)
     */
    @Pointcut("execution(* com.money.h5.controller.H5Controller.*(..))")
    @Order(3)
    public void anyMethod() {
    }

    @Around(value = "anyMethod()")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        // 访问目标方法的参数：
        Object[] args = point.getArgs();
        for (Object arg : args) {
            if (arg instanceof H5RequestBase) {
                checkArg((H5RequestBase) arg);
            }
        }

        return point.proceed(args);
    }

    private void checkArg(H5RequestBase arg) {
        Assert.notNull(arg.getPhone(), "手机号不可为空");
        Assert.notNull(arg.getTimestamp(), "参数缺少时间戳");
        Assert.isTrue(Math.abs(arg.getTimestamp() - System.currentTimeMillis()) < TIMESTAMP_TIMEOUT_MMS, "时间戳已过期");
        Assert.hasText(arg.getSign(), "参数缺少签名");

        String md5 = MD5Utils.getMD5("zlz"+arg.getTimestamp().toString());
        Assert.isTrue(StringUtils.equals(md5, arg.getSign()), "签名错误");
    }

}