package com.money.custom.controller;

import com.money.custom.entity.request.QueryVisitLogRequest;
import com.money.custom.service.VisitLogService;
import com.money.framework.base.annotation.VisitLogFlag;
import com.money.framework.base.entity.GridResponseBase;
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

@VisitLogFlag(module = "系统管理", resource = "访问日志")
@Controller
@RequestMapping("/visitLog/*")
public class VisitLogController extends BaseController {


    @Autowired
    VisitLogService visitLogService;

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "list/search")
    public GridResponseBase listSearch(QueryVisitLogRequest request) {
        int recordCount = this.visitLogService.selectSearchListCount(request);
        return new GridResponseBase(recordCount, request.calTotalPage(recordCount), this.visitLogService.selectSearchList(request));
    }

}
