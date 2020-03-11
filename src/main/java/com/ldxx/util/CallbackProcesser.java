package com.ldxx.util;


import com.ldxx.config.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CallbackProcesser {

    private final HttpServletResponse response;

    public CallbackProcesser(HttpServletResponse response) {
        this.response = response;
        String fileName = "result.csv";
        this.response.addHeader("Content-Type", "application/csv");
        this.response.addHeader("Content-Disposition", "attachment; filename="+fileName);
        this.response.setCharacterEncoding("UTF-8");
        try {
            this.response.getWriter().write(Const.CSV_HEAD);
            this.response.getWriter().write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <E> void processData(E record) {
        try {
            response.getWriter().write(record.toString()); //如果是要写入csv,需要重写toString,属性通过","分割
            response.getWriter().write("\n");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
