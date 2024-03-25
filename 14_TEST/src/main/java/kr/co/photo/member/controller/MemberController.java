package kr.co.photo.member.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.photo.board.dto.BoardDTO;
import kr.co.photo.member.service.MemberService;

@Controller
public class MemberController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MemberService service;
	
	@RequestMapping(value = "/error/404")
	public String notFound(Model model) {
		logger.info("404");
		model.addAttribute("code", "404");
		model.addAttribute("msg", "페이지 또는 요청을 찾지 못함");
		return "error";
	}
	
	@RequestMapping(value = "/error/500")
	public String serverError(Model model) {
		logger.info("404");
		model.addAttribute("code", "500");
		model.addAttribute("msg", "서버에러");
		return "error";
	}
	
	
	@RequestMapping(value = "/")
	public String home() {
		logger.info("접속");
		return "login";
	}
	
	@RequestMapping(value = "/joinForm")
	public String joinForm() {
		logger.info("회원가입 페이지");
		String page = "joinForm";
		return page;
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@RequestParam Map<String, String> param, Model model) {
		logger.info("회원가입 시도");
		String page = "joinForm";
		
		if(service.join(param)) {
			page = "login";
			model.addAttribute("msg", "회원가입 성공");
		}else {
			model.addAttribute("msg", "회원가입 실패");
		}
		
		return page;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session, Model model, String id, String pw) {
		logger.info("로그인 시도");
		String page = "login";
		String loginId = service.login(id, pw);
		
		if(loginId != null) {
			page = "redirect:/list";
			session.setAttribute("loginId", loginId);
			model.addAttribute("msg", "로그인 성공");
			model.addAttribute("loginBox", "<div> 안녕하세요 " + loginId + " 님 <a href='logout'>로그아웃</a></div>");
		}else {
			model.addAttribute("msg", "아이디 또는 비밀번호를 다시 확인해주세요.");
		}
		
		return page;
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		logger.info("로그아웃");
		session.removeAttribute("loginId");
		return "login";
	}
	
	@RequestMapping(value = "/list02")
	public String list02(HttpSession session, Model model) {
		logger.info("회원목록 보기");
		String page = "login";
		
		if(session.getAttribute("loginId") != null) {
			page = "list02";
			List<BoardDTO> list = service.list02();
			model.addAttribute("list", list);
		}
		
		return page;
	}
	
	
}
































