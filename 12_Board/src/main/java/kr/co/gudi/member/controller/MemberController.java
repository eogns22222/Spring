package kr.co.gudi.member.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.gudi.member.service.MemberService;

@Controller
public class MemberController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MemberService service;
	
	@RequestMapping(value = "/")
	public String home() {
		logger.info("로그인 페이지 진입");
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, String id, String pw, HttpSession session) {
		logger.info(id + " / " + pw);
		String page ="login";
		
		String loginId = service.login(id, pw);
		
		if(loginId != null) {
			page = "redirect:/list";
			session.setAttribute("loginId", loginId);
		}else {
			String msg = "아이디 또는 비밀번호를 확인해주세요.";
			model.addAttribute("msg", msg);
		}
		
		return page;
	}
	
}




























