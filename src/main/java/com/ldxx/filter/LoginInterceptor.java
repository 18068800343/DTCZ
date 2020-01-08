package com.ldxx.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ldxx.bean.tUserInfo;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//String requestUrl = request.getRequestURL().toString();
		HttpSession session = request.getSession();
		tUserInfo user = (tUserInfo)session.getAttribute("user");
		if(null==user)
		{
			response.sendRedirect("../view/login.html");
			return false;
		}

		return true;
	}

	
}
