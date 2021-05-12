package com.money.h5.controller;

import com.money.custom.entity.Banner;
import com.money.custom.entity.Goods;
import com.money.custom.entity.Group;
import com.money.custom.entity.enums.GoodsTypeEnum;
import com.money.custom.entity.enums.ResponseCodeEnum;
import com.money.custom.entity.request.QueryGoodsRequest;
import com.money.custom.entity.request.QueryGridRequestBase;
import com.money.custom.entity.request.QueryGroupRequest;
import com.money.custom.service.BannerService;
import com.money.custom.service.GroupService;
import com.money.framework.base.entity.GridResponseBase;
import com.money.framework.base.entity.ResponseBase;
import com.money.h5.entity.H5GridRequestBase;
import com.money.h5.entity.H5RequestBase;
import com.money.h5.entity.request.QueryByIdRequest;
import com.money.h5.entity.request.QueryByOperationalIdRequest;
import com.money.h5.entity.response.QueryBannerResponse;
import com.money.h5.entity.response.QueryGoodsResponse;
import com.money.h5.entity.response.QueryGroupResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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

    @ApiOperation(value = "获取门店列表（可分页）", notes = "传入当前登录用户的id，则返回该用户所属门店信息；否则返回全部门店")
    @ResponseBody
    @RequestMapping(value = "queryGroups", method = RequestMethod.POST)
    public QueryGroupResponse queryGroups(@Valid @RequestBody QueryByOperationalIdRequest request, BindingResult bindingResult) {
        QueryGroupRequest queryGroupRequest = new QueryGroupRequest();
        queryGroupRequest.copyPagerFromH5Request(request);
        queryGroupRequest.setCustomerGroupId(request.getId());
        List<Group> groups = groupService.selectSearchList(queryGroupRequest);
        int recordCount = groupService.selectSearchListCount(queryGroupRequest);

        return new QueryGroupResponse(recordCount, request.calTotalPage(recordCount), groups);

    }

}
