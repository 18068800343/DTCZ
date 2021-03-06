package com.ldxx.controller;

import com.alibaba.fastjson.JSONObject;
import com.ldxx.bean.CompanySite;
import com.ldxx.bean.tUserInfo;
import com.ldxx.dao.StationSiteDao;
import com.ldxx.dao.tUserInfoDao;
import com.ldxx.service.tUserInfoService;
import com.ldxx.util.*;
import com.ldxx.vo.UserBody;
import com.ldxx.vo.tUserInfoVo;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("tUserInfo")
public class tUserInfoController {

    @Autowired
    private tUserInfoService service;
    @Autowired
    private tUserInfoDao dao;
    @Autowired
    private StationSiteDao ssdao;

    @RequestMapping("/addtUserInfo")
    public Map<String,Object> addtUserInfo(tUserInfo tUserInfo) {
         Map<String,Object> map=new HashMap<>();
         int i=0;
         int iscountName=service.iscountName(tUserInfo.getUsrName());
         if(iscountName>0){
             i=-1;
         }else{
             String id=LDXXUtils.getUUID12();
             tUserInfo.setUsrId(id);
             tUserInfo.setDelState(1);
             i= service.addtUserInfo(tUserInfo);
         }

        map.put("result",i);
        map.put("user",tUserInfo);
        return map;
    }

    @RequestMapping("/updatetUserInfo")
    public Map<String,Object> updatetUserInfo(tUserInfo tUserInfo, HttpSession session) {
        Map<String,Object> map=new HashMap<>();
        int i=0;
        int iscountName=service.iscountUNameEdit(tUserInfo.getUsrName(),tUserInfo.getUsrId());
        if(iscountName>0){
            i=-1;
        }else{
            i= service.updatetUserInfo(tUserInfo);
        }
        if(i>0){
           /* tUserInfoVo tUserInfoVo= service.selectUserById(tUserInfo.getUsrId());
            session.removeAttribute("user");
            session.setAttribute("user",tUserInfoVo);*/
        }
        map.put("result",i);
        map.put("user",tUserInfo);
        return map;
    }

    @RequestMapping("/deltUserInfo")
    public int deltUserInfo(String id) {
        return service.deltUserInfo(id);
    }

    @RequestMapping("/getAlltUserInfo")
    public List<tUserInfoVo> getAlltUserInfo() {
        return service.getAlltUserInfo();
    }

    @RequestMapping("/selectUserById")
    public tUserInfoVo selectUserById(String id) {
        return service.selectUserById(id);
    }

    @RequestMapping("/updatepasswordById")
    public int updatepasswordById(String usrPwd, String usrId) {
        String old_password = usrPwd.split(",")[0];
        String new_password = usrPwd.split(",")[1];
        tUserInfoVo user = service.selectUserById(usrId);
        int i=0;
        if(!user.getUsrPwd().equals(old_password)){
            i=-1;
        }else if(user.getUsrPwd().equals(new_password)){
            i=-2;
        }else{
            i=service.updatepasswordById(new_password,usrId);
        }
        return i;
        //return service.updatepasswordById(usrPwd,usrId);
    }

    /**
     *
     * @param USER_NAME 姓名
     * @param USER_ACCOUNT 用户名
     * @param PASSWORD 密码 (base64加密)
     * @param COMPANY_ID 公司id
     * @param COMPANY_NAME 公司名称
     * @param COMPANY_TYPE 公司类型 0:控股公司 1路公司 2养护单位 3第三方单位
     * @param TYPE 0新增 1 删除 2 修改
     * @return
     */
    @RequestMapping("/UserSynchronization")
    public String UserSynchronization(@RequestBody String data) throws UnsupportedEncodingException {
        JSONObject jsonObject=new JSONObject();
        //String data2=Base64Util.decode(data);
        String data2=new String(Base64.decodeBase64(data),"utf-8");
        UserBody Body = jsonObject.parseObject(data2,UserBody.class);
        tUserInfo tUserInfo=new tUserInfo();
        String decode = Base64Util.decode(Body.getPASSWORD());//base64密码解码
        tUserInfoVo tUserInfoVo = service.selectUserByUsrName("user");
        tUserInfo.setUsrUname(Body.getUSER_NAME());
        tUserInfo.setUsrName(Body.getUSER_ACCOUNT());
        tUserInfo.setUsrPwd(decode);
        int iscountCompanySiteName = ssdao.iscountCompanySiteName(Body.getCOMPANY_NAME());
        if(iscountCompanySiteName>0){
            tUserInfo.setUsrRole(Body.getCOMPANY_ID());
        }else{
            CompanySite CompanySite=new CompanySite();
            int num = ssdao.countNumCompanySite();
            String code = getNumCode.getNumCode(num+1, "JSJKDW");
            CompanySite.setId(code);
            CompanySite.setCompanyName(Body.getCOMPANY_NAME());
            CompanySite.setStationPort(tUserInfoVo.getStationPort());
            CompanySite.setGroups(Body.getCOMPANY_TYPE());
            ssdao.addCompanySite(CompanySite);
            tUserInfo.setUsrRole(code);
        }

        int i=0;
        String string="";
        int iscountName = service.iscountName(Body.getUSER_ACCOUNT());
        switch (Body.getTYPE()){
            case "0"://新增
                if(iscountName>0){//用户名已存在
                    string="用户名已存在，新增";
                    i=0;
                }else{
                    string="新增";
                    String id=LDXXUtils.getUUID12();
                    tUserInfo.setUsrId(id);
                    tUserInfo.setDelState(1);
                    tUserInfo.setUsrPersmissionCoding(tUserInfoVo.getUsrPersmissionCoding());
                    i= service.addtUserInfo(tUserInfo);
                }
                break;
            case "1"://删除
                if(iscountName>0){//用户名存在
                    string="删除";
                    i=dao.deltUserInfoByUsrName(Body.getUSER_ACCOUNT());
                }else{
                    string="用户名不存在，删除";
                    i=0;
                }
                break;
            case "2"://修改
                if(iscountName>0){//用户名存在
                    string="修改";
                    i=dao.updtUserInfoByUsrName(tUserInfo);
                }else{
                    string="用户名不存在，修改";
                    i=0;
                }
                break;
        }
        String daoMsg = MsgFormatUtils.getMsgByResult(i, string);
        jsonObject.put("success",daoMsg);
        jsonObject.put("resultMsg",i);
        return jsonObject.toString();
    }

    @RequestMapping("/importExcel")
    @ResponseBody
    public int importExcel(@RequestParam("file") MultipartFile file) throws IOException {
        InputStream is=file.getInputStream();
        ImportData importData=new ImportData();
        int i=importData.readXls(is);
        return i;
    }
}
