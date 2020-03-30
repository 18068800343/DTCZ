package com.ldxx.controller;


import com.ldxx.bean.Report;
import com.ldxx.bean.tPermissions;
import com.ldxx.bean.tUserInfo;
import com.ldxx.dao.ReportInfoDao;
import com.ldxx.dao.tPermissionsDao;
import com.ldxx.vo.tUserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ReportInfoController")
public class ReportInfoController {
    @Resource
    private ReportInfoDao dao;
    @RequestMapping("/getAllReportInfoByCompanyName")
    public List<Report> getAllReportInfoByCompanyName(HttpSession session,String startTime,String endTime,Integer timeType){

        tUserInfoVo user = (tUserInfoVo) session.getAttribute("user");

        List<Report> reports = dao.getAllReportByCompanyName(user.getCompanyName(),startTime,endTime,timeType);
        return reports;

    }
    @RequestMapping("/deleteById")
    public int deleteById(HttpSession session,String id){
        return dao.deleteById(id);
    }

}
