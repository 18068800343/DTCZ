package com.ldxx.util;

import com.ldxx.bean.CheLiuLiangEchartsList;
import com.ldxx.bean.tjfxTotalEchars;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

	public static List<tjfxTotalEchars> gettjfxTotalEchartsByTowList(List<tjfxTotalEchars> A, List<tjfxTotalEchars> B) {
		for (int i = 0; i < A.size(); i++) {
			tjfxTotalEchars itemA = A.get(i);
			tjfxTotalEchars itemB = B.get(i);

			itemA.setZongchaozainum(Integer.parseInt(itemA.getZongchaozainum()) + Integer.parseInt(itemB.getZongchaozainum()) + "");
			itemA.setZongliuliangnum(Integer.parseInt(itemA.getZongliuliangnum()) + Integer.parseInt(itemB.getZongliuliangnum()) + "");

			itemA.setYijiyujingnum(Integer.parseInt(itemA.getYijiyujingnum() == null ? "0" : itemA.getYijiyujingnum()) + Integer.parseInt(itemB.getYijiyujingnum() == null ? "0" : itemB.getYijiyujingnum()) + "");
			itemA.setErjiyujingnum(Integer.parseInt(itemA.getErjiyujingnum() == null ? "0" : itemA.getErjiyujingnum()) + Integer.parseInt(itemB.getErjiyujingnum() == null ? "0" : itemB.getErjiyujingnum()) + "");
		}
		return A;
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

			Integer[] numsAll = new Integer[numsA.length + numsB.length];

			Map map = new HashMap();
			for (int i = 0; i < numsA.length; i++) {
				map.put(numsA[i] + "-" + "name", stationNamesA[i]);
				map.put(numsA[i] + "-" + "port", stationPortsA[i]);
				numsAll[i] = Integer.parseInt(numsA[i]);
			}
			for (int i = 0; i < numsB.length; i++) {
				map.put(numsB[i] + "-" + "name", stationNamesB[i]);
				map.put(numsB[i] + "-" + "port", stationPortsB[i]);
				numsAll[i + numsA.length] = Integer.parseInt(numsB[i]);
			}

			Arrays.sort(numsAll);
			StringBuffer numEnd = new StringBuffer();
			StringBuffer stationPorts = new StringBuffer();
			StringBuffer stationNames = new StringBuffer();
			for (int i = 0; i < numsAll.length; i++) {
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
