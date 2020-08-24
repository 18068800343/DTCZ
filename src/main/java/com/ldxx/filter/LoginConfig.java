package com.ldxx.filter;

import com.ldxx.vo.ImgUrlPrefixVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class LoginConfig extends WebMvcConfigurerAdapter {

	@Autowired
	ImgUrlPrefixVo imgUrlPrefixVo;

	@Bean
	public LoginInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(loginInterceptor())
				.addPathPatterns("/**/view/**")
				.excludePathPatterns("/**/view/" + imgUrlPrefixVo.getLoginUrl());
	}
	}
