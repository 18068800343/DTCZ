package com.ldxx.service.Impl;


import com.ldxx.bean.TongJiTableQuery;
import com.ldxx.util.CallbackProcesser;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class TradingDetailsService {

    @Resource
    private  SqlSessionTemplate sqlSessionTemplate;

    public void downloadAsCsv(TongJiTableQuery tongJiTableQuery, HttpServletResponse httpServletResponse)
            throws IOException {

        CustomResultHandler customResultHandler = new CustomResultHandler(new CallbackProcesser(httpServletResponse));
        sqlSessionTemplate.select(
                "com.ldxx.dao.tWimMsgDao.getAlltWimMsgByCondition", tongJiTableQuery, customResultHandler);
        /*httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();*/
    }

    public void downloadYiChangAsCsv(TongJiTableQuery tongJiTableQuery, HttpServletResponse httpServletResponse)
            throws IOException {

        CustomResultHandler customResultHandler = new CustomResultHandler(new CallbackProcesser(httpServletResponse));
        sqlSessionTemplate.select(
                "com.ldxx.dao.tWimMsgDao.getAlltWimMsgYiChangByCondition", tongJiTableQuery, customResultHandler);
        /*httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();*/
    }
}
