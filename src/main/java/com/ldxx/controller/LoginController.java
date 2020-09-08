package com.ldxx.controller;


import com.ldxx.bean.tUserInfo;
import com.ldxx.config.Config;
import com.ldxx.service.tUserInfoService;
import com.ldxx.util.Base64Util;
import com.ldxx.vo.ImgUrlPrefixVo;
import com.ldxx.vo.tUserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(Config.class)
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private tUserInfoService service;

    @Autowired
    ImgUrlPrefixVo imgUrlPrefixVo;

    @Autowired
    private Config config;


    @RequestMapping("/index")
    public String test() {
        return "redirect:/view/login.html";
    }
    
    @RequestMapping("/login")
    public String login(){ 
        return "redirect:../view/login.html";
    }
    
    @RequestMapping(value="/userlogin",method= RequestMethod.POST)
    public Map<String,Object> userlogin(tUserInfo user, HttpServletRequest request){
        int state = 0;
        tUserInfoVo loginUser = null;
        tUserInfoVo loginUser1 = null;
        Map<String, Object> map = new HashMap<>();
        HttpSession session = request.getSession();
        if ("shiro".equals(user.getUsrName())) {
            if (null != user && null != user.getUsrName() && null != user.getUsrPwd() && !"".equals(user.getUsrName()) && !"".equals(user.getUsrPwd())) {
                loginUser = service.selectUserByUsrName(user.getUsrName());
                loginUser1 = service.selectUserByUsrName1(user.getUsrName());
                if (null != loginUser && null != loginUser.getUsrName()) {
                    if (user.getUsrPwd().equals(loginUser.getUsrPwd())) {//成功登陆
                        session.setAttribute("user", loginUser);
                        session.setAttribute("user1", loginUser1);
                        state = 1;
                    } else {
                        state = -3;
                    }//用户密码错误
                } else {
                    state = -2;
                }//该用户不存在
            } else {
                state = -1;
            }//用户名或密码为空
        }

        map.put("result", state);
        map.put("user", loginUser);
        return map;
    }

    @RequestMapping(value = "/userloginSJ", method = RequestMethod.POST)
    public Map<String, Object> userloginSJ(tUserInfo user, HttpServletRequest request) {
        int state = 0;
        tUserInfoVo loginUser = null;
        tUserInfoVo loginUser1 = null;
        Map<String, Object> map = new HashMap<>();
        HttpSession session = request.getSession();
        if (null != user && null != user.getUsrName() && null != user.getUsrPwd() && !"".equals(user.getUsrName()) && !"".equals(user.getUsrPwd())) {
            loginUser = service.selectUserByUsrName(user.getUsrName());
            loginUser1 = service.selectUserByUsrName1(user.getUsrName());
            if (null != loginUser && null != loginUser.getUsrName()) {
                if (user.getUsrPwd().equals(loginUser.getUsrPwd())) {//成功登陆
                    session.setAttribute("user", loginUser);
                    session.setAttribute("user1", loginUser1);
                    state = 1;
                } else {
                    state = -3;
                }//用户密码错误
            } else {
                state = -2;
            }//该用户不存在
        } else {
            state = -1;
        }//用户名或密码为空
        map.put("result", state);
        map.put("user", loginUser);
        return map;
    }

    @RequestMapping("/getUser")
    public tUserInfoVo getUser(HttpServletRequest request) {
        tUserInfoVo user = (tUserInfoVo) request.getSession().getAttribute("user");
        return user;
    }

    @RequestMapping("/getUser1")
    public tUserInfoVo getUser1(HttpServletRequest request) {
		tUserInfoVo user1 = (tUserInfoVo) request.getSession().getAttribute("user1");
		return user1;
	}

	@RequestMapping("/exit")//退出
	public void exit(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
	}

	@RequestMapping("/keepSession")//退出
	public int keepSession() {
		return 1;
	}

	@RequestMapping(value="/userloginInterface",method= RequestMethod.GET)
	public void userloginInterface(String usrName,String usrPwd, HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		String decodeusrPwd = Base64Util.decode(usrPwd);//base64密码解码

		int state = 0;
		tUserInfoVo loginUser = null;
		tUserInfoVo loginUser1 = null;
		Map<String, Object> map = new HashMap<>();
		HttpSession session = request.getSession();
		if (null != usrName && null != decodeusrPwd && !"".equals(usrName) && !"".equals(decodeusrPwd)) {
			loginUser = service.selectUserByUsrName(usrName);
			loginUser1 = service.selectUserByUsrName1(usrName);
			if (null != loginUser && null != loginUser.getUsrName()) {
				if (decodeusrPwd.equals(loginUser.getUsrPwd())) {//成功登陆
					session.setAttribute("user", loginUser);
					session.setAttribute("user1", loginUser1);
					state = 1;
				} else {
					state = -3;
				}//用户密码错误
			} else {
				state = -2;
			}//该用户不存在
		}else {state=-1;}//用户名或密码为空

		if(state==1) {
            response.sendRedirect("../view/" + imgUrlPrefixVo.getIndexUrl());
//            response.sendRedirect("../view/indexSJ.html");
        }else if(state==-1){
			request.setAttribute("errorMessage","用户名或密码为空");
			//请求转发
			request.getRequestDispatcher("../view/login.html").forward(request,response);
		}else if(state==-2){
			request.setAttribute("errorMessage","用户不存在");
			//请求转发
			request.getRequestDispatcher("../view/login.html").forward(request,response);
		}else if(state==-3){
			request.setAttribute("errorMessage","用户密码错误");
			//请求转发
			request.getRequestDispatcher("../view/login.html").forward(request,response);
		}
	}
}
