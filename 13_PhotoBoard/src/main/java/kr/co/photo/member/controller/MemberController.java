package kr.co.photo.member.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.photo.member.service.MemberService;

@Controller
public class MemberController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MemberService service;
	
	@RequestMapping(value = "/")
	public String home() {
		logger.info("접속");
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, HttpSession session, String id, String pw) {
		logger.info("로그인");
		String page = "login";
		String loginId = service.login(id, pw);
		
		if(loginId != null) {
			page = "redirect:/list";
			session.setAttribute("loginId", loginId);
		}else {
			model.addAttribute("msg", "아이디 또는 비밀번호를 확인해주세요");
		}
		
		return page;
	}
}


































