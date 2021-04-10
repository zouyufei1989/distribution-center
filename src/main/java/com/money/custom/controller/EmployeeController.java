package com.money.custom.controller;

import com.money.custom.entity.dto.TreeNodeDto;
import com.money.custom.entity.request.*;
import com.money.custom.service.EmployeeService;
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

@VisitLogFlag(module = "门店管理", resource = "员工管理")
@Controller
@RequestMapping("/employee/*")
public class EmployeeController extends BaseController {

    @Autowired
    private EmployeeService employeeService;

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "list/search")
    public GridResponseBase listSearch(QueryEmployeeRequest request) {
        int recordCount = this.employeeService.selectSearchListCount(request);
        return new GridResponseBase(recordCount, request.calTotalPage(recordCount), this.employeeService.selectSearchList(request));
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseBase add(@Valid @RequestBody MoAEmployeeRequest request, BindingResult bindingResult) {
        this.employeeService.add(request);
        return ResponseBase.success();
    }

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "findById", method = RequestMethod.POST)
    public ResponseBase findById(String id) {
        return ResponseBase.success(this.employeeService.findById(id));
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ResponseBase edit(@Valid @RequestBody MoAEmployeeRequest request, BindingResult bindingResult) {
        this.employeeService.edit(request);
        return ResponseBase.success();
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "changeStatus", method = RequestMethod.POST)
    public ResponseBase changeStatus(@RequestBody ChangeEmployeeStatusRequest request) {
        this.employeeService.changeStatus(request);
        return ResponseBase.success();
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "bindCustomerGroup", method = RequestMethod.POST)
    public ResponseBase bindCustomerGroup(@Valid @RequestBody BindCustomer4EmployeeRequest request, BindingResult bindingResult) {
        this.employeeService.bindCustomers(request);
        return ResponseBase.success();
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "buildEmployeeRelationships", method = RequestMethod.POST)
    public ResponseBase buildEmployeeRelationships(String employeeId) {
        final TreeNodeDto treeNode = this.employeeService.buildEmployeeRelationships(employeeId);
        return ResponseBase.success(treeNode);
    }

}
