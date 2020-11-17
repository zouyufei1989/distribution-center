package com.money.custom.controller;


import com.money.custom.entity.request.QueryVisitLogRequest;
import com.money.custom.service.StsVisitLogService;
import com.money.framework.base.annotation.VisitLogFlag;
import com.money.framework.base.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@VisitLogFlag(module = "统计报表", resource = "访问日志统计")
@Controller
@RequestMapping("/statistics/*")
public class StsVisitLogController extends BaseController {

    @Autowired
    private StsVisitLogService stsVisitLogService;

    @ResponseBody
    @RequestMapping(value = "visitLogStsByResource")
    public ResponseEntity<Map<String, Object>> vehicleTripAnalyse(QueryVisitLogRequest request) {
        Map<String, Object> result = new HashMap<>();
        result.put("rows", stsVisitLogService.visitLogStsByResource(request));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "visitLogStsByUser")
    public ResponseEntity<Map<String, Object>> visitLogStsByUser(QueryVisitLogRequest request) {
        Map<String, Object> result = new HashMap<>();
        result.put("rows", stsVisitLogService.visitLogStsByUser(request));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
