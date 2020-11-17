package com.money.custom.controller;


import com.money.custom.entity.ScheduleConfig;
import com.money.custom.entity.request.QueryGridRequestBase;
import com.money.custom.service.ScheduleConfigService;
import com.money.framework.base.annotation.VisitLogFlag;
import com.money.framework.base.entity.VisitLogTypeEnum;
import com.money.framework.base.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@VisitLogFlag(module = "系统管理", resource = "定时任务")
@Controller
@RequestMapping("/scheduleConfig/*")
public class ScheduleConfigController extends BaseController {

    @Autowired
    private ScheduleConfigService scheduleConfigService;


    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "list/search")
    public ResponseEntity<Map<String, Object>> listSearch(QueryGridRequestBase request)  {
        Map<String, Object> result = new HashMap<>();
        int recordCount = this.scheduleConfigService.selectSearchListCount(request);
        result.put("records", recordCount);
        result.put("total", request.calTotalPage(recordCount));
        result.put("rows", this.scheduleConfigService.selectSearchList(request));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> add(@RequestBody ScheduleConfig scheduleConfig)  {
        Map<String, Object> result = new HashMap<>();
        this.scheduleConfigService.add(scheduleConfig);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "findById", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> findById(String id)  {
        Map<String, Object> result = new HashMap<>();
        result.put("data", this.scheduleConfigService.findById(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> edit(@RequestBody ScheduleConfig scheduleConfig)  {
        Map<String, Object> result = new HashMap<>();
        this.scheduleConfigService.edit(scheduleConfig);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
