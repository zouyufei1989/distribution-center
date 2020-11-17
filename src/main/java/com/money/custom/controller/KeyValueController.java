package com.money.custom.controller;

import com.money.custom.entity.KeyValue;
import com.money.custom.entity.request.QueryKeyValueRequest;
import com.money.custom.service.KeyValueService;
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

@VisitLogFlag(module = "系统管理", resource = "key-value")
@Controller
@RequestMapping("/keyValue/*")
public class KeyValueController extends BaseController {


    @Autowired
    private KeyValueService keyValueService;


    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "list/search")
    public ResponseEntity<Map<String, Object>> listSearch(QueryKeyValueRequest request)  {
        Map<String, Object> result = new HashMap<>();
        int recordCount = this.keyValueService.selectSearchListCount(request);
        result.put("records", recordCount);
        result.put("total", request.calTotalPage(recordCount));
        result.put("rows", this.keyValueService.selectSearchList(request));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> add(@RequestBody KeyValue keyValue)  {
        Map<String, Object> result = new HashMap<>();
        this.keyValueService.add(keyValue);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "findById", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> findById(String id)  {
        Map<String, Object> result = new HashMap<>();
        result.put("data", this.keyValueService.findById(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> edit(@RequestBody KeyValue keyValue)  {
        Map<String, Object> result = new HashMap<>();
        this.keyValueService.edit(keyValue);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
