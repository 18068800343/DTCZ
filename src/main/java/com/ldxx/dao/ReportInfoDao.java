package com.ldxx.dao;

import com.ldxx.bean.Report;
import com.ldxx.bean.tPermissions;

import java.util.List;

public interface ReportInfoDao {
    List<Report> getAllReportByCompanyName(String companyName,String startTime,String endTime,String timeType,String companyWhere);
    int deleteById(String id);
    int insertReport(Report report);
}
