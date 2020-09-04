package com.ldxx.Thread;

import com.ldxx.util.DateUtil;
import com.ldxx.util.ReportUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AutoReportWeekCallable implements Runnable {

    @Override
    public void run() {
        Date nowDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(nowDate);
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        if (weekday == 6) {
            //或缺前一天日期
            String date = DateUtil.getYestodayStr();
            //String date = "2020-08-31";
            //ReportUtil.getInstance().getReportPath("CreateDayReport.exe 2 "+date,1);
            ReportUtil.getInstance().getReportPath("CreateWeekReport.exe " + date, 3);
        }
    }
}
