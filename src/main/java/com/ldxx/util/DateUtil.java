package com.ldxx.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static String getDateStrByPattern(String pattern, Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String string = simpleDateFormat.format(date);
        return string;
    }

    public static String getYestodayStr(){
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -1);
        date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format.format(date);
        return dateString;
    }

    public static String getLastMonthStr() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
        date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        String dateString = format.format(date);
        return dateString;
    }

    public static String getNowMonthStr() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        String dateString = format.format(date);
        return dateString;
    }

    public static Date geLastWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, -7);
        return cal.getTime();
    }

    public static Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    public static Date getNextWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, 7);
        return cal.getTime();
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse("2017-09-10");
            System.out.println("今天是" + sdf.format(date));
            System.out.println("上周一" + sdf.format(geLastWeekMonday(date)));
            System.out.println("本周一" + sdf.format(getThisWeekMonday(date)));
            System.out.println("下周一" + sdf.format(getNextWeekMonday(date)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
