package com.ldxx.controller;

import com.alibaba.fastjson.JSONObject;
import com.ldxx.bean.tUserInfo;
import com.ldxx.dao.tVehicleOverweightDao;
import com.ldxx.service.TVehicleOverweightService;
import com.ldxx.util.MsgFormatUtils;
import com.ldxx.vo.tWimMsgVo;
import com.ldxx.vo.ImgUrlPrefixVo;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
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
    public String addtVehicleOverweight(@RequestBody tWimMsgVo tWimMsgVo) throws Exception {
        String urlPrefix = getimgUrlPrefix();
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(urlPrefix);

        String url="D:\\Program Files\\DTCZ\\DTCZimage\\port"+tWimMsgVo.getStationIP()+"\\"+tWimMsgVo.getIdLocal()+"-Image.jpg";
        //String url="http://"+jsonObject.get("imgUrlPrefix").toString()+"/port"+tWimMsgVo.getStationIP()+"/"+tWimMsgVo.getIdLocal()+"-Image.jpg";
        tWimMsgVo.setImgprefixurl(url);
        //读取图片
        byte[] bytes = readpicture(url);
        byte[] buf=bytes.toString().getBytes("utf8");
        String bytes2 = org.apache.commons.codec.binary.Base64.encodeBase64String(buf);

        int i=service.addtVehicleOverweightByidLocal(tWimMsgVo.getIdLocal());
        String daoMsg = MsgFormatUtils.getMsgByResult(i, "处理");
        jsonObject.put("resultMsg",daoMsg);
        jsonObject.put("daoMsg",i);
        jsonObject.put("modelImage",bytes2);
        jsonObject.put("obj",tWimMsgVo);
        return jsonObject.toString();
    }


    @RequestMapping("/addtVehicleOverweightList")
    public String addtVehicleOverweightList(String startTime,String endTime ,HttpSession session,Integer lv,String idLocals) throws Exception {
        JSONObject jsonObject=new JSONObject();
        /*String zhandianduankouhao="";
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        if(user!=null){
            zhandianduankouhao = user.getStationPort();
        }*/
        List<tWimMsgVo> tWimMsgVoList=tdao.getMeiRiGuanJianChaoZaiShujuByidLocals(idLocals,lv);
        int i=service.addtVehicleOverweightList(tWimMsgVoList);

        List imgList=new ArrayList();
        for (tWimMsgVo list:tWimMsgVoList){
            String url="D:\\Program Files\\DTCZ\\DTCZimage\\port"+list.getStationIP()+"\\"+list.getIdLocal()+"-Image.jpg";
            //String url="http://"+jsonObject.get("imgUrlPrefix").toString()+"/port"+list.getStationIP()+"/"+list.getIdLocal()+"-Image.jpg";
            list.setImgprefixurl(url);
            //读取图片
            byte[] bytes = readpicture(url);
            byte[] buf=bytes.toString().getBytes("utf8");
            String bytes2 = org.apache.commons.codec.binary.Base64.encodeBase64String(buf);
            imgList.add(bytes2);
        }

        String daoMsg = MsgFormatUtils.getMsgByResult(i, "处理");
        jsonObject.put("resultMsg",daoMsg);
        jsonObject.put("daoMsg",i);
        jsonObject.put("modelImage",imgList);
        return jsonObject.toString();
    }


    @RequestMapping("/delVehicleOverweightTemp")
    public int delVehicleOverweightTemp(List<tWimMsgVo> tWimMsgVoList){
        int i=service.delVehicleOverweightTemp(tWimMsgVoList);
        return i;
    }



    public byte[] readpicture(String  imgurl) throws Exception {
        byte[] byteArray;
        HttpURLConnection connection = null;

        try {
            URL url = new URL(imgurl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5*1000);
            InputStream in = connection.getInputStream();
            try {
                byteArray = readInputStream(in);
            } catch (Exception e) {
                //log.error("error:"+e.getStackTrace());
                throw new Exception("图片转换BYTE流失败！");
            }

        } catch (IOException e2) {
            /*if(log.isErrorEnabled()){
                log.error("error:"+e2.getStackTrace()
                        +"getMessage:"+e2.getMessage());
            }*/
            throw new Exception("获取照片信息失败！");
        }

        //获取照片数据流
        if(byteArray != null){
            //tWimMsgVo.setModelChangeImage(byteArray);
            byteArray= byteArray;
        }
        connection.disconnect();
        return byteArray;
    }
    private static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024]; //创建一个Buffer字符串
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        inStream.close();   //关闭输入流
        return outStream.toByteArray();  //把outStream里的数据写入内存
    }
}
