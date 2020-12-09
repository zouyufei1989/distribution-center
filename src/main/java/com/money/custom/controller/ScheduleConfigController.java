package com.money.custom.controller;


import com.money.custom.entity.ScheduleConfig;
import com.money.custom.entity.request.QueryGridRequestBase;
import com.money.custom.service.ScheduleConfigService;
import com.money.framework.base.annotation.VisitLogFlag;
import com.money.framework.base.entity.GridResponseBase;
import com.money.framework.base.entity.ResponseBase;
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
    public GridResponseBase listSearch(QueryGridRequestBase request)  {
        int recordCount = this.scheduleConfigService.selectSearchListCount(request);
        return new GridResponseBase(recordCount, request.calTotalPage(recordCount), this.scheduleConfigService.selectSearchList(request));
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseBase add(@RequestBody ScheduleConfig scheduleConfig)  {
        this.scheduleConfigService.add(scheduleConfig);
        return ResponseBase.success();
    }

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "findById", method = RequestMethod.POST)
    public ResponseBase findById(String id)  {
        return ResponseBase.success(this.scheduleConfigService.findById(id));
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ResponseBase edit(@RequestBody ScheduleConfig scheduleConfig)  {
        this.scheduleConfigService.edit(scheduleConfig);
        return ResponseBase.success();
    }

}
