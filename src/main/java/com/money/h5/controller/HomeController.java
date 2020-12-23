package com.money.h5.controller;

import com.money.custom.entity.Banner;
import com.money.custom.entity.request.QueryGridRequestBase;
import com.money.custom.service.BannerService;
import com.money.h5.entity.H5RequestBase;
import com.money.h5.entity.response.QueryBannerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "首页")
@RequestMapping(value = "home")
@Controller
@CrossOrigin(allowCredentials = "true", maxAge = 3600)
public class HomeController {

    @Autowired
    BannerService bannerService;

    @ApiOperation(value = "获取banner列表")
    @ResponseBody
    @RequestMapping(value = "queryBanners", method = RequestMethod.POST)
    public QueryBannerResponse queryBanners(@Valid @RequestBody H5RequestBase request, BindingResult bindingResult) {
        List<Banner> banners = bannerService.selectSearchList(new QueryGridRequestBase());
        return new QueryBannerResponse(banners);
    }

}
