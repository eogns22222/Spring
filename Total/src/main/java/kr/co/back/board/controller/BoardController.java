package kr.co.back.board.controller;

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
import org.springframework.web.multipart.MultipartFile;

import kr.co.back.board.service.BoardService;

@Controller
public class BoardController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BoardService boardService;
	
	@RequestMapping(value = "/list")
	public String listForm() {
		return "list";
	}
	
	@RequestMapping(value = "/list.ajax", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(String page, String cnt){
		logger.info("리스트 출력");
		logger.info("page : cnt = " + page + " : " + cnt);
		
		int currPage = Integer.parseInt(page);
		int pagePerCnt = Integer.parseInt(cnt);
		
		Map<String, Object> map = boardService.list(currPage, pagePerCnt);
		
		return map;
	}
	
	@RequestMapping(value = "/del.ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> del(@RequestParam(value = "delArr[]") List<String> delList){
		logger.info("delList : {}", delList);
		
		int cnt = boardService.del(delList);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cnt", cnt);
		
		return map;
	}
	
	@RequestMapping(value = "/write.go")
	public String writeForm(HttpSession session) {
		logger.info("글쓰기 이동");
		String page = "login";
		
		if(session.getAttribute("loginInfo") != null) {
			page = "writeForm";
		}
		
		return page;
	}
	
	@RequestMapping(value = "/write.do", method = RequestMethod.POST)
	public String write(MultipartFile[] photos, HttpSession session, @RequestParam Map<String, String> param) {
		logger.info("글쓰기");
		String page = "login";
		
		if(session.getAttribute("loginInfo") != null) {
			page = "list";
			boardService.write(photos, param);
		}
		
		return page;
	}
	
	@RequestMapping(value = "/detail.go")
	public String detail(HttpSession session, Model model, String idx) {
		logger.info("상세보기 이동");
		String page = "login";
		
		if(session.getAttribute("loginInfo") != null) {
			page = "detail";
			boardService.detail(idx, model);
		}
		
		return page;
	}
	
	@RequestMapping(value = "/updateForm.go")
	public String updateForm(HttpSession session, Model model, String idx) {
		logger.info("수정폼 이동");
		String page = "login";
		
		if(session.getAttribute("loginInfo") != null) {
			page = "updateForm";
			boardService.updateForm(idx, model);
		}
		
		return page;
	}
	
	@RequestMapping(value = "/update.do")
	public String update(MultipartFile[] photos, HttpSession session, @RequestParam Map<String,String> param) {
		logger.info("수정");
		String page = "login";
		
		if(session.getAttribute("loginInfo") != null) {
			page = "list";
			boardService.update(photos, param);
		}
		
		return page;
	}
	
}































