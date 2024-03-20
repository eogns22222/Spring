package kr.co.gudi.board.controller;

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

import kr.co.gudi.board.dto.BoardDTO;
import kr.co.gudi.board.service.BoardService;

@Controller
public class BoardController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BoardService service;
	
	@RequestMapping(value = "/list")
	public String list(Model model, HttpSession session) {
		String page = "login";
		String id = (String) session.getAttribute("loginId");
		
		if(id != null) {
			page = "list";
			List<BoardDTO> list = service.list();
			logger.info("list size : " + list.size());
			model.addAttribute("list", list);
			model.addAttribute("loginBox", "<div>안녕하세요 "+ id +  " 님 <a href='logout'>로그아웃</a></div>");
		}else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
			
		}
		
		return page;
	}
	
	@RequestMapping(value = "/del")
	public String del(HttpSession session, int idx) {
		String page = "login";
		logger.info("delete idx : " + idx);
		
		if(session.getAttribute("loginId") != null) {
			page = "redirect:/list";
			service.del(idx);
		}
		
		return page;
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginId");
		return "redirect:/";
	}
	
	@RequestMapping(value = "/writeForm")
	public String writeForm() {
		return "writeForm";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(HttpSession session, @RequestParam Map<String, String> param) {
		
		logger.info("params = {} ", param);
		String page = "redirect:/list";
		
		if(session.getAttribute("loginId") != null) {
			int row = service.write(param);
			if(row < 1) {
				page = "writeForm";
			}
		}
		
		return page;
	}
	
	@RequestMapping(value = "/detail")
	public String detail(String idx, HttpSession session, Model model) {
		String page = "redirect:/list";
		logger.info("detail idx = " + idx);
		
		if(session.getAttribute("loginId") != null) {
			BoardDTO bbs = service.detail(idx);
			model.addAttribute("bbs", bbs);
			page = "detail";
		}
		
		return page;
	}
	
}



























