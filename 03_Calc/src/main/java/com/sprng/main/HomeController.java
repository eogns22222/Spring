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
	public String home(Model model) {		
		return "home";
	}
	
	
	@RequestMapping(value = "/calc", method = RequestMethod.POST)
	private String calc(Model model, HttpServletRequest req) {
		int su1 = Integer.parseInt(req.getParameter("val1"));
		int su2 = Integer.parseInt(req.getParameter("val2"));
		String oper = req.getParameter("oper");
		Object result = 0;
		
		switch (oper) {
		case "+":
			result = su1 + su2;
			break;
		case "-":
			result = su1 - su2;
			break;
		case "*":
			result = su1 * su2;
			break;
		case "/":
			result = su1 / su2;
			break;
		}
		
		logger.info(su1 + " " + oper + " " + su2 + " = " + result);
		
		model.addAttribute("result", result);
		
		return "result";
	}
	
}
