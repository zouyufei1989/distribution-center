package com.money.h5.controller;

import com.money.custom.entity.Reservation;
import com.money.custom.entity.request.QueryReservationCalenderRequest;
import com.money.custom.entity.request.QueryReservationRequest;
import com.money.custom.service.ReservationService;
import com.money.framework.base.entity.ResponseBase;
import com.money.h5.entity.H5GridRequestBase;
import com.money.h5.entity.request.CancelReserveRequest;
import com.money.h5.entity.request.QueryReserveCalenderRequest;
import com.money.h5.entity.request.ReserveRequest;
import com.money.h5.entity.response.QueryMyReservationResponse;
import com.money.h5.service.H5ReservationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(description = "预约")
@RequestMapping(value = "h5Reservation")
@Controller
@CrossOrigin(allowCredentials = "true", maxAge = 3600)
public class H5ReservationController {

    @Autowired
    ReservationService reservationService;
    @Autowired
    H5ReservationService h5ReservationService;

    @ApiOperation(value = "预约日历", notes = "startDate至endDate预约情况；如startDate=endDate，则返回当天每个预约时间段的预约情况",hidden = true)
    @ResponseBody
    @RequestMapping(value = "queryReservationCalender", method = RequestMethod.POST)
    public ResponseBase queryReservationCalender(@Valid @RequestBody QueryReserveCalenderRequest request, BindingResult bindingResult) {
        QueryReservationCalenderRequest queryReservationCalenderRequest = new QueryReservationCalenderRequest();
        queryReservationCalenderRequest.setStartDate(request.getStartDate());
        queryReservationCalenderRequest.setEndDate(request.getEndDate());
        queryReservationCalenderRequest.setOrderId(request.getOrderId());
        return ResponseBase.success(reservationService.queryReservationCalender(queryReservationCalenderRequest));
    }


    @ApiOperation(value = "预约", notes = "orderId=62",hidden = true)
    @ResponseBody
    @RequestMapping(value = "reserve", method = RequestMethod.POST)
    public ResponseBase reserve(@Valid @RequestBody ReserveRequest request, BindingResult bindingResult) {
        reservationService.add(new Reservation(request));
        return ResponseBase.success();
    }

    @ApiOperation(value = "取消预约", notes = "reserveId=1",hidden = true)
    @ResponseBody
    @RequestMapping(value = "cancel", method = RequestMethod.POST)
    public ResponseBase cancel(@Valid @RequestBody CancelReserveRequest request, BindingResult bindingResult) {
        h5ReservationService.cancelReserve(request);
        return ResponseBase.success();
    }

    @ApiOperation(value = "我的预约（可分页）", notes = "openId=oSpLm5PPyJv5tO-HCGnH5mGUR6lA",hidden = true)
    @ResponseBody
    @RequestMapping(value = "queryMyReservations", method = RequestMethod.POST)
    public QueryMyReservationResponse queryMyReservations(@Valid @RequestBody H5GridRequestBase request, BindingResult bindingResult) {
        QueryReservationRequest queryReservationRequest = new QueryReservationRequest();
        queryReservationRequest.setOpenId(request.getOpenId());
        List<Reservation> reservations = reservationService.selectSearchList(queryReservationRequest);
        Integer recordCount = reservationService.selectSearchListCount(queryReservationRequest);
        return new QueryMyReservationResponse(recordCount, request.calTotalPage(recordCount), reservations);
    }
}
