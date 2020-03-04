package com.ldxx.service.Impl;

import com.ldxx.bean.Accessory;
import com.ldxx.bean.StationSite;
import com.ldxx.dao.AccessoryDao;
import com.ldxx.dao.StationSiteDao;
import com.ldxx.service.StationSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StationSiteServiceImpl implements StationSiteService {


    @Autowired
    private StationSiteDao dao;
    @Autowired
    private AccessoryDao accessoryDao;

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
        int i= dao.addStationSite(StationSite);
        if(i>0){
            //List<Accessory> accessory=StationSite.getFile();
            if(StationSite.getFile()!=null&&StationSite.getFile().getAcName()!=null){
                List<Accessory> accessory=new ArrayList<Accessory>();
                accessory.add(StationSite.getFile());
                i=accessoryDao.addAccessory(accessory);
            }
        }
        return i;
    }

    @Override
    public int updStationSite(StationSite StationSite) {
        int i = dao.updStationSite(StationSite);
        if(i>0){
            /*List<Accessory> accessory = accessoryDao.selectAccessoryById(tMaintenanceLog.getLogId());
            if(accessory!=null&&accessory.size()!=0){
                i=accessoryDao.deleteAccessory(tMaintenanceLog.getLogId());
            }*/
            if(StationSite.getFile()!=null&&StationSite.getFile().getAcName()!=null){
                List<Accessory> accessory=new ArrayList<Accessory>();
                accessory.add(StationSite.getFile());
                i=accessoryDao.addAccessory(accessory);
            }
        }
        return i;
    }

    @Override
    public int delStationSite(String id) {
        int i = dao.delStationSite(id);
        if(i>0){
            List<Accessory> accessory = accessoryDao.selectAccessoryById(id);
            if(accessory!=null&&accessory.size()!=0){
                i=accessoryDao.deleteAccessory(id);
            }

        }
        return i;
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
    public int xg_iscountStationIp(String stationIp, String id) {
        return dao.xg_iscountStationIp(stationIp,id);
    }

    @Override
    public int xg_iscountStationPort(String stationPort, String id) {
        return dao.xg_iscountStationPort(stationPort,id);
    }

    @Override
    public List<StationSite> getStationSiteByPort(String stationPort) {
        return dao.getStationSiteByPort(stationPort);
    }
}
