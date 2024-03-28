package kr.co.home.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.home.board.dto.BoardDTO;
import kr.co.home.board.service.BoardService;
import kr.co.home.member.service.MemberService;

@Controller
public class BoardController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BoardService boardSevice;
	
	@RequestMapping(value="/list")
	public String list(Model model) {
		logger.info("list 요청");
		//List<BoardDTO> list = boardService.list();
		//model.addAttribute("list", list); // responce 로 map 형태로 보내야 된다.
		return "list";
	}
	
	// 비돟기 방식 : 일단 페이지 도착 한 다음
	@RequestMapping(value="/list.ajax")
	@ResponseBody // response 객체로 반환
	public Map<String, Object> listCall(String page, String cnt) {
		logger.info("listCall 요청");
		logger.info("페이지당 보여줄 갯수 : " + cnt);
		logger.info("요청 페이지 : " + page);
		
		int currPage = Integer.parseInt(page);
		int pagePerCnt = Integer.parseInt(cnt);
		
		Map<String, Object> map = boardSevice.list(currPage, pagePerCnt);
		
		return map; //json 과 가자 닮은 map으로 반환
	}
	
	// 배열 형태로 들어올 경우 따로 명시를 해줘야 한다.
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody // response 객체로 반환
	public Map<String, Object> del(@RequestParam(value="delList[]") List<String> delList) {
		logger.info("del List : {}",delList);
		
		int cnt = boardSevice.del(delList);
		logger.info("del count :"+cnt);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cnt", cnt);
		
		return map; //json 과 가자 닮은 map으로 반환
	}
	
}































