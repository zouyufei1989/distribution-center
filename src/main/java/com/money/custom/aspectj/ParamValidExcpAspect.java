package com.money.custom.aspectj;

import com.money.framework.base.exception.PandabusSpecException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ParamValidExcpAspect {

    final static Logger logger = LoggerFactory.getLogger(ParamValidExcpAspect.class);

    @ResponseBody
    @ExceptionHandler({BindException.class}) //指定拦截异常的类型
    public ResponseEntity<Map<String, Object>> customExceptionHandler(BindException e) {
        Map<String, Object> result = new HashMap<>();

        BindingResult bindingResult = e.getBindingResult();
        List<String> errorList = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        result.put("message", StringUtils.join(errorList, ","));
        result.put("success", false);

        logger.error(e.getMessage(), e);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @ExceptionHandler({PandabusSpecException.class}) //指定拦截异常的类型
    public ResponseEntity<Map<String, Object>> pandabusExceptionHandler(PandabusSpecException e) {
        Map<String, Object> result = new HashMap<>();

        result.put("message", e.getMsg());
        result.put("success", false);

        logger.error(e.getMessage(), e);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
