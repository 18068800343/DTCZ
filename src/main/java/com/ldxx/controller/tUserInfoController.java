package com.ldxx.controller;

import com.alibaba.fastjson.JSONObject;
import com.ldxx.bean.CompanySite;
import com.ldxx.bean.tUserInfo;
import com.ldxx.dao.StationSiteDao;
import com.ldxx.dao.tUserInfoDao;
import com.ldxx.service.tUserInfoService;
import com.ldxx.util.Base64Util;
import com.ldxx.util.LDXXUtils;
import com.ldxx.util.MsgFormatUtils;
import com.ldxx.util.getNumCode;
import com.ldxx.vo.tUserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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
    public String UserSynchronization(String USER_NAME,String USER_ACCOUNT,String PASSWORD,String COMPANY_ID,String COMPANY_NAME,String COMPANY_TYPE,String TYPE) {
        JSONObject jsonObject=new JSONObject();
        tUserInfo tUserInfo=new tUserInfo();
        String decode = Base64Util.decode(PASSWORD);//base64密码解码
        tUserInfoVo tUserInfoVo = service.selectUserByUsrName("user");
        tUserInfo.setUsrUname(USER_NAME);
        tUserInfo.setUsrName(USER_ACCOUNT);
        tUserInfo.setUsrPwd(decode);
        int iscountCompanySiteName = ssdao.iscountCompanySiteName(COMPANY_NAME);
        if(iscountCompanySiteName>0){
            tUserInfo.setUsrRole(COMPANY_ID);
        }else{
            CompanySite CompanySite=new CompanySite();
            int num = ssdao.countNumCompanySite();
            String code = getNumCode.getNumCode(num+1, "JSJKDW");
            CompanySite.setId(code);
            CompanySite.setCompanyName(COMPANY_NAME);
            CompanySite.setGroups(COMPANY_TYPE);
            ssdao.addCompanySite(CompanySite);
            tUserInfo.setUsrRole(code);
        }

        int i=0;
        String string="";
        switch (TYPE){
            case "0"://新增
                string="新增";
                String id=LDXXUtils.getUUID12();
                tUserInfo.setUsrId(id);
                tUserInfo.setDelState(1);
                tUserInfo.setUsrPersmissionCoding(tUserInfoVo.getUsrPersmissionCoding());
                i= service.addtUserInfo(tUserInfo);
                break;
            case "1"://删除
                string="删除";
                i=dao.deltUserInfoByUsrName(USER_ACCOUNT);
                break;
            case "2"://修改
                string="修改";
                i=dao.updtUserInfoByUsrName(tUserInfo);
                break;
        }
        String daoMsg = MsgFormatUtils.getMsgByResult(i, string);
        jsonObject.put("success",daoMsg);
        jsonObject.put("resultMsg",i);
        return jsonObject.toString();
    }
}
