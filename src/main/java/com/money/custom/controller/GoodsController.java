package com.money.custom.controller;

import com.money.custom.entity.GoodsItem;
import com.money.custom.entity.request.ChangeStatusRequest;
import com.money.custom.entity.request.MoAGoods4SingleRequest;
import com.money.custom.entity.request.ModifyGoodsDetailRequest;
import com.money.custom.entity.request.QueryGoodsRequest;
import com.money.custom.service.GoodsService;
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
import javax.validation.constraints.NotNull;
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
    public GridResponseBase listSearch(QueryGoodsRequest request) {
        int recordCount = this.goodsService.selectSearchListCount(request);
        return new GridResponseBase(recordCount, request.calTotalPage(recordCount), this.goodsService.selectSearchList(request));
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "addSingleItem", method = RequestMethod.POST)
    public ResponseBase add(@Valid @RequestBody MoAGoods4SingleRequest request, BindingResult bindingResult) {
        this.goodsService.addSingleItem(request);
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
    @RequestMapping(value = "editSingleItem", method = RequestMethod.POST)
    public ResponseBase edit(@Valid @RequestBody MoAGoods4SingleRequest request, BindingResult bindingResult) {
        this.goodsService.editSingleItem(request);
        return ResponseBase.success();
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "changeStatus", method = RequestMethod.POST)
    public ResponseBase changeStatus(@RequestBody ChangeStatusRequest request) {
        this.goodsService.changeStatus(request);
        return ResponseBase.success();
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "editGoodsDetail", method = RequestMethod.POST)
    public ResponseBase changeStatus(@Valid @RequestBody ModifyGoodsDetailRequest request, BindingResult bindingResult) {
        MoAGoods4SingleRequest editRequest = new MoAGoods4SingleRequest();
        editRequest.setId(request.getId());
        editRequest.setDetail(request.getDetail());
        editRequest.copyOperationInfo(request);
        this.goodsService.editSingleItem(editRequest);
        return ResponseBase.success();
    }


}
