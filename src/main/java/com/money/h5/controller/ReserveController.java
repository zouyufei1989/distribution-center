package com.money.h5.controller;

import com.money.custom.entity.Reservation;
import com.money.custom.entity.request.QueryReservationCalenderRequest;
import com.money.custom.service.ReservationService;
import com.money.framework.base.entity.ResponseBase;
import com.money.h5.entity.request.QueryReserveCalenderRequest;
import com.money.h5.entity.request.ReserveRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "预约",hidden = true)
@RequestMapping(value = "reserve")
@Controller
@CrossOrigin(allowCredentials = "true", maxAge = 3600)
public class ReserveController {

    @Autowired
    ReservationService reservationService;

    @ApiOperation(value = "预约日历", notes = "startDate至endDate预约情况；如startDate=endDate，则返回当天每个预约时间段的预约情况")
    @ResponseBody
    @RequestMapping(value = "queryReservationCalender", method = RequestMethod.POST)
    public ResponseBase queryReservationCalender(@Valid @RequestBody QueryReserveCalenderRequest request, BindingResult bindingResult) {
        QueryReservationCalenderRequest queryReservationCalenderRequest = new QueryReservationCalenderRequest();
        queryReservationCalenderRequest.setStartDate(request.getStartDate());
        queryReservationCalenderRequest.setEndDate(request.getEndDate());
        queryReservationCalenderRequest.setOrderId(request.getOrderId());
        return ResponseBase.success(reservationService.queryReservationCalender(queryReservationCalenderRequest));
    }


    @ApiOperation(value = "预约", notes = "orderId=62")
    @ResponseBody
    @RequestMapping(value = "reserve", method = RequestMethod.POST)
    public ResponseBase reserve(@Valid @RequestBody ReserveRequest request, BindingResult bindingResult) {
        reservationService.add(new Reservation(request));
        return ResponseBase.success();
    }



}
