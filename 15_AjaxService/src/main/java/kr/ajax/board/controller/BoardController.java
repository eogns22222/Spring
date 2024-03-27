package kr.ajax.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ajax.board.dto.BoardDTO;
import kr.ajax.board.service.BoardService;

@Controller
public class BoardController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BoardService boardService;
	
	@RequestMapping(value = "/")
	public String list(Model model, String idx) {
		logger.info("접속");
		
		List<BoardDTO> list = boardService.list();
		model.addAttribute("list", list);
		
		return "list";
	}
	
	@RequestMapping(value = "/del")
	public String del(String idx) {
		logger.info("delete idx : " + idx);
		String page = "redirect:/";
		boardService.del(idx);
		
		return page;
	}

}































