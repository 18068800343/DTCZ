package com.ldxx.dao;

import com.ldxx.bean.Accessory;
import com.ldxx.bean.CompanySite;
import com.ldxx.bean.StationSite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StationSiteDao {

    List<StationSite> getAllStationSite();

    StationSite getStationSiteByStationNameAndRouteName(String stationName,String routeName);

    StationSite getStationSiteById(String id);

    int addStationSite(StationSite StationSite);

    int updStationSite(StationSite StationSite);

    int updateCompanySite(CompanySite companySite);

    int delStationSite(String id);

    int delCompanySite(String id);

    int addCompanySite(CompanySite companySite);

    int iscountStationIp(String stationIp);

    int iscountStationPort(String stationPort);


    int xg_iscountStationIp(String stationIp,String id);

    int xg_iscountStationPort(String stationPort,String id);

    List<StationSite> getStationSiteByPort(String stationPort);

    List<CompanySite> getCompanyStation();

    List<Accessory> getAccessoryByPort(String stationPort);
}
