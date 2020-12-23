package com.money.h5.controller;

import com.money.custom.entity.Group;
import com.money.custom.service.GroupService;
import com.money.h5.entity.request.QueryByIdRequest;
import com.money.h5.entity.response.QueryGroupDetailResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "商铺详情")
@RequestMapping(value = "groupDetail")
@Controller
@CrossOrigin(allowCredentials = "true", maxAge = 3600)
public class GroupDetailController {

    @Autowired
    GroupService groupService;

    @ApiOperation(value = "门店详情")
    @ResponseBody
    @RequestMapping(value = "queryGroupDetail", method = RequestMethod.POST)
    public QueryGroupDetailResponse queryGroupDetail(@Valid @RequestBody QueryByIdRequest request, BindingResult bindingResult) {
        Group group = groupService.findById(request.getId());
        return new QueryGroupDetailResponse(group);
    }

}
