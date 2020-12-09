package com.money.custom.controller;

import com.money.custom.entity.TreeNode;
import com.money.custom.service.TreeService;
import com.money.framework.base.entity.GridResponseBase;
import com.money.framework.base.entity.OperationalEntity;
import com.money.framework.base.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("tree")
public class TreeCategoryController extends BaseController {

    @Autowired
    TreeService treeService;

    @RequestMapping(value = "/getCategorys", method = RequestMethod.POST)
    @ResponseBody
    public GridResponseBase getCategorys(OperationalEntity entity) {
        List<TreeNode> treeNodes = treeService.buildCategory(entity.getLoginUser());
        return new GridResponseBase(treeNodes);
    }

}
