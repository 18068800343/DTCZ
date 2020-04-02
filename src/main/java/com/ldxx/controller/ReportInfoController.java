package com.ldxx.controller;


import com.ldxx.bean.Report;
import com.ldxx.bean.tPermissions;
import com.ldxx.bean.tUserInfo;
import com.ldxx.dao.ReportInfoDao;
import com.ldxx.dao.tPermissionsDao;
import com.ldxx.util.ReportUtil;
import com.ldxx.vo.tUserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("ReportInfoController")
public class ReportInfoController {
    @Resource
    private ReportInfoDao dao;
    @RequestMapping("/getAllReportInfoByCompanyName")
    public List<Report> getAllReportInfoByCompanyName(HttpSession session,String startTime,String endTime,String timeType){

        tUserInfoVo user = (tUserInfoVo) session.getAttribute("user");

        List<Report> reports = dao.getAllReportByCompanyName(user.getCompanyName(),startTime,endTime,timeType);
        return reports;

    }
    @RequestMapping("/deleteById")
    public int deleteById(HttpSession session,String id){
        return dao.deleteById(id);
    }
    @RequestMapping("/reportDayAndMonth")
    public int reportDayAndMonth(HttpSession session,String dayTime,String monthTime,String companyNames){
        tUserInfoVo tUserInfo = (tUserInfoVo) session.getAttribute("user");
        String companName = tUserInfo.getCompanyName();
        String[] list = companyNames.split(",");
        List<String> list1 = new ArrayList<>();
        list1.add(companName);
        List<String> companyNameList =  !"".equals(companyNames)&&null!=companyNames? Arrays.asList(companyNames.split(",")):list1;
        String cmdOp = "CreateDayReport.exe 3";
        ReportUtil.getInstance().reportByDayAndMonth(dayTime,monthTime,companyNameList,cmdOp);
        return 1;
    }

}
