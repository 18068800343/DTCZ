package com.ldxx.dao;

import com.ldxx.bean.Report;
import com.ldxx.bean.tPermissions;

import java.util.List;

public interface ReportInfoDao {
    List<Report> getAllReportByCompanyName(String companyName,String startTime,String endTime,Integer timeType);
    int deleteById(String id);
}
