package com.money.h5.controller;

import com.money.h5.entity.H5RequestBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * H5 相关请求处理
 * 去掉user 登录校验
 * 添加方法签名校验
 */
@Api(value = "H5Controller")
@RequestMapping(value = "h5")
@Controller
public class H5Controller {

    @ApiOperation(value = "测试")
    @ResponseBody
    @CrossOrigin(allowCredentials = "true", maxAge = 3600)
    @RequestMapping(value = "bindPassengerRoute", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> bindTmpVehicleRouteAuto(@Valid @RequestBody H5RequestBase request, BindingResult bindingResult) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
