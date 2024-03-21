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
	
	@RequestMapping(value = "/error/404")
	public String notFound(Model model) {
		model.addAttribute("code", "404");
		model.addAttribute("msg", "페이지 또는 요청을 못찾음");
		
		return "error";
	}
	
	@RequestMapping(value = "/error/500")
	public String serverError(Model model) {
		model.addAttribute("code", "500");
		model.addAttribute("msg", "서버 처리중 문제 발생");
		
		return "error";
	}
	
	@RequestMapping(value="/")
	public String home() {
		logger.info("누군가 접속하였습니다.");
		
		
		return "login";
	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String login(Model model, HttpSession session,String id, String pw) {
		logger.info("[로그인]을 시도하였습니다.");
		String page = "login";
		String msg = "[로그인]에 실패 하였습니다. 다시시도해주세요.";
		
		String loginId = service.login(id,pw);
		logger.info("[로그인 정보]");
		logger.info("아이디 :"+id);
		logger.info("비밀번호 :"+pw);
		logger.info("----------------");
		
		if(loginId !=null) {
			msg= id+"님[로그인] 되었습니다.";
			page="redirect:/list";
			session.setAttribute("loginId", loginId);
		}
		
			model.addAttribute("msg",msg);
		
		return page;
	}

}






















