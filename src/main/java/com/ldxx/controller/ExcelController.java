package com.ldxx.controller;

import com.ldxx.bean.LicensePlate;
import com.ldxx.bean.TongJiTableQuery;
import com.ldxx.service.Impl.RedisServiceImpl;
import com.ldxx.service.tWimMsgService;
import com.ldxx.util.ExportUtils;
import com.ldxx.vo.ExcelData;
import com.ldxx.vo.tWimMsgVo;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("ExcelController")
public class ExcelController {
    @Resource
    RedisServiceImpl redisServiceImpl;

    @Autowired
    tWimMsgService tWimMsgService;

    @RequestMapping(value = "/excel", method = RequestMethod.GET)
    public void excel(HttpServletResponse response, HttpSession session) throws Exception {
        ExcelData data = new ExcelData();
        data.setName("hello");
        List<String> titles = new ArrayList();
        titles.add("站点");
        titles.add("车道号");
        titles.add("车牌号");
        titles.add("车牌颜色");
        titles.add("长");
        titles.add("车速");
        titles.add("轴数");
        titles.add("总重量");
        titles.add("路面温度");
        titles.add("超重比率");
        titles.add("时间");
        data.setTitles(titles);

        TongJiTableQuery tongJiTableQuery = (TongJiTableQuery) session.getAttribute("tongJiTableQuery");
        tongJiTableQuery.setStart(null);
        tongJiTableQuery.setLength(null);
        List<tWimMsgVo> list = tWimMsgService.getAlltWimMsgByConditionByPage(tongJiTableQuery);
        if(null!=list){
            List<List<Object>> rows1 = new ExcelController().initRowsList(list);
            data.setRows(rows1);
        }
        ExportUtils.exportExcel(response,"联系人表.xlsx",data);
    }

    private  List<List<Object>> initRowsList(List<tWimMsgVo> list){
        List<List<Object>> rows1 = new ArrayList();
        for(tWimMsgVo tWimMsgVo : list){
            List<Object> rows = new ArrayList<>();
            rows.add(tWimMsgVo.getStationName());
            rows.add(tWimMsgVo.getLaneNoName());
            rows.add(tWimMsgVo.getPlate());
            rows.add(tWimMsgVo.getLicensePlateColor());
            rows.add(tWimMsgVo.getLength());
            rows.add(tWimMsgVo.getSpeed());
            rows.add(tWimMsgVo.getAxlesCount());
            rows.add(tWimMsgVo.getTotalWeight());
            rows.add(tWimMsgVo.getTemperature());
            rows.add(tWimMsgVo.getOverWeightRatioName());
            rows.add(tWimMsgVo.getEvtTime());
            rows1.add(rows);
        }
        return rows1;
    }



    @RequestMapping("/test")
    public tWimMsgVo jedisTest(){

        tWimMsgVo tWimMsgVo = new tWimMsgVo();
        tWimMsgVo.setDirection(1);
        tWimMsgVo.setDirectionName("上行");
        redisServiceImpl.set("vo1",tWimMsgVo);
        tWimMsgVo tWimMsgVo1 = (com.ldxx.vo.tWimMsgVo) redisServiceImpl.get("vo1");
        System.out.println(tWimMsgVo1.getDirectionName());
        return tWimMsgVo1;
    }
}
