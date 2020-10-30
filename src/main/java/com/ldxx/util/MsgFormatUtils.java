package com.ldxx.util;

import com.ldxx.bean.CheLiuLiangEchartsList;

import java.util.Arrays;
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


	public static CheLiuLiangEchartsList getCheLiuLiangEchartsByTow(CheLiuLiangEchartsList A, CheLiuLiangEchartsList B) {
		String numA = A.getNums();
		if (!"".equals(numA) && numA.contains(",")) {
			String numsA[] = numA.split(",");
			String stationNamesA[] = A.getStationNames().split(",");
			String stationPortsA[] = A.getStationPorts().split(",");

			String numsB[] = B.getNums().split(",");
			String stationNamesB[] = B.getStationNames().split(",");
			String stationPortsB[] = B.getStationPorts().split(",");

			Integer[] numsAll = new Integer[12];

			Map map = new HashMap();
			for (int i = 0; i < numsA.length; i++) {
				map.put(numsA[i] + "-" + "name", stationNamesA[i]);
				map.put(numsA[i] + "-" + "port", stationPortsA[i]);
				numsAll[i] = Integer.parseInt(numsA[i]);
			}
			for (int i = 0; i < numsB.length; i++) {
				map.put(numsB[i] + "-" + "name", stationNamesB[i]);
				map.put(numsB[i] + "-" + "port", stationPortsB[i]);
				numsAll[i + 6] = Integer.parseInt(numsB[i]);
			}

			Arrays.sort(numsAll);
			StringBuffer numEnd = new StringBuffer();
			StringBuffer stationPorts = new StringBuffer();
			StringBuffer stationNames = new StringBuffer();
			for (int i = 6; i < numsAll.length; i++) {
				if (i == numsAll.length - 1) {
					numEnd.append(numsAll[i]);
					stationNames.append(map.get(numsAll[i] + "-" + "name"));
					stationPorts.append(map.get(numsAll[i] + "-" + "port"));
				} else {
					numEnd.append(numsAll[i]).append(",");
					stationNames.append(map.get(numsAll[i] + "-" + "name")).append(",");
					stationPorts.append(map.get(numsAll[i] + "-" + "port")).append(",");
				}

			}
			A.setNums(numEnd.toString());
			A.setStationNames(stationNames.toString());
			A.setStationPorts(stationPorts.toString());
			return A;
		} else {
			return A;
		}
	}

	public static void main(String[] args) {


	}
}
