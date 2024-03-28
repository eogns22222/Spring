package kr.co.back.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.back.board.dao.BoardDAO;
import kr.co.back.board.dto.BoardDTO;

@Service
public class BoardService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BoardDAO boardDAO;
	
	public Map<String, Object> list(int currPage, int pagePerCnt) {
		
		int start = (currPage-1) * pagePerCnt;
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<BoardDTO> list = boardDAO.list(pagePerCnt,start);
		logger.info("pagePerCnt : " + pagePerCnt);
		logger.info("list size : "+ list.size());
		result.put("list", list);		
		result.put("currPage", currPage);
		result.put("totalPages", boardDAO.allCount(pagePerCnt));
		
		return result;
	}
	
}




























