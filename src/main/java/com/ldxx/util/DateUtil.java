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
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = format.format(date);
		return dateString;
	}

	public static String getLastMonthStr() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
		date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		String dateString = format.format(date);
		return dateString;
	}

	public static String getNowMonthStr() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		String dateString = format.format(date);
		return dateString;
	}

	public static void main(String[] args) {
		String str = DateUtil.getLastMonthStr();
		String str1 = DateUtil.getNowMonthStr();
		System.out.println(str1);
	}
}
