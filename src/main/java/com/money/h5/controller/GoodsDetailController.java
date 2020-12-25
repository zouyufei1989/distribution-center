package com.money.h5.controller;

import com.money.custom.entity.Goods;
import com.money.custom.service.GoodsService;
import com.money.h5.entity.request.QueryByIdRequest;
import com.money.h5.entity.response.QueryGoodsDetailResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "商品详情")
@RequestMapping(value = "goodsDetail")
@Controller
@CrossOrigin(allowCredentials = "true", maxAge = 3600)
public class GoodsDetailController {

    @Autowired
    GoodsService goodsService;

    @ApiOperation(value = "商品详情", notes = "id=17")
    @ResponseBody
    @RequestMapping(value = "queryGoodsDetail", method = RequestMethod.POST)
    public QueryGoodsDetailResponse queryGoodsDetail(@Valid @RequestBody QueryByIdRequest request, BindingResult bindingResult) {
        Goods goods = goodsService.findById(request.getId());
        return new QueryGoodsDetailResponse(goods);
    }

}
