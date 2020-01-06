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
             String id=LDXXUtils.getUUID32();
             tUserInfo.setUsrId(id);
             tUserInfo.setDelState(1);
             i= service.addtUserInfo(tUserInfo);
         }

        map.put("result",i);
        map.put("user",tUserInfo);
        return map;
    }

    @RequestMapping("/updatetUserInfo")
    public Map<String,Object> updatetUserInfo(tUserInfo tUserInfo) {
        Map<String,Object> map=new HashMap<>();
        int i=0;
        int iscountName=service.iscountUNameEdit(tUserInfo.getUsrName(),tUserInfo.getUsrId());
        if(iscountName>0){
            i=-1;
        }else{
            i= service.updatetUserInfo(tUserInfo);
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
}
