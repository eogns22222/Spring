package kr.co.back.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.back.board.dto.BoardDTO;
import kr.co.back.board.service.BoardService;

@Controller
public class BoardController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BoardService boardService;
	
	@RequestMapping(value = "/list.ajax")
	@ResponseBody
	public Map<String, Object> list(){
		logger.info("리스트 요청");
		Map<String, Object> map = new HashMap<String, Object>();
		List<BoardDTO> list = boardService.list();
		map.put("list", list);
		
		return map;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Map<String, Object> del(@RequestParam(value = "delList[]") List<String> delList){
		logger.info("삭제 요청");
		
		int cnt = boardService.del(delList);
		logger.info("cnt : " + cnt);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cnt", cnt);
		
		return map;
	}
	
}






















