package com.money.h5.controller;

import com.money.custom.entity.Banner;
import com.money.custom.entity.Goods;
import com.money.custom.entity.Group;
import com.money.custom.entity.enums.GoodsTypeEnum;
import com.money.custom.entity.request.QueryGoodsRequest;
import com.money.custom.entity.request.QueryGridRequestBase;
import com.money.custom.entity.request.QueryGroupRequest;
import com.money.custom.service.BannerService;
import com.money.custom.service.GroupService;
import com.money.h5.entity.H5GridRequestBase;
import com.money.h5.entity.H5RequestBase;
import com.money.h5.entity.response.QueryBannerResponse;
import com.money.h5.entity.response.QueryGoodsResponse;
import com.money.h5.entity.response.QueryGroupResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(description = "首页")
@RequestMapping(value = "home")
@Controller
@CrossOrigin(allowCredentials = "true", maxAge = 3600)
public class HomeController {

    @Autowired
    BannerService bannerService;
    @Autowired
    GroupService groupService;

    @ApiOperation(value = "获取banner列表")
    @ResponseBody
    @RequestMapping(value = "queryBanners", method = RequestMethod.POST)
    public QueryBannerResponse queryBanners(@Valid @RequestBody H5RequestBase request, BindingResult bindingResult) {
        List<Banner> banners = bannerService.selectSearchList(new QueryGridRequestBase());
        return new QueryBannerResponse(banners);
    }

    @ApiOperation(value = "获取门店列表（可分页）")
    @ResponseBody
    @RequestMapping(value = "queryGroups", method = RequestMethod.POST)
    public QueryGroupResponse queryGroups(@Valid @RequestBody H5GridRequestBase request, BindingResult bindingResult) {
        QueryGroupRequest queryGroupRequest = new QueryGroupRequest();
        queryGroupRequest.copyPagerFromH5Request(request);
        List<Group> groups = groupService.selectSearchList(queryGroupRequest);
        int recordCount = groupService.selectSearchListCount(queryGroupRequest);
        return new QueryGroupResponse(recordCount, request.calTotalPage(recordCount), groups);

    }

}
