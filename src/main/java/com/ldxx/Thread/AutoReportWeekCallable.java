package com.ldxx.Thread;

import com.ldxx.util.DateUtil;
import com.ldxx.util.ReportUtil;

public class AutoReportWeekCallable implements Runnable {

    @Override
    public void run() {
        //或缺前一天日期
        String date = DateUtil.getYestodayStr();
        //String date = "2020-05-25";
        //ReportUtil.getInstance().getReportPath("CreateDayReport.exe 2 "+date,1);
        ReportUtil.getInstance().getReportPath("CreateWeekReport.exe " + date, 3);
    }
}
