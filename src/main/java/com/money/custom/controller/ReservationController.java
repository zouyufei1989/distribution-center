package com.money.custom.controller;

import com.money.custom.entity.Reservation;
import com.money.custom.entity.request.QueryReservationCalenderByOrderIdRequest;
import com.money.custom.entity.request.QueryReservationRequest;
import com.money.custom.service.ReservationService;
import com.money.framework.base.annotation.VisitLogFlag;
import com.money.framework.base.entity.GridResponseBase;
import com.money.framework.base.entity.ResponseBase;
import com.money.framework.base.entity.VisitLogTypeEnum;
import com.money.framework.base.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@VisitLogFlag(module = "客户管理", resource = "到店预约记录")
@Controller
@RequestMapping("/reservation/*")
public class ReservationController extends BaseController {

    @Autowired
    private ReservationService reservationService;

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "list/search")
    public GridResponseBase listSearch(QueryReservationRequest request) {
        int recordCount = this.reservationService.selectSearchListCount(request);
        return new GridResponseBase(recordCount, request.calTotalPage(recordCount), this.reservationService.selectSearchList(request));
    }

    @VisitLogFlag(type = VisitLogTypeEnum.READ)
    @ResponseBody
    @RequestMapping(value = "findById", method = RequestMethod.POST)
    public ResponseBase findById(String id) {
        return ResponseBase.success(this.reservationService.findById(id));
    }

    @VisitLogFlag(type = VisitLogTypeEnum.EDIT)
    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ResponseBase edit(@RequestBody Reservation reservation) {
        this.reservationService.edit(reservation);
        return ResponseBase.success();
    }

    @ResponseBody
    @RequestMapping(value = "queryReservationCalender", method = RequestMethod.POST)
    public ResponseBase queryReservationCalender(QueryReservationCalenderByOrderIdRequest request) {
        return ResponseBase.success(reservationService.queryReservationCalender(request));
    }

}
