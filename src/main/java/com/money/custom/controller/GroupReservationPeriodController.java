package com.money.custom.controller;

import com.money.custom.entity.Group;
import com.money.custom.entity.request.*;
import com.money.custom.service.GroupReservationPeriodService;
import com.money.custom.service.GroupService;
import com.money.framework.base.annotation.VisitLogFlag;
import com.money.framework.base.entity.GridResponseBase;
import com.money.framework.base.entity.ResponseBase;
import com.money.framework.base.entity.VisitLogTypeEnum;
import com.money.framework.base.web.controller.BaseController;
import com.money.framework.util.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;

@VisitLogFlag(module = "企业管理", resource = "预约时间段")
@Controller
@RequestMapping("/groupReservationPeriod/*")
public class GroupReservationPeriodController extends BaseController {

    @Autowired
    GroupReservationPeriodService groupReservationPeriodService;
    @Autowired
    GroupService groupService;

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "list/search")
    public GridResponseBase listSearch(QueryGroupReservationPeriodRequest request) {
        int recordCount = this.groupReservationPeriodService.selectSearchListCount(request);
        return new GridResponseBase(recordCount, request.calTotalPage(recordCount), this.groupReservationPeriodService.selectSearchList(request));
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "addReservationPeriods", method = RequestMethod.POST)
    public ResponseBase add(@Valid @RequestBody SaveGroupReservationPeriodsRequest request, BindingResult bindingResult) {
        this.groupReservationPeriodService.add(request);
        return ResponseBase.success();
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "changeReserveFlag", method = RequestMethod.POST)
    public ResponseBase changeReserveFlag(@Valid @RequestBody ChangeGroupReserveFlagRequest request, BindingResult bindingResult) {
        groupService.edit(new Group(request));
        return ResponseBase.success();
    }

}
