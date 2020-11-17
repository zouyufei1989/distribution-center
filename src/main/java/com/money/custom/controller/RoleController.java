package com.money.custom.controller;

import com.money.custom.entity.request.QueryRoleRequest;
import com.money.custom.entity.request.SaveRoleRightsRequest;
import com.money.custom.service.RoleService;
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
    public ResponseEntity<Map<String, Object>> selectSearchList(QueryRoleRequest request)  {
        Map<String, Object> result = new HashMap<>();

        result.put("rows", roleService.selectSearchList(request));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "queryUserRoleRights", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> queryUserRoleRights(String id)  {
        Map<String, Object> result = new HashMap<>();
        result.put("data", this.roleService.queryUserRoleRights(id));
        if (id != null) {
            result.put("role", this.roleService.findById(id));
        }
        result.put("success", true);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> add(@RequestBody SaveRoleRightsRequest request)  {
        Map<String, Object> result = new HashMap<>();
        this.roleService.add(request);
        result.put("success", true);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> edit(@RequestBody SaveRoleRightsRequest request)  {
        Map<String, Object> result = new HashMap<>();
        this.roleService.edit(request);
        result.put("success", true);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
