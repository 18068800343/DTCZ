package com.ldxx.util;

public class getNumCode {

    public static String getNumCode(int count,String code){
        String y=String.valueOf(count);
        if(y.length()==1){
            y="00"+y;
        }else if(y.length()==2){
            y="0"+y;
        }
        String Code=code+y;
        return Code;
    }
}
