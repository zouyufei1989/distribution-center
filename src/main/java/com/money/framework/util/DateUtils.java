package com.money.framework.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

public final class DateUtils {
    final static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    private final static DateTimeFormatter STF_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final static DateTimeFormatter DAY_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {

        System.out.println(JSON.toJSONString(DateUtils.convertWeekByDate(new Date())));
        System.out.println(JSON.toJSONString(DateUtils.convertMonthByDate(new Date())));
        System.out.println(JSON.toJSONString(DateUtils.convertYearByDate(new Date())));

    }

    public static Date parse(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static String format(Date date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public final static String now() {
        try {
            return format(new Date(), "yyyy-MM-dd");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public final static long getTimeStamp() {
        try {
            Date date = new Date();
            long time = date.getTime();
            return time;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return 0;
    }

    public static String addDay(String date, int day, String format) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(parse(date, format));
        cd.add(Calendar.DATE, day);
        return format(cd.getTime(), format);
    }

    public static String nextMonth() {
        return nextMonth(1);
    }

    public static String nextMonth(int n) {
        return LocalDate.now().plusMonths(n).format(DateTimeFormatter.ofPattern("yyyy-MM"));
    }

    public static String nowMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
    }

    public static String nowDate() {
        return LocalDate.now().format(STF_DATE);
    }

    public static Integer nowWeekDay() {
        return LocalDate.now().getDayOfWeek().getValue();
    }

    // 获取本月最后一天
    public static String getNowMonthEnd() {
        return Year.now().atMonth(MonthDay.now().getMonth()).atEndOfMonth().format(DAY_FORMATTER);
    }

    // 获取本月最后一天
    public static String getMonthEnd(String yyyyMM) {
        if (StringUtils.isEmpty(yyyyMM)) {
            return StringUtils.EMPTY;
        }
        return YearMonth.parse(yyyyMM, DateTimeFormatter.ofPattern("yyyy-MM")).atEndOfMonth().format(DAY_FORMATTER);
    }

    // 获取下月月初时间
    public static String getNextMonthStart() {
        LocalDate nextMonth = LocalDate.now().plusMonths(1);
        return LocalDate.of(nextMonth.getYear(), nextMonth.getMonth(), 1).format(DAY_FORMATTER);
    }

    // 获取下月月末时间
    public static String getNextMonthEnd() {
        LocalDate nextMonth = LocalDate.now().plusMonths(1);
        return LocalDate.of(nextMonth.getYear(), nextMonth.getMonth(), nextMonth.lengthOfMonth()).format(DAY_FORMATTER);
    }

    public static String nextDayStr() {
        return nextNDayStr(1);
    }

    public static String nextNDayStr(Integer day) {
        return LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static Date nextNDay(Integer day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, day);
        return cal.getTime();
    }

    public static String nowTime() {
        return format(new Date(), "HH:mm");
    }

    public static String nowDateTime() {
        return format(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getTimeFromDateTime(String dateTime) {
        Date date = parse(dateTime, "yyyy-MM-dd HH:mm:ss");
        return format(date, "HH:mm:ss");
    }

    /**
     * 根据日期计算所在周的上下界
     *
     * @param time
     */
    public static Map<String, String> convertWeekByDate(Date time) {
        Map<String, String> map = new HashMap<String, String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式  
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天  
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        //System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期  
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
        int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天  
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值  
        String imptimeBegin = sdf.format(cal.getTime());
        // System.out.println("所在周星期一的日期：" + imptimeBegin);  
        cal.add(Calendar.DATE, 6);
        String imptimeEnd = sdf.format(cal.getTime());
        // System.out.println("所在周星期日的日期：" + imptimeEnd);  

        map.put("startDate", imptimeBegin);

        map.put("endDate", imptimeEnd);

        return map;
    }

    public static Map<String, String> convertMonthByDate(Date date) {
        Map<String, String> map = new HashMap<String, String>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //calendar.add(Calendar.MONTH, -1);  
        Date theDate = calendar.getTime();
        // 上个月第一天  
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        // 上个月最后一天  
        calendar.add(Calendar.MONTH, 1); // 加一个月  
        calendar.set(Calendar.DATE, 1); // 设置为该月第一天  
        calendar.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天  
        String day_last = df.format(calendar.getTime());
        map.put("startDate", day_first);
        map.put("endDate", day_last);
        return map;
    }

    public static Map<String, String> convertYearByDate(Date date) {
        Map<String, String> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);

        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();

        calendar.set(Calendar.YEAR, year + 1);
        calendar.add(Calendar.DATE, -1);
        Date lastYearFirst = calendar.getTime();

        map.put("startDate", sdf.format(currYearFirst));
        map.put("endDate", sdf.format(lastYearFirst));
        return map;
    }

    public static int getTimeDurationSec(String startTime, String endTime) {
        long diff = 0L;
        Time startObj = Time.valueOf(startTime);
        Time endObj = Time.valueOf(endTime);
        Time dayLineTime = Time.valueOf("24:00:00");
        Time dayStartTime = Time.valueOf("00:00:00");
        if (endObj.getTime() >= startObj.getTime()) {
            diff = endObj.getTime() - startObj.getTime();
        } else {
            diff = dayLineTime.getTime() - startObj.getTime() + (endObj.getTime() - dayStartTime.getTime());
        }

        return (int) diff / 1000;
    }

}
