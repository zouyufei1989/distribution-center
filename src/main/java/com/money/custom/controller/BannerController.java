package com.money.custom.controller;

import com.money.custom.entity.Banner;
import com.money.custom.entity.Group;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.custom.entity.request.QueryGroupRequest;
import com.money.custom.service.BannerService;
import com.money.framework.base.annotation.VisitLogFlag;
import com.money.framework.base.entity.VisitLogTypeEnum;
import com.money.framework.base.web.controller.BaseController;
import com.money.framework.util.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@VisitLogFlag(module = "系统管理", resource = "banner管理")
@Controller
@RequestMapping("/banner/*")
public class BannerController extends BaseController {

    @Autowired
    private BannerService bannerService;

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "list/search")
    public ResponseEntity<Map<String, Object>> listSearch(QueryGroupRequest request) {
        Map<String, Object> result = new HashMap<>();
        int recordCount = this.bannerService.selectSearchListCount(request);
        result.put("records", recordCount);
        result.put("total", request.calTotalPage(recordCount));
        result.put("rows", this.bannerService.selectSearchList(request));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> add(@Valid @RequestBody Banner item, BindingResult bindingResult) {
        Map<String, Object> result = new HashMap<>();
        this.bannerService.add(item);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "findById", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> findById(String id) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", this.bannerService.findById(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> edit(@Valid @RequestBody Banner item, BindingResult bindingResult) {
        Map<String, Object> result = new HashMap<>();
        this.bannerService.edit(item);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "changeStatus", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> changeStatus(@RequestBody ChangeStatusRequest request) {
        Map<String, Object> result = new HashMap<>();
        this.bannerService.changeStatus(request);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
