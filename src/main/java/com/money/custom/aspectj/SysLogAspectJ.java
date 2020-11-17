package com.money.custom.aspectj;

import com.alibaba.fastjson.JSON;
import com.money.framework.base.entity.ExcelMultipartFile;
import com.money.framework.base.exception.PandabusSpecException;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public Object process(ProceedingJoinPoint point) {
        long start = System.currentTimeMillis();

        // 访问目标方法的参数：
        Object[] args = point.getArgs();
        Object returnValue = null;
        try {
            checkValidError(args);
            returnValue = point.proceed(args);
        } catch (Throwable ex) {
            String fullName = point.getTarget().getClass().getName();
            String methodName = point.getSignature().getName();
            String params = "";
            try {
                if (args[0] instanceof ExcelMultipartFile) {
                    params = ((ExcelMultipartFile) args[0]).getOriginalFilename();
                } else {
                    params = JSON.toJSONString(args);
                }
            } catch (Exception e) {
                params = "参数不可JSON序列化";
            }

            logger.error(String.format("%s.%s(%s)", fullName, methodName, params), ex);

            Map<String, Object> result = new HashMap<>();
            result.put("success", false);
            result.put("message", "服务器异常");
            if(ex instanceof IllegalArgumentException){
                result.put("message", ex.getMessage());
            }
            if (ex instanceof PandabusSpecException) {
                result.put("message", ((PandabusSpecException) ex).getMsg());
            }
            result.put("args", params);

            return new ResponseEntity<>(result, HttpStatus.OK);
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
                    throw new PandabusSpecException(StringUtils.join(errorList, "\r\n"));
                }
            }
        }
    }


}