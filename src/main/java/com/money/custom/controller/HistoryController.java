package com.money.custom.controller;

import com.money.custom.entity.request.QueryHistoryRequest;
import com.money.custom.service.HistoryService;
import com.money.framework.base.annotation.SkipUserLoginCheck;
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

@SkipUserLoginCheck
@Controller
@RequestMapping("/history/*")
public class HistoryController extends BaseController {


    @Autowired
    private HistoryService historyService;

    @ResponseBody
    @RequestMapping(value = "list/search")
    public ResponseEntity<Map<String, Object>> listSearch(QueryHistoryRequest request) {
        Map<String, Object> result = new HashMap<>();
        int recordCount = this.historyService.selectSearchListCount(request);
        result.put("records", recordCount);
        result.put("total", request.calTotalPage(recordCount));
        result.put("rows", this.historyService.selectSearchList(request));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
