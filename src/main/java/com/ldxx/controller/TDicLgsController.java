package com.ldxx.controller;

import com.alibaba.fastjson.JSONObject;
import com.ldxx.bean.CruxWeight;
import com.ldxx.bean.StationSite;
import com.ldxx.bean.TDicLgs;
import com.ldxx.dao.TDicLgsDao;
import com.ldxx.service.CruxWeightService;
import com.ldxx.util.LDXXUtils;
import com.ldxx.util.MsgFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("TDicLgsController")
public class TDicLgsController {

    @Resource
    private TDicLgsDao tDicLgsDao;

    @RequestMapping("/getTDicLgsList")
    public List<TDicLgs> getTDicLgsList() {
        return tDicLgsDao.getTDicLgsList();
    }


}
