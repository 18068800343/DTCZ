package com.ldxx.Thread;

import com.ldxx.util.DateUtil;
import com.ldxx.util.ReportUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class AutoReportMonthCallable implements Runnable {

    @Override
    public void run() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate = new Date();
        String monthStr = simpleDateFormat.format(nowDate).substring(8, 10);
        if (monthStr.equals("01")) {
            //或缺前一天日期
            String date = DateUtil.getLastMonthStr();
            //ReportUtil.getInstance().getReportPath("CreateDayReport.exe 2 "+date,1);
            ReportUtil.getInstance().getReportPath("CreateMonthReport.exe " + date, 2);
        }
    }

    public static void main(String[] args) {
        Thread t = new Thread(new AutoReportMonthCallable());
        t.start();
    }
}
