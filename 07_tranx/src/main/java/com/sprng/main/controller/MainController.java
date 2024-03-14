package com.sprng.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sprng.main.service.MainService;

@Controller // 컨트롤러라는 표시
public class MainController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String main() {
		return "index";
	}
	
	@RequestMapping(value="/insert")
	public String insert() {
		logger.info("insert 요청");
		MainService service = new MainService();
		service.insert();
		
		return "index";
	}
	
}












