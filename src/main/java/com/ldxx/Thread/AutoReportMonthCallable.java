package com.ldxx.Thread;

import com.ldxx.util.DateUtil;
import com.ldxx.util.ReportUtil;

import java.util.concurrent.Callable;

public class AutoReportMonthCallable implements Runnable {

    @Override
    public void run() {
        //或缺前一天日期
        String date = DateUtil.getLastMonthStr();
        //ReportUtil.getInstance().getReportPath("CreateDayReport.exe 2 "+date,1);
        ReportUtil.getInstance().getReportPath("CreateMonthReport.exe " + date, 2);
    }

    public static void main(String[] args) {
        Thread t = new Thread(new AutoReportMonthCallable());
        t.start();
    }
}
