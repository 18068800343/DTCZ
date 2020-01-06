package com.ldxx.dao;

import com.ldxx.bean.StationSite;

import java.util.List;

public interface StationSiteDao {

    List<StationSite> getAllStationSite();

    StationSite getStationSiteById(String id);
}
