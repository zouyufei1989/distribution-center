package com.money.h5.controller;

import com.money.custom.entity.dto.TreeNodeDto;
import com.money.custom.service.EmployeeService;
import com.money.framework.base.entity.ResponseBase;
import com.money.h5.entity.dto.H5Employee;
import com.money.h5.entity.request.QueryByIdRequest;
import com.money.h5.entity.response.QueryEmployeeCustomerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "员工端")
@RequestMapping(value = "h5Employee")
@Controller
@CrossOrigin(allowCredentials = "true", maxAge = 3600)
public class H5EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @ApiOperation(value = "查看员工的股东列表", notes = "id = 1")
    @ResponseBody
    @RequestMapping(value = "queryEmployeeCustomerList", method = RequestMethod.POST)
    public QueryEmployeeCustomerResponse queryEmployeeCustomerList(@Valid @RequestBody QueryByIdRequest request, BindingResult bindingResult) {
        final TreeNodeDto treeNodeDto = employeeService.buildEmployeeRelationships(request.getId());
        return new QueryEmployeeCustomerResponse(treeNodeDto.getChildren());
    }

    @ApiOperation(value = "获取当前登录员工信息", notes = "id = 6")
    @ResponseBody
    @RequestMapping(value = "queryEmployeeInfo", method = RequestMethod.POST)
    public ResponseBase queryEmployeeInfo(@Valid @RequestBody QueryByIdRequest request, BindingResult bindingResult) {
        return ResponseBase.success(new H5Employee(employeeService.findById(request.getId())));
    }

}
