package com.ldxx.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String getDateStrByPattern(String pattern, Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String string = simpleDateFormat.format(date);
		return string;
	}

	public static String getYestodayStr(){
		Date date=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.DATE, -1);
		date = calendar.getTime();
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		String dateString = format.format(date);
		return dateString;
	}
	public static void main(String[] args) {
		
	}
}
