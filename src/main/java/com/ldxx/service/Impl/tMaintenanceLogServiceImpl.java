package com.ldxx.service.Impl;

import com.ldxx.bean.Accessory;
import com.ldxx.bean.tMaintenanceLog;
import com.ldxx.dao.AccessoryDao;
import com.ldxx.dao.tMaintenanceLogDao;
import com.ldxx.service.tMaintenanceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class tMaintenanceLogServiceImpl implements tMaintenanceLogService {

    @Autowired
    private tMaintenanceLogDao dao;
    @Autowired
    private AccessoryDao accessoryDao;

    @Override
    public int addtMaintenanceLog(tMaintenanceLog tMaintenanceLog) {
        int i= dao.addtMaintenanceLog(tMaintenanceLog);
        if(i>0){
            List<Accessory> accessory=tMaintenanceLog.getAccessory();
            if(accessory!=null){
                i=accessoryDao.addAccessory(accessory);
            }
        }
        return i;
    }

    @Override
    public int deltMaintenanceLog(String id) {
        int i= dao.deltMaintenanceLog(id);
        if(i>0){
            List<Accessory> accessory = accessoryDao.selectAccessoryById(id);
            if(accessory!=null&&accessory.size()!=0){
                i=accessoryDao.deleteAccessory(id);
            }

        }
        return i;
    }

    @Override
    public int updtMaintenanceLog(tMaintenanceLog tMaintenanceLog) {
        int i= dao.updtMaintenanceLog(tMaintenanceLog);
        if(i>0){
            /*List<Accessory> accessory = accessoryDao.selectAccessoryById(tMaintenanceLog.getLogId());
            if(accessory!=null&&accessory.size()!=0){
                i=accessoryDao.deleteAccessory(tMaintenanceLog.getLogId());
            }*/
            if(tMaintenanceLog.getAccessory()!=null&&tMaintenanceLog.getAccessory().size()!=0){
                i=accessoryDao.addAccessory(tMaintenanceLog.getAccessory());
            }
        }
        return i;
    }

    @Override
    public List<tMaintenanceLog> getAlltMaintenanceLog() {
        return dao.getAlltMaintenanceLog();
    }
}
