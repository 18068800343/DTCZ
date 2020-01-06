package com.ldxx.controller;

import com.ldxx.bean.StationSite;
import com.ldxx.service.StationSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("StationSite")
public class StationSiteController {

    @Autowired
    private StationSiteService service;

    @RequestMapping("/getAllStationSite")
    public List<StationSite> getAllStationSite(){
        List<StationSite> list = service.getAllStationSite();
        return list;
    }
}
