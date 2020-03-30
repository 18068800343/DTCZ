package com.ldxx.controller;

import com.alibaba.fastjson.JSONObject;
import com.ldxx.bean.tUserInfo;
import com.ldxx.dao.tVehicleOverweightDao;
import com.ldxx.service.TVehicleOverweightService;
import com.ldxx.util.HandleRemoteDatas;
import com.ldxx.util.MsgFormatUtils;
import com.ldxx.vo.tWimMsgVo;
import com.ldxx.vo.ImgUrlPrefixVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("tVehicleOverweight")
@RestController
public class TVehicleOverweightController {

    @Autowired
    ImgUrlPrefixVo imgUrlPrefixVo;
    @Autowired
    private TVehicleOverweightService service;
    @Autowired
    private tVehicleOverweightDao tdao;

    @RequestMapping("/getimgUrlPrefix")
    public String getimgUrlPrefix(){
        JSONObject jsonObject=new JSONObject();
        String imgUrlPrefix=imgUrlPrefixVo.getImgUrlPrefix();
        jsonObject.put("imgUrlPrefix",imgUrlPrefix);
        return jsonObject.toString();
    }

    @RequestMapping("/getMeiRiChaoZaiShujuByStationPortCondition")
    public List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPortCondition(String stationPorts,String startTime,String endTime, HttpSession session){
        String zhandianduankouhao="";
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        if(stationPorts!=null&&stationPorts!=""){
            zhandianduankouhao=stationPorts;
        }else{
            if(user!=null){
                zhandianduankouhao = user.getStationPort();
            }
        }
        return service.getMeiRiChaoZaiShujuByStationPortCondition(zhandianduankouhao,startTime,endTime);
    }

    @RequestMapping("/getMeiRiChaoZaiShujuByStationPortAlreadychuli")
    public List<tWimMsgVo> getMeiRiChaoZaiShujuByStationPortAlreadychuli(String stationPorts,String startTime,String endTime, HttpSession session){
        String zhandianduankouhao="";
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        if(stationPorts!=null&&stationPorts!=""){
            zhandianduankouhao=stationPorts;
        }else{
            if(user!=null){
                zhandianduankouhao = user.getStationPort();
            }
        }
        return service.getMeiRiChaoZaiShujuByStationPortAlreadychuli(zhandianduankouhao,startTime,endTime);
    }


    @RequestMapping("/getMeiRiGuanJianChaoZHongShujuByStationPort")
    public List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPort(String stationPorts,Integer lv,String startTime,String endTime, HttpSession session){
        String zhandianduankouhao="";
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        if(stationPorts!=null&&stationPorts!=""){
            zhandianduankouhao=stationPorts;
        }else{
            if(user!=null){
                zhandianduankouhao = user.getStationPort();
            }
        }
        return service.getMeiRiGuanJianChaoZHongShujuByStationPort(zhandianduankouhao,lv,startTime,endTime);
    }

    @RequestMapping("/getMeiRiGuanJianChaoZHongShujuByStationPortAlreadychuli")
    public List<tWimMsgVo> getMeiRiGuanJianChaoZHongShujuByStationPortAlreadychuli(String stationPorts,Integer lv,String startTime,String endTime, HttpSession session){
        String zhandianduankouhao="";
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        if(stationPorts!=null&&stationPorts!=""){
            zhandianduankouhao=stationPorts;
        }else{
            if(user!=null){
                zhandianduankouhao = user.getStationPort();
            }
        }
        return service.getMeiRiGuanJianChaoZHongShujuByStationPortAlreadychuli(zhandianduankouhao,lv,startTime,endTime);
    }

