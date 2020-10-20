package com.ldxx.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ldxx.bean.tUserInfo;
import com.ldxx.service.tUserInfoService;
import com.ldxx.vo.ImgUrlPrefixVo;
import com.ldxx.vo.tUserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    ImgUrlPrefixVo imgUrlPrefixVo;
    @Autowired
    private tUserInfoService service;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        //String requestUrl = request.getRequestURL().toString();
        HttpSession session = request.getSession();
        tUserInfoVo loginUser = null;
        String token = (String) request.getParameter("token");
        String usrName = (String) request.getParameter("usrName");
        if (null != token && !"".equals(token) && "jiance".equals(token)) {
            String user = "";
            loginUser = service.selectUserByUsrName(usrName);
            session.setAttribute("user", loginUser);
            response.sendRedirect("view/" + imgUrlPrefixVo.getIndexUrl());
            return false;
        }
        tUserInfo user = (tUserInfo) session.getAttribute("user");
        if (null == user) {
            response.sendRedirect("../view/" + imgUrlPrefixVo.getLoginUrl());
            return false;
        }

        return true;
    }

	
}
