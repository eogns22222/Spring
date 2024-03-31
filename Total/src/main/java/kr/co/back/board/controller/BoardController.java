package kr.co.back.board.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.back.board.service.BoardService;

@Controller
public class BoardController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BoardService boardServicer;
	
	@RequestMapping(value = "/list.ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(String page, String cnt){
		logger.info("리스트 ajax 출력");
		
		int currPage = Integer.parseInt(page);
		int pagePerNum = Integer.parseInt(cnt);
		
		Map<String, Object> map = boardServicer.list(currPage, pagePerNum);
		logger.info("cnt : " + cnt);
		
		return map;
	}
	
	@RequestMapping(value = "write.go")
	public String writeForm() {
		logger.info("글쓰기 폼 이동");
		
		return "writeForm";
	}
	
	@RequestMapping(value = "write.do")
	public String write(MultipartFile[] photos, @RequestParam Map<String, String> param) {
		logger.info("글쓰기 요청");
		String page = "writeForm";
		
		int row = boardServicer.write(photos, param);
		
		if(row > 0) {
			page = "list";
		}
		
		return page;
	}
	
}



