    @RequestMapping("/addtVehicleOverweight")
    public String addtVehicleOverweight(@RequestBody tWimMsgVo tWimMsg) throws Exception {
        String urlPrefix = getimgUrlPrefix();
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(urlPrefix);
        //预警动态称重接口
        String yujingUrl = imgUrlPrefixVo.getYujingUrl();
        String url="D:\\Program Files\\DTCZ\\DTCZimage\\port"+tWimMsg.getStationIP()+"\\"+tWimMsg.getIdLocal()+"-Image.jpg";
        String url2="D:\\Program Files\\DTCZ\\DTCZimage\\port"+tWimMsg.getStationIP()+"\\"+tWimMsg.getIdLocal()+"-PlateImg.jpg";
        //String url="http://"+jsonObject.get("imgUrlPrefix").toString()+"/port"+tWimMsg.getStationIP()+"/"+tWimMsg.getIdLocal()+"-Image.jpg";//车辆正面图片
        //String url2="http://"+jsonObject.get("imgUrlPrefix").toString()+"/port"+tWimMsg.getStationIP()+"/"+tWimMsg.getIdLocal()+"-PlateImg.jpg";//车牌照图片

        Double OverWeightRatio=tWimMsg.getOverWeightRatio()*100;
        String plate = tWimMsg.getPlate();
        if(OverWeightRatio>=5&&!"无车牌".equals(plate)){
            HandleRemoteDatas.HandleRemoteDatas(url,url2,tWimMsg,yujingUrl);//处理图片流并调用远程接口
        }


       int i=service.addtVehicleOverweightByidLocal(tWimMsg.getIdLocal());
        String daoMsg = MsgFormatUtils.getMsgByResult(i, "处理");
        jsonObject.put("resultMsg",daoMsg);
        jsonObject.put("daoMsg",i);
        jsonObject.put("obj",tWimMsg);
        return jsonObject.toString();
    }


    @RequestMapping("/addtVehicleOverweightList")
    public String addtVehicleOverweightList(String startTime,String endTime ,HttpSession session,Integer lv,String idLocals) throws Exception {
        String urlPrefix = getimgUrlPrefix();
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(urlPrefix);
        /*String zhandianduankouhao="";
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        if(user!=null){
            zhandianduankouhao = user.getStationPort();
        }*/
        List<tWimMsgVo> tWimMsgVoList=tdao.getMeiRiGuanJianChaoZaiShujuByidLocals(idLocals,lv);

        //预警动态称重接口
        String yujingUrl = imgUrlPrefixVo.getYujingUrl();
        for (tWimMsgVo list:tWimMsgVoList){
            String url="D:\\Program Files\\DTCZ\\DTCZimage\\port"+list.getStationIP()+"\\"+list.getIdLocal()+"-Image.jpg";
            String url2="D:\\Program Files\\DTCZ\\DTCZimage\\port"+list.getStationIP()+"\\"+list.getIdLocal()+"-PlateImg.jpg";
            //String url="http://"+jsonObject.get("imgUrlPrefix").toString()+"/port"+list.getStationIP()+"/"+list.getIdLocal()+"-Image.jpg";
            //String url2="http://"+jsonObject.get("imgUrlPrefix").toString()+"/port"+list.getStationIP()+"/"+list.getIdLocal()+"-PlateImg.jpg";//车牌照图片
            list.setImgprefixurl(url);

            Double OverWeightRatio=list.getOverWeightRatio()*100;
            String plate = list.getPlate();
            if(OverWeightRatio>=5&&!"无车牌".equals(plate)){
                HandleRemoteDatas.HandleRemoteDatas(url,url2,list,yujingUrl);//处理图片流并调用远程接口
            }
        }
        int i=service.addtVehicleOverweightList(tWimMsgVoList);
        String daoMsg = MsgFormatUtils.getMsgByResult(i, "处理");
        jsonObject.put("resultMsg",daoMsg);
        jsonObject.put("daoMsg",i);
        return jsonObject.toString();
    }


    @RequestMapping("/delVehicleOverweightTemp")
    public int delVehicleOverweightTemp(List<tWimMsgVo> tWimMsgVoList){
        int i=service.delVehicleOverweightTemp(tWimMsgVoList);
        return i;
    }

    @RequestMapping("/quxiaotVehicleOverweight")
    public String quxiaotVehicleOverweight(@RequestBody tWimMsgVo tWimMsgVo) throws Exception {
        String urlPrefix = getimgUrlPrefix();
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(urlPrefix);

        int i=service.quxiaotVehicleOverweight(tWimMsgVo.getIdLocal());
        String daoMsg = MsgFormatUtils.getMsgByResult(i, "取消");
        jsonObject.put("resultMsg",daoMsg);
        jsonObject.put("daoMsg",i);
        jsonObject.put("obj",tWimMsgVo);
        return jsonObject.toString();
    }

}
