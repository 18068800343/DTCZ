package com.ldxx.util;


import com.ldxx.config.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class CallbackProcesser {

    private final HttpServletResponse response;
    private OutputStreamWriter osw;
    public CallbackProcesser(HttpServletResponse response) {
        this.response = response;
        String fileName = "result.csv";
        this.response.addHeader("Content-Type", "application/csv");
        this.response.addHeader("Content-Disposition", "attachment; filename="+fileName);
        this.response.setCharacterEncoding("utf-8");
        try {
            if(null==this.osw){
            this.osw = new OutputStreamWriter(this.response.getOutputStream());
            }
//            this.osw.write("\ufeff");
//            this.osw.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF }));
            this.osw.write(Const.CSV_HEAD);
            this.osw.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <E> void processData(E record) {
        try {

            this.osw.write(record.toString());
            this.osw.write("\n");
            this.osw.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
