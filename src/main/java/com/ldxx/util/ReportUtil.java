package com.ldxx.util;

import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.Properties;

/** 
* @ClassName: ReportUtil 
* @Description: 生成日报月报
*/
public class ReportUtil {

	//报告程序所在路径
	@Value("${reportCmd}")
	private String reportCmd;


	public String getReportPath(String cmdOp){

		String path = "";
		String op = reportCmd+ File.separator+ cmdOp;
		try {
			Process process = Runtime.getRuntime().exec(cmdOp);
			InputStream is = process.getInputStream();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "GBK"));
		    String line;
		    while((line = reader.readLine())!= null){
				   System.out.println(line);
					   if(line.contains("success:")){
						   path = line.replace("success:", "");
						   System.out.println(path);
					   }
				  }
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	public static void main(String[] args) {

		String path = new ReportUtil().getReportPath("D:\\javasoft\\report\\CreateDayReport.exe 3 连徐公司 2020-03-29");
		System.out.println("aaaa"+path);
	}
}
