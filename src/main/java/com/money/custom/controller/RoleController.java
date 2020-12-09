package com.money.custom.controller;

import com.money.custom.entity.request.QueryRoleRequest;
import com.money.custom.entity.request.SaveRoleRightsRequest;
import com.money.custom.entity.response.QueryRoleAndRightsResponse;
import com.money.custom.service.RoleService;
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

/**
 * 系统角色管理
 */
@VisitLogFlag(module = "系统管理", resource = "系统角色管理")
@Controller
@RequestMapping("/system/role/*")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return "/system_role/index";
    }

    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String update() {
        return "/system_role/update";
    }

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "list/search")
    public GridResponseBase selectSearchList(QueryRoleRequest request) {
        return new GridResponseBase(this.roleService.selectSearchList(request));
    }

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "queryUserRoleRights", method = RequestMethod.POST)
    public QueryRoleAndRightsResponse queryUserRoleRights(String id) {
        QueryRoleAndRightsResponse response = new QueryRoleAndRightsResponse();
        response.setData(this.roleService.queryUserRoleRights(id));
        if (id != null) {
            response.setRole(this.roleService.findById(id));
        }
        return response;
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseBase add(@RequestBody SaveRoleRightsRequest request) {
        this.roleService.add(request);
        return ResponseBase.success();
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ResponseBase edit(@RequestBody SaveRoleRightsRequest request) {
        this.roleService.edit(request);
        return ResponseBase.success();
    }
}
