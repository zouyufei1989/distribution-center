package com.money.h5.controller;

import com.money.custom.entity.Goods;
import com.money.custom.entity.GoodsTag;
import com.money.custom.entity.Group;
import com.money.custom.entity.enums.GoodsTypeEnum;
import com.money.custom.entity.request.QueryGoodsRequest;
import com.money.custom.entity.request.QueryGoodsTagRequest;
import com.money.custom.service.GoodsService;
import com.money.custom.service.GoodsTagService;
import com.money.custom.service.GroupService;
import com.money.h5.entity.request.QueryByGroupIdRequest;
import com.money.h5.entity.request.QueryByIdRequest;
import com.money.h5.entity.request.QueryGoodsByTagIdRequest;
import com.money.h5.entity.response.QueryGoodsResponse;
import com.money.h5.entity.response.QueryGoodsTagResponse;
import com.money.h5.entity.response.QueryGroupDetailResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(description = "商铺详情")
@RequestMapping(value = "groupDetail")
@Controller
@CrossOrigin(allowCredentials = "true", maxAge = 3600)
public class GroupDetailController {

    @Autowired
    GroupService groupService;
    @Autowired
    GoodsTagService goodsTagService;
    @Autowired
    GoodsService goodsService;

    @ApiOperation(value = "门店详情")
    @ResponseBody
    @RequestMapping(value = "queryGroupDetail", method = RequestMethod.POST)
    public QueryGroupDetailResponse queryGroupDetail(@Valid @RequestBody QueryByIdRequest request, BindingResult bindingResult) {
        Group group = groupService.findById(request.getId());
        return new QueryGroupDetailResponse(group);
    }

    @ApiOperation(value = "门店商品类别列表")
    @ResponseBody
    @RequestMapping(value = "queryGoodsTag", method = RequestMethod.POST)
    public QueryGoodsTagResponse queryGoodsTag(@Valid @RequestBody QueryByGroupIdRequest request, BindingResult bindingResult) {
        QueryGoodsTagRequest queryGoodsTagRequest = new QueryGoodsTagRequest();
        queryGoodsTagRequest.setGroupId(request.getGroupId());
        List<GoodsTag> goodsTags = goodsTagService.selectSearchList(queryGoodsTagRequest);
        return new QueryGoodsTagResponse(goodsTags);
    }

    @ApiOperation(value = "按类别搜索商品列表")
    @ResponseBody
    @RequestMapping(value = "queryGoods", method = RequestMethod.POST)
    public QueryGoodsResponse queryGoods(@Valid @RequestBody QueryGoodsByTagIdRequest request, BindingResult bindingResult) {
        QueryGoodsRequest queryGoodsRequest = new QueryGoodsRequest();
        queryGoodsRequest.setGroupId(request.getGroupId());
        queryGoodsRequest.setGoodsTagId(request.getTagId());
        queryGoodsRequest.setGoodsTypeId(GoodsTypeEnum.SINGLE);
        List<Goods> goods = goodsService.selectSearchList(queryGoodsRequest);
        return new QueryGoodsResponse(goods);
    }

}
