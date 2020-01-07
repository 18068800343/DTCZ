package com.ldxx.controller;


import com.ldxx.bean.tUserInfo;
import com.ldxx.service.tUserInfoService;
import com.ldxx.vo.tUserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	private tUserInfoService service;
	
    @RequestMapping("/index")
    public String test(){ 
    	return "redirect:/view/login.html";
    }
    
    @RequestMapping("/login")
    public String login(){ 
        return "redirect:../view/WEB/login.html";
    }
    
    @RequestMapping(value="/userlogin",method= RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> userlogin(tUserInfo user, HttpServletRequest request){
    	int state=0;
		tUserInfoVo loginUser=null;
    	Map<String,Object> map=new HashMap<>();
		HttpSession session = request.getSession();
		if(null!=user&&null!=user.getUsrName()&&null!=user.getUsrPwd()&&!"".equals(user.getUsrName())&&!"".equals(user.getUsrPwd())) {
			loginUser=service.selectUserByUsrName(user.getUsrName());
			if(null!=loginUser&&null!=loginUser.getUsrName())
			{
				if(user.getUsrPwd().equals(loginUser.getUsrPwd()))
				{//成功登陆
					session.setAttribute("user",loginUser);
					state=1;
				}else {state=-3;}//用户密码错误
			}else {state=-2;}//该用户不存在
		}else {state=-1;}//用户名或密码为空
		
		map.put("result", state);
		map.put("user", loginUser);
		return map;
    }
    
    @RequestMapping("/getUser")
    @ResponseBody
    public tUserInfoVo getUser(HttpServletRequest request){
		tUserInfoVo user=(tUserInfoVo) request.getSession().getAttribute("user");
    	return user;
    }
    
    @RequestMapping("/exit")//退出
	@ResponseBody
	public void exit(HttpServletRequest request){
		request.getSession().removeAttribute("user");
	}

	@RequestMapping("/keepSession")//退出
	@ResponseBody
	public int keepSession(){
		return 1;
	}
}
