package com.ldxx.dao;

import com.ldxx.vo.tWimMsgVo;

import java.util.List;

public interface tWimMsgDao {

    List<tWimMsgVo> getAlltWimMsg(String stationPort);
}
