package com.money.custom.service;

import com.money.custom.entity.Reservation;
import com.money.custom.entity.dto.ReservationCalendar;
import com.money.custom.entity.request.ChangeReservationStatusRequest;
import com.money.custom.entity.request.QueryReservationCalenderRequest;
import com.money.custom.entity.request.QueryReservationRequest;
import com.money.framework.base.service.BaseService;

import java.util.List;

public interface ReservationService extends BaseService {

    List<Reservation> selectSearchList(QueryReservationRequest request);

    int selectSearchListCount(QueryReservationRequest request);

    Reservation findById(String id);

    String add(Reservation item);

    List<String> changeStatus(ChangeReservationStatusRequest request);

    List<ReservationCalendar> queryReservationCalender(QueryReservationCalenderRequest request);


}
