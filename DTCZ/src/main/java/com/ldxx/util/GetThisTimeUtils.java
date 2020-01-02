package com.ldxx.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetThisTimeUtils {
	
	public static String getDateTime(){
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}
	public static String getDate() {
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}
	public static String getTime() {
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("HH:mm:ss");
		return dateFormat.format(date);
	}
	public static String getDateTimeNumber(){
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddHHmmss");
		return dateFormat.format(date);
	}
}
