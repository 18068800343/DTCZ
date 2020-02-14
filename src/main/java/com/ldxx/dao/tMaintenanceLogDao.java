package com.ldxx.dao;

import com.ldxx.bean.tMaintenanceLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface tMaintenanceLogDao {

    int addtMaintenanceLog(tMaintenanceLog tMaintenanceLog);

    int deltMaintenanceLog(String id);

    int updtMaintenanceLog(tMaintenanceLog tMaintenanceLog);

    List<tMaintenanceLog> getAlltMaintenanceLog();

}
