package com.money.custom.controller;

import com.money.custom.entity.enums.GoodsTypeEnum;
import com.money.custom.entity.request.AssignGoods4PackageRequest;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.custom.entity.request.MoAGoods4PackageRequest;
import com.money.custom.entity.request.QueryGoodsRequest;
import com.money.custom.service.GoodsService;
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

@VisitLogFlag(module = "套餐/活动管理", resource = "套餐管理")
@Controller
@RequestMapping("/package/*")
public class PackageController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "list/search")
    public GridResponseBase listSearch(QueryGoodsRequest request) {
        request.getGoods().setType(GoodsTypeEnum.PACKAGE.getValue());
        int recordCount = this.goodsService.selectSearchListCount(request);
        return new GridResponseBase(recordCount, request.calTotalPage(recordCount), this.goodsService.selectSearchList(request));
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseBase add(@Valid @RequestBody MoAGoods4PackageRequest request, BindingResult bindingResult) {
        this.goodsService.addPackage(request);
        return ResponseBase.success();
    }

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "findById", method = RequestMethod.POST)
    public ResponseBase findById(String id) {
        return ResponseBase.success(this.goodsService.findById(id));
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ResponseBase edit(@Valid @RequestBody MoAGoods4PackageRequest request, BindingResult bindingResult) {
        this.goodsService.editPackage(request);
        return ResponseBase.success();
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "assignGoods4Package", method = RequestMethod.POST)
    public ResponseBase assignGoods4Package(@Valid @RequestBody AssignGoods4PackageRequest request, BindingResult bindingResult) {
        this.goodsService.assignGoods4Package(request);
        return ResponseBase.success();
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "changeStatus", method = RequestMethod.POST)
    public ResponseBase changeStatus(@RequestBody ChangeStatusRequest request) {
        this.goodsService.changeStatus(request);
        return ResponseBase.success();
    }

}
