package com.ldxx.util;

import java.util.HashMap;
import java.util.Map;

public class FormatUtil {

    public static Map<String,Double> changeBigAndSmall(Double start,Double end){
        Double mid = 0.0;
        if(null!=start&&null!=end&&start>end){
            mid=start;
            start = end;
            end = mid;
        }
        Map map = new HashMap();
        map.put("start",start);
        map.put("end",end);
        return map;
    }
}
