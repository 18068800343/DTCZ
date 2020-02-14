package com.ldxx.service;

import com.ldxx.bean.tMaintenanceLog;

import java.util.List;

public interface tMaintenanceLogService {

    int addtMaintenanceLog(tMaintenanceLog tMaintenanceLog);

    int deltMaintenanceLog(String id);

    int updtMaintenanceLog(tMaintenanceLog tMaintenanceLog);

    List<tMaintenanceLog> getAlltMaintenanceLog();
}
