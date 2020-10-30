package com.ldxx.util;

import com.ldxx.bean.CheLiuLiangEchartsList;

import java.util.HashMap;
import java.util.Map;

public class MsgFormatUtils {

	public static String getMsgByResult(int i, String vMsg) {
		if (i > 0) {
			vMsg = vMsg + "成功";
		} else {
			vMsg = vMsg + "失败";
		}
		return vMsg;
	}


	public CheLiuLiangEchartsList getCheLiuLiangEchartsByTow(CheLiuLiangEchartsList A, CheLiuLiangEchartsList B) {


		String numA = A.getNums();
		if (!"".equals(numA) && numA.contains(",")) {
			String numsA[] = numA.split(",");
			String stationNamesA[] = A.getStationNames().split(",");
			String stationPortsA[] = A.getStationPorts().split(",");

			String numsB[] = B.getNums().split(",");
			String stationNamesB[] = B.getStationNames().split(",");
			String stationPortsB[] = B.getStationPorts().split(",");

			String[] numsAll = new String[12];

			Map map = new HashMap();
			for (int i = 0; i < numsA.length; i++) {
				map.put(numsA[i] + "-" + "name", stationNamesA[i]);
				map.put(numsA[i] + "-" + "port", stationPortsA[i]);
				numsAll[i] = numsA[i];
			}
			for (int i = 0; i < numsB.length; i++) {
				map.put(numsB[i] + "-" + "name", stationNamesB[i]);
				map.put(numsB[i] + "-" + "port", stationPortsB[i]);
				numsAll[i + 6] = numsA[i];
			}


			return null;
		} else {
			return null;
		}
	}

}
