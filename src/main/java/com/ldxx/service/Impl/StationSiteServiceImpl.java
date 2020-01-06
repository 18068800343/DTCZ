package com.ldxx.service.Impl;

import com.ldxx.bean.StationSite;
import com.ldxx.dao.StationSiteDao;
import com.ldxx.service.StationSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationSiteServiceImpl implements StationSiteService {


    @Autowired
    private StationSiteDao dao;

    @Override
    public List<StationSite> getAllStationSite() {
        return dao.getAllStationSite();
    }
}
