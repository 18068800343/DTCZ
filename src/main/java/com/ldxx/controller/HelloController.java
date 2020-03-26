package com.ldxx.controller;


import com.ldxx.bean.TongJiTableQuery;
import com.ldxx.service.Impl.TradingDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;

@RestController
@RequestMapping("down")
public class HelloController {

    private final TradingDetailsService tradingDetailsService;

    public HelloController(TradingDetailsService tradingDetailsService) {
        this.tradingDetailsService = tradingDetailsService;
    }

    @GetMapping("/download_csv")
    public void downloadAsCsv(HttpServletResponse response, HttpSession session)
            throws IOException {
        TongJiTableQuery tongJiTableQuery = (TongJiTableQuery) session.getAttribute("tongJiTableQuery");
        tongJiTableQuery.setStart(null);
        tongJiTableQuery.setLength(null);
        tradingDetailsService.downloadAsCsv(tongJiTableQuery, response);
    }
    @RequestMapping("/downloadReport")
    public void downloadReport(HttpServletResponse response, HttpSession session,String filePath,String fileName)
            throws IOException {
        File f=new File(filePath);
        //第一步：设置响应类型
        response.setContentType("application/force-download");//应用程序强制下载
        //第二读取文件
        InputStream in = new FileInputStream(filePath);
        //设置响应头，对文件进行url编码
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename="+fileName);
        response.setContentLength(in.available());

        //第三步：老套路，开始copy
        OutputStream out = response.getOutputStream();
        byte[] b = new byte[1024];
        int len = 0;
        while((len = in.read(b))!=-1){
            out.write(b, 0, len);
        }
        out.flush();
        out.close();
        in.close();
    }
}

