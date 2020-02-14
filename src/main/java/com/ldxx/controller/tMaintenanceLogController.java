package com.ldxx.controller;

import com.ldxx.bean.Accessory;
import com.ldxx.bean.tMaintenanceLog;
import com.ldxx.bean.tUserInfo;
import com.ldxx.service.tMaintenanceLogService;
import com.ldxx.util.LDXXUtils;
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

    @RequestMapping("/addtMaintenanceLog")
    public Map<String,Object> addtMaintenanceLog(tMaintenanceLog tMaintenanceLog, @RequestParam("file") MultipartFile[] file, HttpSession session) throws IOException {
        Map<String,Object> map=new HashMap<>();
        String id= LDXXUtils.getUUID12();
        tMaintenanceLog.setLogId(id);
        tMaintenanceLog.setDelState(1+"");
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        tMaintenanceLog.setLogUser(user.getUsrId());
        tMaintenanceLog.setUsrName(user.getUsrName());
        String webApps=LDXXUtils.getWebAppFile();
        String path=webApps+id;
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
                accessory.setAcUrl(id+File.separator+fileName);
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
        String id= tMaintenanceLog.getLogId();
        String webApps=LDXXUtils.getWebAppFile();
        String path=webApps+id;
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
                accessory.setAcUrl(id+File.separator+fileName);
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
}
