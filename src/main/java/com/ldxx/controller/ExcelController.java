package com.ldxx.controller;

import com.ldxx.util.ExportUtils;
import com.ldxx.vo.ExcelData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("ExcelController")
public class ExcelController {

    @RequestMapping(value = "/excel", method = RequestMethod.GET)
    public void excel(HttpServletResponse response) throws Exception {
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
        List<List<Object>> rows = new ArrayList();
        List<Object> row1 = new ArrayList();
        row1.add("张三");
        row1.add("男");
        row1.add("23");
        row1.add("18612341234");
        List<Object> row2 = new ArrayList();
        row2.add("李四");
        row2.add("女");
        row2.add("24");
        row2.add("15312341234");
        rows.add(row1);
        rows.add(row2);
        data.setRows(rows);
        ExportUtils.exportExcel(response,"联系人表.xlsx",data);
    }
}
