package com.money.custom.entity.dto;

import com.money.custom.entity.GroupReservationPeriod;

public class ReservationCalendar {

    private String start;
    private String end;
    private Integer max;
    private Integer used;
    private Integer available;

    public ReservationCalendar() {}

    public ReservationCalendar(String date, Integer max, Integer used) {
        this.start = date;
        this.end = date;
        this.max = max;
        this.used = used;
        this.available = max - used;
        if (this.available < 0) {
            this.available = 0;
        }
    }

    public ReservationCalendar(GroupReservationPeriod period, long used) {
        this.start = period.getStartTime();
        this.end = period.getEndTime();
        this.max = period.getCnt();
        this.used = (int) used;
        this.available = max - (int) used;
        if (this.available < 0) {
            this.available = 0;
        }
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}
