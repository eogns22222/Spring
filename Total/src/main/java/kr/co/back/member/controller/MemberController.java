package kr.co.back.member.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.back.member.dto.MemberDTO;
import kr.co.back.member.service.MemberService;

@Controller
public class MemberController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MemberService memberService;
	
	
	@RequestMapping(value = "/error/404")
	public String pageNotfound(Model model) {
		model.addAttribute("code", "404");
		model.addAttribute("msg", "페이지 또는 요청을 찾지 못함");
		
		return "error";
	}
	
	@RequestMapping(value = "/error/500")
	public String serverError(Model model) {
		model.addAttribute("code", "500");
		model.addAttribute("msg", "서버 처리중 에러 발생");
		
		return "error";
	}
	
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
		String page = "joinForm";
		
		int row = memberService.join(param);
		
		if(row > 0) {
			page = "login";
			model.addAttribute("msg", "회원가입 성공");
		}
		
		return page;
	}
	
	@RequestMapping(value = "/overlay.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> overlay(String id) {
		
		logger.info("중복 체크 id : " + id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		int row = memberService.overlay(id);
		map.put("use", row);
		
		return map;
	}
	
	@RequestMapping(value = "/login.do")
	public String login(Model model, String id, String pw, HttpSession session) {
		logger.info("로그인 시도");
		String page = "login";
		MemberDTO info = memberService.login(id, pw);
		
		if(info != null) {
			page = "list";
			session.setAttribute("loginInfo", info);
			model.addAttribute("msg", "로그인 성공");
		}else {
			model.addAttribute("msg", "아이디 또는 비밀번호를 확인해주세요.");
		}
		
		return page;
	}
	
	@RequestMapping(value = "/logout.do")
	public String logout(HttpSession session) {
		logger.info("로그아웃 시도");
		String page = "login";
		session.removeAttribute("loginInfo");
		
		return page;
	}
	
	@RequestMapping(value = "/member_list")
	public String memberList(HttpSession session, Model model) {
		logger.info("멤버 리스트 보기");
		String page = "login";
		
		if(session.getAttribute("loginInfo") != null) {
			page = "member_list";
			List<MemberDTO> list = memberService.list();
			model.addAttribute("list", list);
		}
		
		return page;
	}
	
}























