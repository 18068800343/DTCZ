package com.ldxx.controller;


import com.alibaba.fastjson.JSONObject;
import com.ldxx.bean.*;
import com.ldxx.dao.AccessoryDao;
import com.ldxx.dao.ReportInfoDao;
import com.ldxx.dao.tPermissionsDao;
import com.ldxx.util.GetThisTimeUtils;
import com.ldxx.util.LDXXUtils;
import com.ldxx.util.ReportUtil;
import com.ldxx.vo.ImgUrlPrefixVo;
import com.ldxx.vo.tUserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("ReportInfoController")
public class ReportInfoController {
    @Resource
    private ReportInfoDao dao;
    @Autowired
    ImgUrlPrefixVo imgUrlPrefixVo;

    @RequestMapping("/getAllReportInfoByCompanyName")
    public List<Report> getAllReportInfoByCompanyName(HttpSession session,String startTime,String endTime,String timeType,String companyName){

        tUserInfoVo user = (tUserInfoVo) session.getAttribute("user");

        List<Report> reports = dao.getAllReportByCompanyName(user.getCompanyName(),startTime,endTime,timeType,companyName);
        return reports;

    }
    @RequestMapping("/deleteById")
    public int deleteById(HttpSession session, String id) {
        return dao.deleteById(id);
    }

    @RequestMapping("/reportDayAndMonth")
    public int reportDayAndMonth(HttpSession session, String dayTime, String monthTime, String companyNames, String weekTime) {
        tUserInfoVo tUserInfo = (tUserInfoVo) session.getAttribute("user");
        String companName = tUserInfo.getCompanyName();
        String[] list = companyNames.split(",");
        List<String> list1 = new ArrayList<>();
        list1.add(companName);
        List<String> companyNameList = !"".equals(companyNames) && null != companyNames ? Arrays.asList(companyNames.split(",")) : list1;
        String cmdOp = "CreateDayReport.exe 3";
        ReportUtil.getInstance().reportByDayAndMonthAndWeek(dayTime, monthTime, weekTime, companyNameList, cmdOp);
        return 1;
    }

    @RequestMapping("/shangchuanzhoubao")
    public String addtMaintenanceLog( @RequestParam("file") MultipartFile file) throws IOException, ParseException {
        JSONObject jsonObject=new JSONObject();
        String id= LDXXUtils.getUUID32();
        //String webApps=imgUrlPrefixVo.getFujianUrl();
        String path="d:\\work";
        File f=new File(path);
        if(!f.exists()){
            f.mkdirs();
        }
        Report Report=new Report();
        Report.setrId(id);
        List<Accessory> list=new ArrayList<>();
        if(file!=null){
            //Accessory accessory=new Accessory();
            String fileName=file.getOriginalFilename();
            String filePath=path+File.separator+fileName;
            File f2=new File(filePath);
            file.transferTo(f2);
           /* accessory.setaId(id);
            accessory.setAcName(fileName);
            accessory.setAcUrl(fileName);
            accessory.setaType("周报告");
            list.add(accessory);*/

            Report.setCompanyName("控股");
            Report.setReportName(fileName);
            Report.setReportUrl(filePath);
            Report.setReportStatus(1);
            Report.setTimeType(3);
            String time = GetThisTimeUtils.getDateTime();
            SimpleDateFormat ft1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = ft1.parse(time);
            Report.setReportTime(date);
        }
        int i=dao.insertReport(Report);
        /*if(i>0){
            i=accessoryDao.addAccessory(list);
        }*/
        jsonObject.put("obj",Report);
        jsonObject.put("flag",i);
        return jsonObject.toJSONString();
    }

}
