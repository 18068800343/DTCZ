package com.ldxx.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.*;
import javax.annotation.PostConstruct;

import com.ldxx.bean.CompanySite;
import com.ldxx.bean.tUserInfo;
import com.ldxx.dao.StationSiteDao;
import com.ldxx.dao.tUserInfoDao;
import com.ldxx.service.tUserInfoService;
import com.ldxx.vo.tUserInfoVo;
import org.apache.poi.ss.usermodel.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.apache.poi.ss.usermodel.CellType.BOOLEAN;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;

@Component
public class ImportData {
	
	public static ImportData INSTANCE; 
	
	@PostConstruct // 第三步  
    public void init() {  
        INSTANCE = this;
        INSTANCE.service=this.service;
    }
	@Autowired
	private tUserInfoService service;
	@Autowired
	private StationSiteDao ssdao;
	@Autowired
	private tUserInfoDao dao;


	
	public int readXls(InputStream is) throws IOException {
		Map<String,Object> map=new HashMap<String, Object>();
		Workbook  hssfWorkbook=null;
		
		int count=0;
		try {
			hssfWorkbook = WorkbookFactory.create(is);  
		} catch (Exception e) {
			e.printStackTrace();
		}  

        List<tUserInfo> tUserInfoVo = new ArrayList<tUserInfo>();
        // 循环工作表Sheet  
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {  
            Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);  
            if (hssfSheet == null) {  
                continue;  
            }  
            // 循环行Row  
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
            	try {
					Row hssfRow = hssfSheet.getRow(rowNum);
					if(hssfRow!=null){
						int cells=hssfRow.getPhysicalNumberOfCells();
						tUserInfoVo t2=new tUserInfoVo();

						Cell colum1 = hssfRow.getCell(0);//账号
						Cell colum2 = hssfRow.getCell(1); //姓名
						Cell colum3 = hssfRow.getCell(2); //性别
						Cell colum4 = hssfRow.getCell(3); //职务
						Cell colum5 = hssfRow.getCell(4);//公司
						Cell colum6 = hssfRow.getCell(5);//公司编码
						Cell colum7 = hssfRow.getCell(6);//手机号
						Cell colum8 = hssfRow.getCell(7);//TYPE 	0新增 1 删除 2 修改
						Cell colum9 = hssfRow.getCell(8);//公司类型 0:控股公司 1路公司 2养护单位 3第三方单位
						String value=getValue(colum1);

						tUserInfoVo tUserInfoVo2 = INSTANCE.service.selectUserByUsrName("user");
						tUserInfo tUserInfo=new tUserInfo();
						tUserInfo.setUsrUname(getValue(colum2));
						tUserInfo.setUsrName(getValue(colum1));
						tUserInfo.setUsrSex(getValue(colum3));
						tUserInfo.setUsrPwd("111111");//暂时写死的
						tUserInfo.setUsrPhone(getValue(colum7));
						int iscountCompanySiteName = INSTANCE.ssdao.iscountCompanySiteName(getValue(colum5));
						if(iscountCompanySiteName>0){
							tUserInfo.setUsrRole(getValue(colum6));
						}else{
							CompanySite CompanySite=new CompanySite();
							int num = INSTANCE.ssdao.countNumCompanySite();
							String code = getNumCode.getNumCode(num+1, "JSJKDW");
							CompanySite.setId(code);
							CompanySite.setCompanyName(getValue(colum5));
							CompanySite.setStationPort(tUserInfoVo2.getStationPort());
							CompanySite.setGroups(getValue(colum9));
							INSTANCE.ssdao.addCompanySite(CompanySite);
							tUserInfo.setUsrRole(code);
						}
						int i = INSTANCE.service.iscountName(value);
						String string="";
						switch (getValue(colum8)){
							case "0"://新增
								if(i>0){//用户名已存在
									string="用户名已存在，新增";
									count=0;
								}else{
									string="新增";
									String id=LDXXUtils.getUUID12();
									tUserInfo.setUsrId(id);
									tUserInfo.setDelState(1);
									tUserInfo.setUsrPersmissionCoding(tUserInfoVo2.getUsrPersmissionCoding());
									count= INSTANCE.service.addtUserInfo(tUserInfo);
									tUserInfoVo.add(tUserInfo);
								}
								break;
							case "1"://删除
								if(i>0){//用户名存在
									string="删除";
									count=dao.deltUserInfoByUsrName(getValue(colum1));
								}else{
									string="用户名不存在，删除";
									count=0;
								}
								break;
							case "2"://修改
								if(i>0){//用户名存在
									string="修改";
									count=INSTANCE.dao.updtUserInfoByUsrName(tUserInfo);
								}else{
									string="用户名不存在，修改";
									count=0;
								}
								break;
						}



					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
                }  
            }
		return count;
    }


	@SuppressWarnings("static-access")
	private String getValue(Cell colum1) {
		if(null!=colum1){
			if (colum1.getCellType() == BOOLEAN) {
				// 返回布尔类型的值
				return String.valueOf(colum1.getBooleanCellValue());
			} else if (colum1.getCellType() == NUMERIC) {
				// 返回数值类型的值
				NumberFormat nf = NumberFormat.getInstance();
				String s = nf.format(colum1.getNumericCellValue());
				//这种方法对于自动加".0"的数字可直接解决
				//但如果是科学计数法的数字就转换成了带逗号的，例如：12345678912345的科学计数法是1.23457E+13，经过这个格式化后就变成了字符串“12,345,678,912,345”，这也并不是想要的结果，所以要将逗号去掉
				if (s.indexOf(",") >= 0) {
					s = s.replace(",", "");
				}
				return s;
			} else {
				// 返回字符串类型的值
				return String.valueOf(colum1.getStringCellValue());
			}
		}else{
			return "";
		}
	}




}
