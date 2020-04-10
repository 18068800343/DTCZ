package com.ldxx.dao;

import java.util.List;

import com.ldxx.bean.Accessory;
import org.apache.ibatis.annotations.Param;

public interface AccessoryDao {

	public int addAccessory(@Param("accessory") List<Accessory> accessory);
	
	public int deleteAccessory(@Param("id") String id);
	
	public List<Accessory> selectAccessoryById(@Param("id") String id);
	
	public int deleteAccessoryByIdAndName(@Param("accessory") Accessory accessory);
	
	public List<Accessory> selectAccessoryByIdAndType(@Param("id") String id, @Param("type") String type);
	
	public int fileCount(@Param("id") String id);
    
}