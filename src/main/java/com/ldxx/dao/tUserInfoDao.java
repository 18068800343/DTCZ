package com.ldxx.dao;

import com.ldxx.bean.tUserInfo;
import com.ldxx.vo.tUserInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface tUserInfoDao {

    int addtUserInfo(tUserInfo tUserInfo);

    List<tUserInfoVo> getAlltUserInfo();

    int iscountName(String name);

    int updatetUserInfo(tUserInfo tUserInfo);

    int deltUserInfo(String id);

    int iscountUNameEdit(String name,String id);

    tUserInfoVo selectUserByUsrName(String name);

    int updatepasswordById(String usrPwd,String usrId);

    tUserInfoVo selectUserById(String usrId);

    int updlastMonitoringSiteById(String lastMonitoringSite,String usrId);
}
