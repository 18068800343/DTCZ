package com.ldxx.controller;

import javax.servlet.http.HttpServletResponse;

import com.ldxx.vo.ImgUrlPrefixVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ldxx.config.Config;

@Configuration
@EnableConfigurationProperties(Config.class)
@RequestMapping("")
@Controller
public class IndexController {

	@Autowired
	ImgUrlPrefixVo imgUrlPrefixVo;

	@RequestMapping("/")
	public String index(Model model, HttpServletResponse response) {
		model.addAttribute("name", "simonsfan");
		//return "redirect:/view/login.html";
		return "redirect:/view/" + imgUrlPrefixVo.getLoginUrl();
	}
}
