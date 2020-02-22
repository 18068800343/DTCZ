package com.ldxx.controller;


import com.ldxx.bean.tPermissions;
import com.ldxx.dao.tPermissionsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("tPermissions")
public class tPermissionsController {

    @Autowired
    private tPermissionsDao dao;

    @RequestMapping("/getAlltPermissions")
    public Map<String,Object> getAllUPermissionsRole(){
        Map<String,Object> map=new HashMap<>();
        List<tPermissions> list= dao.getAllUPermissionsRole();
        map.put("list",list);
        List<tPermissions> list1=new ArrayList<>();
        List<tPermissions> list2=new ArrayList<>();
        List<tPermissions> list3=new ArrayList<>();
        List<tPermissions> list4=new ArrayList<>();
        List<tPermissions> list5=new ArrayList<>();
        List<tPermissions> list6=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getCoding().length()==3){//一级菜单栏
                list1.add(list.get(i));
                map.put("list1",list1);
            }else if(list.get(i).getCoding().length()==4){//二级子菜单栏
                list2.add(list.get(i));
                map.put("list2",list2);
            }else if(list.get(i).getCoding().length()==5){//三级子菜单栏
                list3.add(list.get(i));
                map.put("list3",list3);
            }else if(list.get(i).getCoding().length()==6){//四级子菜单栏
                list4.add(list.get(i));
                map.put("list4",list4);
            }else if(list.get(i).getCoding().length()==7){//五级子菜单栏
                list5.add(list.get(i));
                map.put("list5",list5);
            }else{										//六级子菜单栏
                list6.add(list.get(i));
                map.put("list6",list6);
            }
        }
        return map;
    }

    /**
     * 修改用户权限编码
     * @param usrId
     * @param UsrPersmissionCoding
     * @return
     */
    @RequestMapping("/updUsrPersmissionCodingById")
    public int updUsrPersmissionCodingById(String  usrId,String UsrPersmissionCoding){
        return dao.updUsrPersmissionCodingById(usrId,UsrPersmissionCoding);

    }
}
