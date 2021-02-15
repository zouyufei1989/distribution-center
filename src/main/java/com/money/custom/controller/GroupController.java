package com.money.custom.controller;

import com.money.custom.entity.Group;
import com.money.custom.entity.GroupReservationPeriod;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@VisitLogFlag(module = "企业管理", resource = "公司管理")
@Controller
@RequestMapping("/group/*")
public class GroupController extends BaseController {

    @Autowired
    private GroupService groupService;
    @Autowired
    UploadUtils uploadUtils;
    @Autowired
    GroupReservationPeriodService groupReservationPeriodService;

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "list/search")
    public GridResponseBase listSearch(QueryGroupRequest request) {
        int recordCount = this.groupService.selectSearchListCount(request);
        return new GridResponseBase(recordCount, request.calTotalPage(recordCount), this.groupService.selectSearchList(request));
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseBase add(@Valid @RequestBody Group group, BindingResult bindingResult) {
        this.groupService.add(group);
        return ResponseBase.success();
    }

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "findById", method = RequestMethod.POST)
    public ResponseBase findById(String id) {
        return ResponseBase.success(this.groupService.findById(id));
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "editDetailImg", method = RequestMethod.POST)
    public ResponseBase editDetailImg(@Valid @RequestBody ModifyGroupDetailImgsRequest request, BindingResult bindingResult) {
        this.groupService.edit(new Group(request));
        return ResponseBase.success();
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "editVideoList", method = RequestMethod.POST)
    public ResponseBase editVideoList(@Valid @RequestBody ModifyGroupVedioesRequest request, BindingResult bindingResult) {
        this.groupService.edit(new Group(request));
        return ResponseBase.success();
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ResponseBase edit(@Valid @RequestBody Group group, BindingResult bindingResult) {
        this.groupService.edit(group);
        return ResponseBase.success();
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "changeStatus", method = RequestMethod.POST)
    public ResponseBase changeStatus(@RequestBody ChangeStatusRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        this.groupService.changeStatus(request);
        return ResponseBase.success();
    }

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "reservationPeriodsList")
    public GridResponseBase reservationPeriodsList(QueryGridRequestBase request) {
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

}
