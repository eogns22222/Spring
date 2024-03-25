package kr.co.photo.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.photo.board.dto.BoardDTO;
import kr.co.photo.board.service.BoardService;

@Controller
public class BoardController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BoardService service;
	
	@RequestMapping(value = "/list")
	public String list(Model model, HttpSession session) {
		logger.info("리스트");
		String page = "login";
		String id = (String) session.getAttribute("loginId");
		
		if(id != null) {
			page = "list";
			List<BoardDTO> list = service.list();
			model.addAttribute("list", list);
			model.addAttribute("loginBox", "<div> 안녕하세요 " + id + " 님 <a href='logout'>로그아웃</a></div>");
		}
		
		return page;
	}
	
	@RequestMapping(value = "/writeForm")
	public String writeForm(HttpSession session) {
		logger.info("글쓰기 폼");
		String page = "login";
		
		if(session.getAttribute("loginId") != null) {
			page = "writeForm";
		}
		
		return page;
	}
	
	@RequestMapping(value = "/write")
	public String write(MultipartFile[] photos, HttpSession session, @RequestParam Map<String, String> param) {
		logger.info("글쓰기 요청");
		String page = "login";
		
		if(session.getAttribute("loginId") != null) {
			page = "redirect:/list";
			service.write(param, photos);
		}
		
		return page;
	}
	
	@RequestMapping(value = "/detail")
	public String detail(HttpSession session, String idx, Model model) {
		logger.info("글쓰기 요청");
		String page = "login";
		
		if(session.getAttribute("loginId") != null) {
			page = "detail";
			service.detail(idx, model);
		}
		
		return page;
	}
	
	@RequestMapping(value = "/updateForm")
	public String updateForm(HttpSession session, String idx, Model model) {
		logger.info("글쓰기 요청");
		String page = "login";
		
		if(session.getAttribute("loginId") != null) {
			page = "updateForm";
			service.updateForm(idx, model);
		}
		
		return page;
	}
	
	@RequestMapping(value = "/update")
	public String update(MultipartFile[] photos, HttpSession session, @RequestParam Map<String, String> param) {
		logger.info("글쓰기 요청");
		String page = "login";
		
		if(session.getAttribute("loginId") != null) {
			page = "redirect:/detail?idx=" + param.get("idx");
			service.update(param, photos);
		}
		
		return page;
	}
	
	@RequestMapping(value = "/del")
	public String del(String idx, HttpSession session) {
		String page = "login";
		
		if(session.getAttribute("loginId") != null) {
			page = "redirect:/list";
			service.del(idx);
		}
		
		return page;
	}
	
	
}





























