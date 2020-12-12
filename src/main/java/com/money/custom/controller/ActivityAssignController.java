package com.money.custom.controller;

import com.money.custom.entity.request.AssignActivityRequest;
import com.money.custom.entity.request.QueryAssignActivityRequest;
import com.money.custom.service.AssignActivityService;
import com.money.framework.base.annotation.VisitLogFlag;
import com.money.framework.base.entity.GridResponseBase;
import com.money.framework.base.entity.ResponseBase;
import com.money.framework.base.entity.VisitLogTypeEnum;
import com.money.framework.base.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@VisitLogFlag(module = "套餐/活动管理", resource = "活动赠送记录")
@Controller
@RequestMapping("/activityAssign/*")
public class ActivityAssignController extends BaseController {

    @Autowired
    private AssignActivityService service;

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "list/search")
    public GridResponseBase listSearch(QueryAssignActivityRequest request) {
        int recordCount = this.service.selectSearchListCount(request);
        return new GridResponseBase(recordCount, request.calTotalPage(recordCount), this.service.selectSearchList(request));
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseBase add(@Valid @RequestBody AssignActivityRequest request, BindingResult bindingResult) {
        this.service.add(request);
        return ResponseBase.success();
    }

}
