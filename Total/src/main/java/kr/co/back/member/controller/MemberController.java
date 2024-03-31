package kr.co.back.member.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.back.member.dto.MemberDTO;
import kr.co.back.member.service.MemberService;

@Controller
public class MemberController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MemberService memberService;
	
	@RequestMapping(value = "/error/404")
	public String notFound(Model model) {
		model.addAttribute("code", "404");
		model.addAttribute("msg", "페이지 또는 요청을 찾지 못했습니다.");
		
		return "error";
	}
	
	@RequestMapping(value = "/error/500")
	public String serverError(Model model) {
		model.addAttribute("code", "500");
		model.addAttribute("msg", "서버 처리중 문제가 발생했습니다.");
		
		return "error";
	}
	
	@RequestMapping(value = "/")
	public String home() {
		logger.info("접속");
		return "login";
	}
	
	@RequestMapping(value = "/join.go")
	public String joinForm() {
		logger.info("회원기입 폼 이동");
		return "joinForm";
	}
	
	@RequestMapping(value = "/join.do", method = RequestMethod.POST)
	public String join(Model model, @RequestParam Map<String, String> params) {
		logger.info("회원가입 시도");
		String page = "joinForm";
		String msg = "회원가입 실패";
		int row = memberService.join(params);
		logger.info("row : " + row);
		
		if(row > 0) {
			page = "login";
			msg = "회원가입 성공";
		}
		
		model.addAttribute("msg", msg);
		return page;
	}
	
	@RequestMapping(value = "/overlay.do")
	@ResponseBody
	public Map<String, Object> overlay(String id) {
		logger.info("중복확인");
		
		int cnt = memberService.overlay(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("use", cnt);
		
		return map;
	}
	
	@RequestMapping(value = "/login.do")
	public String login(Model model, HttpSession session, String id, String pw) {
		logger.info("로그인 시도");
		String page = "login";
		logger.info("id : " + id);
		logger.info("pw : " + pw);
		MemberDTO info = memberService.login(id, pw);
		logger.info("info : {}", info);
		if(info != null) {
			page = "list";
			session.setAttribute("loginInfo", info);
		}else {
			model.addAttribute("msg", "아이디 또는 비밀번호를 확인해주세요.");
		}
		
		return page;
	}
	
	
}































