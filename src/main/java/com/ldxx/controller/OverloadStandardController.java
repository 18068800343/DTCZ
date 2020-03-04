package com.ldxx.controller;

import com.alibaba.fastjson.JSONObject;
import com.ldxx.bean.OverloadStandard;
import com.ldxx.dao.OverloadStandardDao;
import com.ldxx.util.LDXXUtils;
import com.ldxx.util.MsgFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("OverloadStandard")
@RestController
public class OverloadStandardController {

    @Autowired
    private OverloadStandardDao dao;

    @RequestMapping("/getAllOverloadStandard")
    public List<OverloadStandard> getAllOverloadStandard(){
        List<OverloadStandard> list = dao.getAllOverloadStandard();
        return list;
    }

    @RequestMapping("/addOverloadStandard")
    public String addOverloadStandard(@RequestBody OverloadStandard OverloadStandard){
        JSONObject jsonObject=new JSONObject();
        String id = LDXXUtils.getUUID12();
        int num=dao.countaxleNumber(OverloadStandard.getAxleNumber());
        int i=0;
        if(num!=0){
            i=-1;
        }else{
            OverloadStandard.setId(id);
            i=dao.addOverloadStandard(OverloadStandard);
        }
        String daoMsg = MsgFormatUtils.getMsgByResult(i, "新增");
        jsonObject.put("resultMsg",daoMsg);
        jsonObject.put("daoMsg",i);
        jsonObject.put("obj",OverloadStandard);
        return jsonObject.toString();
    }

    @RequestMapping("/updOverloadStandard")
    public String updOverloadStandard(@RequestBody OverloadStandard OverloadStandard){
        JSONObject jsonObject=new JSONObject();
        int num=dao.xgcountaxleNumber(OverloadStandard.getAxleNumber(),OverloadStandard.getId());
        int i=0;
        if(num!=0){
            i=-1;
        }else{
            i=dao.updOverloadStandard(OverloadStandard);
        }
        String daoMsg = MsgFormatUtils.getMsgByResult(i, "更新");
        jsonObject.put("resultMsg",daoMsg);
        jsonObject.put("daoMsg",i);
        jsonObject.put("obj",OverloadStandard);
        return jsonObject.toString();
    }

    @RequestMapping("/delOverloadStandard")
    public int delOverloadStandard(String id){
        return dao.delOverloadStandard(id);
    }

    @RequestMapping("/getOverloadStandardByNumber")
    public OverloadStandard getOverloadStandardByNumber(Integer axleNumber){
        OverloadStandard overloadStandard = dao.getOverloadStandardByNumber(axleNumber);
        return overloadStandard;
    }
}
