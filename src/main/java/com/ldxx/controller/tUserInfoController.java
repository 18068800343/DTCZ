package com.ldxx.controller;

import com.ldxx.bean.tUserInfo;
import com.ldxx.service.tUserInfoService;
import com.ldxx.util.LDXXUtils;
import com.ldxx.vo.tUserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
            tUserInfoVo tUserInfoVo= service.selectUserById(tUserInfo.getUsrId());
            session.setAttribute("user",tUserInfoVo);
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
}
