package com.money.custom.controller;

import com.money.custom.entity.Group;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.custom.entity.request.QueryGroupRequest;
import com.money.custom.service.GroupService;
import com.money.framework.base.annotation.VisitLogFlag;
import com.money.framework.base.entity.VisitLogTypeEnum;
import com.money.framework.base.web.controller.BaseController;
import com.money.framework.util.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@VisitLogFlag(module = "企业管理", resource = "公司管理")
@Controller
@RequestMapping("/group/*")
public class GroupController extends BaseController {

    @Autowired
    private GroupService groupService;
    @Autowired
    UploadUtils uploadUtils;


    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "list/search")
    public ResponseEntity<Map<String, Object>> listSearch(QueryGroupRequest request) {
        Map<String, Object> result = new HashMap<>();
        int recordCount = this.groupService.selectSearchListCount(request);
        result.put("records", recordCount);
        result.put("total", request.calTotalPage(recordCount));
        result.put("rows", this.groupService.selectSearchList(request));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> add(@Valid @RequestBody Group group, BindingResult bindingResult) {
        Map<String, Object> result = new HashMap<>();
        this.groupService.add(group);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "findById", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> findById(String id) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", this.groupService.findById(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> edit(@Valid @RequestBody Group group, BindingResult bindingResult) {
        Map<String, Object> result = new HashMap<>();
        this.groupService.edit(group);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "changeStatus", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> changeStatus(@RequestBody ChangeStatusRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<String, Object> result = new HashMap<>();
        this.groupService.changeStatus(request);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
