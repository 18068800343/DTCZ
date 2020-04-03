package com.ldxx.controller;


import com.ldxx.bean.TongJiTableQuery;
import com.ldxx.service.Impl.TradingDetailsService;
import com.ldxx.util.UpLoadImgUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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


    @RequestMapping(value = "/saveImg",method = RequestMethod.POST)
    public boolean saveImg(HttpServletResponse response, HttpSession session,String saveDiskUrl,String imgCode)
            throws IOException {
        return UpLoadImgUtil.generateImage(imgCode,saveDiskUrl);
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

