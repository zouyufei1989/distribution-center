package com.money.custom.controller;

import com.money.custom.entity.request.QueryExceptionLogRequest;
import com.money.custom.service.ExceptionLogService;
import com.money.framework.base.annotation.VisitLogFlag;
import com.money.framework.base.entity.VisitLogTypeEnum;
import com.money.framework.base.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@VisitLogFlag(module = "系统管理", resource = "异常日志")
@Controller
@RequestMapping("/exceptionLog/*")
public class ExceptionLogController extends BaseController {

    @Autowired
    private ExceptionLogService exceptionLogService;

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "list/search")
    public ResponseEntity<Map<String, Object>> listSearch(QueryExceptionLogRequest request) {
        Map<String, Object> result = new HashMap<>();
        result.put("rows", this.exceptionLogService.selectSearchList(request));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "selectSearchListGroupByMethod")
    public ResponseEntity<Map<String, Object>> selectSearchListGroupByMethod(QueryExceptionLogRequest request) {
        Map<String, Object> result = new HashMap<>();
        result.put("rows", this.exceptionLogService.selectSearchListGroupByMethod(request));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
