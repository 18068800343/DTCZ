package com.ldxx.controller;

import com.ldxx.bean.Accessory;
import com.ldxx.bean.tMaintenanceLog;
import com.ldxx.bean.tMaintenanceReplaceLog;
import com.ldxx.bean.tUserInfo;
import com.ldxx.dao.tMaintenanceLogDao;
import com.ldxx.service.tMaintenanceLogService;
import com.ldxx.util.LDXXUtils;
import com.ldxx.vo.ImgUrlPrefixVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("tMaintenanceLog")
@RestController
public class tMaintenanceLogController {

    @Autowired
    private tMaintenanceLogService service;
    @Autowired
    private tMaintenanceLogDao dao;
    @Autowired
    ImgUrlPrefixVo imgUrlPrefixVo;

    @RequestMapping("/addtMaintenanceLog")
    public Map<String,Object> addtMaintenanceLog(tMaintenanceLog tMaintenanceLog, @RequestParam("file") MultipartFile[] file, HttpSession session) throws IOException {
        Map<String,Object> map=new HashMap<>();
        String id= LDXXUtils.getUUID12();
        tMaintenanceLog.setLogId(id);
        tMaintenanceLog.setDelState(1);
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        tMaintenanceLog.setLogUser(user.getUsrId());
        tMaintenanceLog.setUsrName(user.getUsrName());
        String webApps=LDXXUtils.getWebAppFile(imgUrlPrefixVo.getFujianUrl());
        String path=webApps;
        File f=new File(path);
        if(!f.exists()){
            f.mkdirs();
        }
        if(file.length>0){
            List<Accessory> list=new ArrayList<>();
            for(int i=0;i<file.length;i++){
                Accessory accessory=new Accessory();
                String fileName=file[i].getOriginalFilename();
                String filePath=path+File.separator+fileName;
                File f2=new File(filePath);
                file[i].transferTo(f2);
                accessory.setaId(id);
                accessory.setAcName(fileName);
                accessory.setAcUrl(fileName);
                accessory.setaType("维护日志");
                list.add(accessory);
            }
            tMaintenanceLog.setAccessory(list);
        }
        int i=service.addtMaintenanceLog(tMaintenanceLog);
        map.put("result", i);
        map.put("tMaintenanceLog", tMaintenanceLog);
        return map;
    }

    @RequestMapping("/deltMaintenanceLog")
    public int deltMaintenanceLog(String id){
        int i=service.deltMaintenanceLog(id);
        return i;
    }

    @RequestMapping("/updtMaintenanceLog")
    public Map<String,Object> updtMaintenanceLog(tMaintenanceLog tMaintenanceLog, @RequestParam("file") MultipartFile[] file, HttpSession session) throws IOException {
        Map<String,Object> map=new HashMap<>();
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        tMaintenanceLog.setLogUser(user.getUsrId());
        tMaintenanceLog.setUsrName(user.getUsrName());
        if("0".equals(tMaintenanceLog.getIsChangeEquipment())){
            tMaintenanceLog.setIsChangeEquipmentName("否");
        }else{
            tMaintenanceLog.setIsChangeEquipmentName("是");
        }

        String id= tMaintenanceLog.getLogId();
        String webApps=LDXXUtils.getWebAppFile(imgUrlPrefixVo.getFujianUrl());
        String path=webApps;
        File f=new File(path);
        if(!f.exists()){
            f.mkdirs();
        }
        if(file.length>0){
            List<Accessory> list=new ArrayList<>();
            for(int i=0;i<file.length;i++){
                Accessory accessory=new Accessory();
                String fileName=file[i].getOriginalFilename();
                String filePath=path+File.separator+fileName;
                File f2=new File(filePath);
                file[i].transferTo(f2);
                accessory.setaId(id);
                accessory.setAcName(fileName);
                accessory.setAcUrl(fileName);
                accessory.setaType("维护日志");
                list.add(accessory);
            }
            tMaintenanceLog.setAccessory(list);
        }
        int i=service.updtMaintenanceLog(tMaintenanceLog);
        map.put("result", i);
        map.put("tMaintenanceLog", tMaintenanceLog);
        return map;
    }

    @RequestMapping("/getAlltMaintenanceLog")
    public List<tMaintenanceLog> getAlltMaintenanceLog(){
        List<tMaintenanceLog> tMaintenanceLog=service.getAlltMaintenanceLog();
        return tMaintenanceLog;
    }


    //---------------------------------------------------  设备更换记录表  -----------------------------------------------------------------------------

    @RequestMapping("/addtMaintenanceReplaceLog")
    public Map<String,Object> addtMaintenanceReplaceLog(tMaintenanceReplaceLog tMaintenanceReplaceLog, HttpSession session) {
        Map<String,Object> map=new HashMap<>();
        String id= LDXXUtils.getUUID12();
        tMaintenanceReplaceLog.setLogReplaceId(id);
        tMaintenanceReplaceLog.setDelState(1);
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        tMaintenanceReplaceLog.setLogReplaceUser(user.getUsrId());
        tMaintenanceReplaceLog.setUsrName(user.getUsrName());

        int i=dao.addtMaintenanceReplaceLog(tMaintenanceReplaceLog);
        map.put("result", i);
        map.put("tMaintenanceReplaceLog", tMaintenanceReplaceLog);
        return map;
    }

    @RequestMapping("/updtMaintenanceReplaceLog")
    public Map<String,Object> updtMaintenanceReplaceLog(tMaintenanceReplaceLog tMaintenanceReplaceLog, HttpSession session) {
        Map<String,Object> map=new HashMap<>();
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        tMaintenanceReplaceLog.setLogReplaceUser(user.getUsrId());
        tMaintenanceReplaceLog.setUsrName(user.getUsrName());

        int i=dao.updtMaintenanceReplaceLog(tMaintenanceReplaceLog);
        map.put("result", i);
        map.put("tMaintenanceReplaceLog", tMaintenanceReplaceLog);
        return map;
    }

    @RequestMapping("/getAlltMaintenanceReplaceLog")
    public List<tMaintenanceReplaceLog> getAlltMaintenanceReplaceLog(){
        List<tMaintenanceReplaceLog> tMaintenanceReplaceLog=dao.getAlltMaintenanceReplaceLog();
        return tMaintenanceReplaceLog;
    }

    @RequestMapping("/deltMaintenanceReplaceLog")
    public int deltMaintenanceReplaceLog(String id){
        int i=dao.deltMaintenanceReplaceLog(id);
        return i;
    }
}
