package com.ldxx.controller;


import com.ldxx.bean.TongJiTableQuery;
import com.ldxx.service.Impl.TradingDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
}

