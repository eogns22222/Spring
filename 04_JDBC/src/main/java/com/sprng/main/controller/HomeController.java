package com.sprng.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sprng.main.service.HomeService;

// @ Controller 때문에 이 클래스가 컨트롤러 라는걸 알 수 있다.
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {		
		logger.info("controller home 접근");
		
		
		//service 기능을 사용하기 위해서
		HomeService service = new HomeService();
		String msg = service.dbConnect();
		model.addAttribute("msg", msg);
		
		return "index";
	}
	
}
