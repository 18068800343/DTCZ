package com.ldxx.service;

import com.ldxx.bean.StationSite;

import java.util.List;

public interface StationSiteService {

    List<StationSite> getAllStationSite();

    StationSite getStationSiteById(String id);

    int addStationSite(StationSite StationSite);

    int updStationSite(StationSite StationSite);

    int delStationSite(String id);

    int iscountStationIp(String stationIp);

    int iscountStationPort(String stationPort);

    int xg_iscountStationIp(String stationIp,String id);

    int xg_iscountStationPort(String stationPort,String id);

    List<StationSite> getStationSiteByPort(String stationPort);
}
