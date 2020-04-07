package com.ldxx.Thread;

import com.ldxx.util.DateUtil;
import com.ldxx.util.GetThisTimeUtils;
import com.ldxx.util.ReportUtil;

import java.util.concurrent.Callable;

public class AutoReportDayCallable implements Runnable {

    @Override
    public void run() {

        //或缺前一天日期
        String date = DateUtil.getYestodayStr();
         //ReportUtil.getInstance().getReportPath("CreateDayReport.exe 2 "+date,1);
        ReportUtil.getInstance().getReportPath("CreateDayReport.exe "+date,1);
    }

    public static void main(String[] args) {
        new ReportUtil().getReportPath("CreateDayReport.exe "+ DateUtil.getYestodayStr(),1);
    }
}
