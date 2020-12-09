package com.money.custom.controller;

import com.money.custom.entity.Banner;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.custom.entity.request.MoACustomerRequest;
import com.money.custom.entity.request.QueryCustomerRequest;
import com.money.custom.entity.request.QueryGroupRequest;
import com.money.custom.service.BannerService;
import com.money.custom.service.CustomerService;
import com.money.framework.base.annotation.VisitLogFlag;
import com.money.framework.base.entity.GridResponseBase;
import com.money.framework.base.entity.ResponseBase;
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

@VisitLogFlag(module = "客户管理", resource = "客户信息管理")
@Controller
@RequestMapping("/customer/*")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "list/search")
    public GridResponseBase listSearch(QueryCustomerRequest request) {
        int recordCount = this.customerService.selectSearchListCount(request);
        return new GridResponseBase(recordCount, request.calTotalPage(recordCount), this.customerService.selectSearchList(request));
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseBase add(@Valid @RequestBody MoACustomerRequest request, BindingResult bindingResult) {
        this.customerService.add(request);
        return ResponseBase.success();
    }

//    @VisitLogFlag(type = VisitLogTypeEnum.READ)
//    @ResponseBody
//    @RequestMapping(value = "findById", method = RequestMethod.POST)
//    public ResponseEntity<Map<String, Object>> findById(String id) {
//        Map<String, Object> result = new HashMap<>();
//        result.put("data", this.bannerService.findById(id));
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
//    @ResponseBody
//    @RequestMapping(value = "edit", method = RequestMethod.POST)
//    public ResponseEntity<Map<String, Object>> edit(@Valid @RequestBody Banner item, BindingResult bindingResult) {
//        Map<String, Object> result = new HashMap<>();
//        this.bannerService.edit(item);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
//    @ResponseBody
//    @RequestMapping(value = "changeStatus", method = RequestMethod.POST)
//    public ResponseEntity<Map<String, Object>> changeStatus(@RequestBody ChangeStatusRequest request) {
//        Map<String, Object> result = new HashMap<>();
//        this.bannerService.changeStatus(request);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }


}
