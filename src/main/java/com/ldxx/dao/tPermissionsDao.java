package com.ldxx.dao;

import com.ldxx.bean.tPermissions;

import java.util.List;

public interface tPermissionsDao {

    List<tPermissions> getAllUPermissionsRole();

    int updUsrPersmissionCodingById(String  usrId,String UsrPersmissionCoding);
}
