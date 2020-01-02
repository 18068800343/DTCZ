package com.ldxx.util;

public class MsgFormatUtils {

	public static String getMsgByResult(int i,String vMsg) 
	{
		if(i>0) {
			vMsg = vMsg+"成功";
		}else {
			vMsg = vMsg+"失败";
		}
		return vMsg;
	}
	
}
