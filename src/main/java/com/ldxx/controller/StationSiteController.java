package com.ldxx.controller;

import com.ldxx.bean.Accessory;
import com.ldxx.bean.CompanySite;
import com.ldxx.bean.StationSite;
import com.ldxx.bean.tUserInfo;
import com.ldxx.dao.StationSiteDao;
import com.ldxx.service.StationSiteService;
import com.ldxx.util.LDXXUtils;
import com.ldxx.util.MsgFormatUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("StationSite")
public class StationSiteController {

    @Autowired
    private StationSiteService service;

    @Resource
    private StationSiteDao stationSiteDao;

    @RequestMapping("/getAllStationSite")
    public List<StationSite> getAllStationSite(){
        List<StationSite> list = service.getAllStationSite();
        return list;
    }

    @RequestMapping("/getStationSiteById")
    public StationSite getStationSiteById(String id) {
        return service.getStationSiteById(id);
    }

    @RequestMapping("/getCompanyStationSite")
    public List<CompanySite> getCompanyStationSite() {
        return stationSiteDao.getCompanyStation();
    }

    @RequestMapping("/addStationSite")
    public String addStationSite(String stationSite,@RequestParam("file") MultipartFile file) throws IOException {
        JSONObject jsonObject = new JSONObject();
        Map<String,Class> map=new HashMap<>();
        JSONObject jsonObject2=JSONObject.fromObject(stationSite);
        StationSite StationSite=(StationSite)JSONObject.toBean(jsonObject2, StationSite.class,map);

        String id = LDXXUtils.getUUID12();

        String webApps=LDXXUtils.getWebAppFile();
        String path=webApps+id;
        File f=new File(path);
        if(!f.exists()){
            f.mkdirs();
        }
        if(file!=null){
            //List<Accessory> list=new ArrayList<>();
            //for(int i=0;i<file.length;i++){
                Accessory accessory=new Accessory();
                String fileName=file.getOriginalFilename();
                String filePath=path+File.separator+fileName;
                File f2=new File(filePath);
                file.transferTo(f2);
                accessory.setaId(id);
                accessory.setAcName(fileName);
                accessory.setAcUrl(id+File.separator+fileName);
                accessory.setaType("测点车道图");
                //list.add(accessory);
           // }
            StationSite.setFile(accessory);
        }

        int i;
        int iscountStationIp=0;
        if(StationSite.getStationIp()!=""&&StationSite.getStationIp()!=null){
            iscountStationIp=service.iscountStationIp(StationSite.getStationIp());
        }
        int iscountStationPort=0;
        if(StationSite.getStationPort()!=""&&StationSite.getStationPort()!=null){
            iscountStationPort=service.iscountStationPort(StationSite.getStationPort());
        }
        if(iscountStationIp>0){
            i=-3;
        }else if(iscountStationPort>0){
            i=-4;
        }else{
            StationSite.setsId(id);
            StationSite.setDelState(1);
            i= service.addStationSite(StationSite);
        }
        String daoMsg = MsgFormatUtils.getMsgByResult(i, "新增");
        jsonObject.put("resultMsg",daoMsg);
        jsonObject.put("daoMsg",i);
        jsonObject.put("obj",StationSite);
        return jsonObject.toString();
    }

    @RequestMapping("/updStationSite")
    public String updStationSite(String stationSite,@RequestParam("file") MultipartFile file) throws IOException {
        JSONObject jsonObject = new JSONObject();
        Map<String,Class> map=new HashMap<>();
        JSONObject jsonObject2=JSONObject.fromObject(stationSite);
        StationSite StationSite=(StationSite)JSONObject.toBean(jsonObject2, StationSite.class,map);
        String id=StationSite.getsId();
        String webApps=LDXXUtils.getWebAppFile();
        String path=webApps+id;;
        File f=new File(path);
        if(!f.exists()){
            f.mkdirs();
        }
        if(file!=null){
            //List<Accessory> list=new ArrayList<>();
            //for(int i=0;i<file.length;i++){
                Accessory accessory=new Accessory();
                String fileName=file.getOriginalFilename();
                String filePath=path+File.separator+fileName;
                File f2=new File(filePath);
                file.transferTo(f2);
                accessory.setaId(id);
                accessory.setAcName(fileName);
                accessory.setAcUrl(id+File.separator+fileName);
                accessory.setaType("测点车道图");
               // list.add(accessory);
            //}
            StationSite.setFile(accessory);
        }

        int i;
        int iscountStationIp=0;
        if(StationSite.getStationIp()!=""&&StationSite.getStationIp()!=null){
            iscountStationIp=service.xg_iscountStationIp(StationSite.getStationIp(),StationSite.getsId());
        }
        int iscountStationPort=0;
        if(StationSite.getStationPort()!=""&&StationSite.getStationPort()!=null){
            iscountStationPort=service.xg_iscountStationPort(StationSite.getStationPort(),StationSite.getsId());
        }
        if(iscountStationIp>0){
            i=-3;
        }else if(iscountStationPort>0){
            i=-4;
        }else{
            i= service.updStationSite(StationSite);
        }
        String daoMsg = MsgFormatUtils.getMsgByResult(i, "更新");
        jsonObject.put("resultMsg",daoMsg);
        jsonObject.put("daoMsg",i);
        jsonObject.put("obj",StationSite);
        return jsonObject.toString();
    }

    @RequestMapping("/delStationSite")
    public int delStationSite(String id) {
        return service.delStationSite(id);
    }


    @RequestMapping("/delCompaySite")
    public int delCompaySite(String id) {
        return stationSiteDao.delCompanySite(id);
    }

    @RequestMapping("/updateCompanySite")
    public Map<String,Object> updateCompanySite(CompanySite companySite) {
        Map<String,Object> map=new HashMap<>();

        int i = stationSiteDao.updateCompanySite(companySite);
        map.put("result",i);
        map.put("companySite",companySite);
        return map;
    }

    @RequestMapping("/addCompanySite")
    public String addCompanySite(@RequestBody CompanySite companySite) {
        JSONObject jsonObject=new JSONObject();
        String id = LDXXUtils.getUUID12();
        int i=stationSiteDao.addCompanySite(companySite);
        companySite.setStationName("");
        String daoMsg = MsgFormatUtils.getMsgByResult(i, "新增");
        jsonObject.put("resultMsg",daoMsg);
        jsonObject.put("daoMsg",i);
        jsonObject.put("obj",companySite);
        return jsonObject.toString();
    }

    /**
     * 通过登录人的站点端口号查询站点信息
     * @param stationPort
     * @return
     */
    @RequestMapping("/getStationSiteByPort")
    public List<StationSite> getStationSiteByPort(HttpSession session, String stationPort) {
        String zhandianduankouhao="";
        if(stationPort!=null){
            zhandianduankouhao=stationPort;
        }else{
            tUserInfo user = (tUserInfo) session.getAttribute("user");
            zhandianduankouhao = user.getStationPort();
        }
        return service.getStationSiteByPort(stationPort);
    }
}
