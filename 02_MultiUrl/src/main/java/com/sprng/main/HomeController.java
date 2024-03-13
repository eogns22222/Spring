package com.sprng.main;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest req) {		
		
		String param = req.getParameter("param");
		System.out.println("param : " + param);
		logger.info("param : {}", param);
		
		String val = "";
		
		if(param != null && param.equals("greeting")) {
			val = "안녕하세요!";
		}else if(param != null && param.equals("date")) {
			val = new Date().toString();
		}
		
		model.addAttribute("msg", val);
		
		
		return "index";
	}
	
}













