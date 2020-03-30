package com.ldxx.util;

import com.ldxx.vo.tWimMsgVo;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringEscapeUtils;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;

public class HandleRemoteDatas {

    public static String HandleRemoteDatas(String url, String url2, tWimMsgVo tWimMsgVo2, String yujingUrl) throws Exception {
        tWimMsgVo data = setNullValue(tWimMsgVo2);
        //读取车辆正面图片图片
        byte[] bytes = readpicture(url);
        BASE64Encoder encoder = new BASE64Encoder();
        String base64 = encoder.encode(bytes);//返回Base64编码过的字节数组字符串
        data.setPlateImg1(base64);
        //读取车牌图片
        byte[] bytes2 = readpicture(url2);
        BASE64Encoder encoder2 = new BASE64Encoder();
        String base642 = encoder.encode(bytes2);//返回Base64编码过的字节数组字符串
        data.setPlateImg2(base642);
        data.setDeviceNo(null);
        data.setVehicleWeight(data.getTotalWeight());

        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor());
        String data2= net.sf.json.JSONObject.fromObject(data,jsonConfig).toString();
        //System.out.println(data);
        ToInterface toInterface=new ToInterface();
        String s = toInterface.interfaceUtil(yujingUrl, data2);
        return s;
    }

    public static <T> T setNullValue(T source) throws IllegalArgumentException, IllegalAccessException, SecurityException {
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getGenericType().toString().equals(
                    "class java.lang.String")) {
                field.setAccessible(true);
                Object obj = field.get(source);
                if (obj != null && obj.equals("")) {
                    field.set(source, null);
                } else if (obj != null) {
                    String str = obj.toString();
                    str = StringEscapeUtils.escapeSql(str);//StringEscapeUtils是commons-lang中的通用类
                    field.set(source, str.replace("\\", "\\" + "\\").replace("(", "\\(").replace(")", "\\)")
                            .replace("%", "\\%").replace("*", "\\*").replace("[", "\\[").replace("]", "\\]")
                            .replace("|", "\\|").replace(".", "\\.").replace("$", "\\$").replace("+", "\\+").trim()
                    );
                }
            }
        }
        return source;
    }

    public static byte[] readpicture(String  imgurl) throws Exception {
        byte[] byteArray;
        HttpURLConnection connection = null;

        try {
            URL url = new URL(imgurl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5*1000);
            InputStream in = connection.getInputStream();
            try {
                byteArray = readInputStream(in);
            } catch (Exception e) {
                //log.error("error:"+e.getStackTrace());
                throw new Exception("图片转换BYTE流失败！");
            }

        } catch (IOException e2) {
            /*if(log.isErrorEnabled()){
                log.error("error:"+e2.getStackTrace()
                        +"getMessage:"+e2.getMessage());
            }*/
            throw new Exception("获取照片信息失败！");
        }

        //获取照片数据流
        if(byteArray != null){
            //tWimMsgVo.setModelChangeImage(byteArray);
            byteArray= byteArray;
        }
        connection.disconnect();
        return byteArray;
    }
    private static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024]; //创建一个Buffer字符串
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        inStream.close();   //关闭输入流
        return outStream.toByteArray();  //把outStream里的数据写入内存
    }
}
