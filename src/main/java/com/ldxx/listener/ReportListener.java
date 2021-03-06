package com.ldxx.listener;

import com.ldxx.Thread.AutoReportDayCallable;
import com.ldxx.Thread.AutoReportMonthCallable;
import com.ldxx.Thread.AutoReportWeekCallable;
import com.ldxx.config.Const;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class ReportListener implements ApplicationListener<ContextRefreshedEvent> {

    public static ScheduledThreadPoolExecutor schedule;
    public ReportListener() {
        schedule = new ScheduledThreadPoolExecutor(6);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        long waitTime = 21600;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate = new Date();
        String monthStr = simpleDateFormat.format(nowDate).substring(8, 10);
        String nowDateStr = simpleDateFormat.format(nowDate).substring(0, 10);

        Calendar c = Calendar.getInstance();
        c.setTime(nowDate);
        int weekday = c.get(Calendar.DAY_OF_WEEK);


        System.out.println(nowDateStr);
        try {
            Date date = simpleDateFormat.parse(nowDateStr + " 24:00:00");
            waitTime = (date.getTime() - nowDate.getTime()) / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (Const.REPORT_DAY) {
            schedule.scheduleWithFixedDelay(new AutoReportDayCallable(), waitTime + 5400, 86400, TimeUnit.SECONDS);
        }
        if (Const.REPORT_MONTH) {
            schedule.scheduleWithFixedDelay(new AutoReportMonthCallable(), waitTime + 5400, 86400, TimeUnit.SECONDS);
//            schedule.scheduleWithFixedDelay(new AutoReportMonthCallable(), 0, 86400, TimeUnit.SECONDS);
        }

        if (Const.REPORT_WEEK) {
//                schedule.scheduleWithFixedDelay(new AutoReportWeekCallable(), waitTime + 5400, 86400, TimeUnit.SECONDS);
            schedule.scheduleWithFixedDelay(new AutoReportWeekCallable(), 0, 86400, TimeUnit.SECONDS);
        }
    }
}
