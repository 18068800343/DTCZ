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

    @Override
    public StationSite getStationSiteById(String id) {
        return dao.getStationSiteById(id);
    }

    @Override
    public int addStationSite(StationSite StationSite) {
        return dao.addStationSite(StationSite);
    }

    @Override
    public int updStationSite(StationSite StationSite) {
        return dao.updStationSite(StationSite);
    }

    @Override
    public int delStationSite(String id) {
        return dao.delStationSite(id);
    }

    @Override
    public int iscountStationId(String stationId) {
        return dao.iscountStationId(stationId);
    }

    @Override
    public int iscountStationName(String stationName) {
        return dao.iscountStationName(stationName);
    }

    @Override
    public int iscountStationIp(String stationIp) {
        return dao.iscountStationIp(stationIp);
    }

    @Override
    public int iscountStationPort(String stationPort) {
        return dao.iscountStationPort(stationPort);
    }

    @Override
    public int xg_iscountStationId(String stationId, String id) {
        return dao.xg_iscountStationId(stationId,id);
    }

    @Override
    public int xg_iscountStationName(String stationName, String id) {
        return dao.xg_iscountStationName(stationName,id);
    }

    @Override
    public int xg_iscountStationIp(String stationIp, String id) {
        return dao.xg_iscountStationIp(stationIp,id);
    }

    @Override
    public int xg_iscountStationPort(String stationPort, String id) {
        return dao.xg_iscountStationPort(stationPort,id);
    }
}
