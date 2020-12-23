package com.money.custom.controller;

import com.money.custom.entity.request.QueryHistoryRequest;
import com.money.custom.service.HistoryService;
import com.money.framework.base.entity.GridResponseBase;
import com.money.framework.base.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/history/*")
public class HistoryController extends BaseController {


    @Autowired
    private HistoryService historyService;

    @ResponseBody
    @RequestMapping(value = "list/search")
    public GridResponseBase listSearch(QueryHistoryRequest request) {
        int recordCount = this.historyService.selectSearchListCount(request);
        return new GridResponseBase(recordCount, request.calTotalPage(recordCount), this.historyService.selectSearchList(request));
    }

}
