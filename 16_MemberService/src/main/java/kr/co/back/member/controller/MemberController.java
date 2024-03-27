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
	
	@RequestMapping(value = "/")
	public String home() {
		logger.info("접속");
		return "login";
	}
	
	@RequestMapping(value = "/join.go")
	public String joinForm() {
		logger.info("회원가입 페이지 이동");
		return "joinForm";
	}
	
	@RequestMapping(value = "/join.do", method = RequestMethod.POST)
	public String join(@RequestParam Map<String,String> param, Model model) {
		logger.info("화원가입 시도");
		String page = "joinForm";
		String msg = "회원가입 실패";
		int row = memberService.join(param);
		
		if(row == 1) {
			page = "login";
			msg = "회원가입 성공";
		}
		
		model.addAttribute("msg", msg);
		return page;
	}
	
	@RequestMapping(value = "/overlay.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> overlay(String id){
		logger.info("id : " + id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("use", memberService.overlay(id));
		return map;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, HttpSession session, String id, String pw) {
		logger.info("로그인 요청");
		String page = "login";
		MemberDTO info = memberService.login(id, pw);
		
		if(info != null) {
			page = "list";
			session.setAttribute("loginInfo", info);
		}else {
			model.addAttribute("msg", "아이디 또는 비밀번호를 확인해주세요.");
		}
		
		return page;
	}
	
}

























