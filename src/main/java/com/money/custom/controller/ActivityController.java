package com.money.custom.controller;

import com.money.custom.entity.enums.GoodsTypeEnum;
import com.money.custom.entity.request.*;
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

@VisitLogFlag(module = "套餐/活动管理", resource = "活动管理")
@Controller
@RequestMapping("/activity/*")
public class ActivityController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "list/search")
    public GridResponseBase listSearch(QueryGoodsRequest request) {
        request.getGoods().setType(GoodsTypeEnum.ACTIVITY.getValue());
        int recordCount = this.goodsService.selectSearchListCount(request);
        return new GridResponseBase(recordCount, request.calTotalPage(recordCount), this.goodsService.selectSearchList(request));
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseBase add(@Valid @RequestBody MoAGoods4ActivityRequest request, BindingResult bindingResult) {
        this.goodsService.addActivityWithItem(request);
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
    public ResponseBase edit(@Valid @RequestBody MoAGoods4ActivityRequest request, BindingResult bindingResult) {
        this.goodsService.editActivity(request);
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
