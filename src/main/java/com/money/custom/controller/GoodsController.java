package com.money.custom.controller;

import com.money.custom.entity.Banner;
import com.money.custom.entity.Goods;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.custom.entity.request.QueryGoodsRequest;
import com.money.custom.entity.request.QueryGroupRequest;
import com.money.custom.service.GoodsService;
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

@VisitLogFlag(module = "商品管理", resource = "商品信息管理")
@Controller
@RequestMapping("/goods/*")
public class GoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "list/search")
    public ResponseEntity<Map<String, Object>> listSearch(QueryGoodsRequest request) {
        Map<String, Object> result = new HashMap<>();
        int recordCount = this.goodsService.selectSearchListCount(request);
        result.put("records", recordCount);
        result.put("total", request.calTotalPage(recordCount));
        result.put("rows", this.goodsService.selectSearchList(request));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> add(@RequestBody Goods item) {
        Map<String, Object> result = new HashMap<>();
        this.goodsService.add(item);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "findById", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> findById(String id) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", this.goodsService.findById(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> edit(@RequestBody Goods item) {
        Map<String, Object> result = new HashMap<>();
        this.goodsService.edit(item);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "changeStatus", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> edit(@RequestBody ChangeStatusRequest request) {
        Map<String, Object> result = new HashMap<>();
        this.goodsService.changeStatus(request);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
