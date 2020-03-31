package com.ldxx.util;

import com.ldxx.Thread.PageCountCallable;
import com.ldxx.bean.Report;
import com.ldxx.config.Config;
import com.ldxx.dao.ReportInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/** 
* @ClassName: ReportUtil 
* @Description: 生成日报月报
*/
@Configuration
@EnableConfigurationProperties(Config.class)
@Component
public class ReportUtil {

	public static ReportUtil reportUtil;
	//报告程序所在路径
	@Resource
	private Config config;

	@Resource
	private ReportInfoDao dao;

	@PostConstruct
	public void init(){
		reportUtil = this;
		reportUtil.config = this.config;
		reportUtil.dao = this.dao;
	}

	public static ReportUtil getInstance(){
		if(reportUtil==null){
			reportUtil = new ReportUtil();
		}
		return reportUtil;
	}

	public ReportUtil() {
	}

	/**
	 *
	 * @param cmdOp cmd运行报告程序参数.
	 * @param timeType 报告类型,1日报告,2月报告.
	 * @return
	 */
	public String getReportPath(String cmdOp,Integer timeType){

		String path = "";
		String op = reportUtil.config.getReportCmd()+ File.separator+ cmdOp;
		try {
			Process process = Runtime.getRuntime().exec(op);
			InputStream is = process.getInputStream();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "GBK"));
		    String line;
		    while((line = reader.readLine())!= null){
				   System.out.println(line);
				   if(line!=null&&!line.trim().equals("")){
					   if(line.endsWith("docx")){
					   	   String[] strs = line.split("\\\\");
					   	   String str = strs[strs.length-1];
					   	   String companyName = str.split("_")[0];
					   	   String reportName = str;
					   	   String reportUrl = line.replace("info:","").replace("success:","");
						   Report report = new Report();
						   report.setrId(LDXXUtils.getUUID32());
						   report.setCompanyName(companyName);
						   report.setReportName(reportName);
						   report.setReportStatus(1);
						   report.setReportTime(new Date());
						   report.setTimeType(timeType);
						   report.setReportUrl(reportUrl);
						   int i = reportUtil.dao.insertReport(report);
					   }
				   }
				  }
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	/**
	 *
	 * @param day
	 * @param month
	 * @param companyNames 公司名称集合
	 * @return
	 */
	public int reportByDayAndMonth(String day, String month, List<String> companyNames,String cmdOp){
		int g = 1;
		try {
			for(int i =1;i<3;i++){
				if(i==1){
					for(String companyNameOut : companyNames){
						String op = reportUtil.config.getReportCmd()+ File.separator+ cmdOp+" "+companyNameOut+" "+day;
						Process process = Runtime.getRuntime().exec(op);
						InputStream is = process.getInputStream();
						BufferedReader reader = new BufferedReader(new InputStreamReader(is, "GBK"));
						String line;
						while((line = reader.readLine())!= null){
							   System.out.println(line);
							   if(line!=null&&!line.trim().equals("")){
								   if(line.endsWith("docx")){
									   String[] strs = line.split("\\\\");
									   String str = strs[strs.length-1];
									   String companyName = str.split("_")[0];
									   String reportName = str;
									   String reportUrl = line.replace("info:","").replace("success:","");
									   Report report = new Report();
									   report.setrId(LDXXUtils.getUUID32());
									   report.setCompanyName(companyName);
									   report.setReportName(reportName);
									   report.setReportStatus(1);
									   report.setReportTime(new Date());
									   report.setTimeType(i);
									   report.setReportUrl(reportUrl);
									   int k = reportUtil.dao.insertReport(report);
								   }
							   }
							  }
						is.close();
					}
				}
			}
		} catch (IOException e) {
			g=0;
			e.printStackTrace();
		}
		return g;
	}

	public static void main(String[] args) {

		String path = new ReportUtil().getReportPath("D:\\javasoft\\report\\CreateDayReport.exe 3 连徐公司 2020-03-29",1);
		System.out.println("aaaa"+path);
	}
}
