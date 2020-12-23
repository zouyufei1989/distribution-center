package com.money.custom.aspectj;

import com.alibaba.fastjson.JSON;
import com.money.framework.base.entity.ExcelMultipartFile;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Aspect
public class SysLogAspectJ {

    final static Logger logger = LoggerFactory.getLogger(SysLogAspectJ.class);

    /**
     * 对所有controller切片 (方法说明描述)
     *
     * @author yufei.zou
     */
    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    @Order(2)
    public void anyMethod() {
    }

    @Around(value = "anyMethod()")
    public Object process(ProceedingJoinPoint point) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        long start = System.currentTimeMillis();
        String fullName = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 访问目标方法的参数：
        Object[] args = point.getArgs();
        Object returnValue = null;
        String params = "";
        try {
            if (args[0] instanceof ExcelMultipartFile) {
                params = ((ExcelMultipartFile) args[0]).getOriginalFilename();
            } else if (args[args.length - 1] instanceof BindingResult) {
                params = JSON.toJSONString(Arrays.copyOf(args, args.length - 1));
            } else {
                params = JSON.toJSONString(args);
            }
        } catch (Exception e) {
            params = "参数不可JSON序列化";
        }

        try {
            checkValidError(args);
            returnValue = point.proceed(args);
        } catch (Throwable ex) {
            logger.error(String.format("%s.%s(%s)", fullName, methodName, params), ex);
            returnValue = signature.getReturnType().newInstance();
            Method errMethod = signature.getReturnType().getMethod("error", Throwable.class);
            errMethod.invoke(returnValue, ex);
        }

        long execMM = System.currentTimeMillis() - start;
        logger.info("exec duration: {} \t {} ", execMM, point.getSignature().toString());
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        request.setAttribute("method_exec_mm", execMM);
        return returnValue;
    }

    private void checkValidError(Object[] args) {
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) arg;
                if (bindingResult.hasErrors()) {
                    List<String> errorList = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
                    throw new IllegalArgumentException(StringUtils.join(errorList, "\r\n"));
                }
            }
        }
    }


}