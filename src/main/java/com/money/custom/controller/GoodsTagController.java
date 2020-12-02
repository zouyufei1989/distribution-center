package com.money.custom.controller;

import com.money.custom.entity.GoodsTag;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.custom.entity.request.QueryGoodsTagRequest;
import com.money.custom.service.GoodsTagService;
import com.money.framework.base.annotation.VisitLogFlag;
import com.money.framework.base.entity.VisitLogTypeEnum;
import com.money.framework.base.web.controller.BaseController;
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
import java.util.HashMap;
import java.util.Map;

@VisitLogFlag(module = "商品管理", resource = "商品标签管理")
@Controller
@RequestMapping("/goodsTag/*")
public class GoodsTagController extends BaseController {

    @Autowired
    private GoodsTagService goodsTagService;

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "list/search")
    public ResponseEntity<Map<String, Object>> listSearch(QueryGoodsTagRequest request) {
        Map<String, Object> result = new HashMap<>();
        int recordCount = this.goodsTagService.selectSearchListCount(request);
        result.put("records", recordCount);
        result.put("total", request.calTotalPage(recordCount));
        result.put("rows", this.goodsTagService.selectSearchList(request));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> add(@Valid @RequestBody GoodsTag item, BindingResult bindingResult) {
        Map<String, Object> result = new HashMap<>();
        this.goodsTagService.add(item);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "findById", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> findById(String id) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", this.goodsTagService.findById(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> edit(@Valid  @RequestBody GoodsTag item, BindingResult bindingResult) {
        Map<String, Object> result = new HashMap<>();
        this.goodsTagService.edit(item);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "changeStatus", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> changeStatus(@RequestBody ChangeStatusRequest request) {
        Map<String, Object> result = new HashMap<>();
        this.goodsTagService.changeStatus(request);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
