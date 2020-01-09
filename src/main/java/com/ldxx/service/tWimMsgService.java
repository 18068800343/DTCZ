package com.ldxx.service;

import com.ldxx.vo.tWimMsgVo;

import java.util.List;

public interface tWimMsgService {

    List<tWimMsgVo> getAlltWimMsg(String stationPort);

    List<tWimMsgVo> getAlltWimMsgByCondition(String stationPort,String startTime,String endTime);
}
