package kr.co.home.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.home.board.dao.BoardDAO;
import kr.co.home.board.dto.BoardDTO;

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

	public int del(List<String> delList) {
		int cnt = 0;
		//1.게시글에 연결된 파일명
		for (String idx : delList) {
			List<String> files = boardDAO.getFiles(idx);
			//2.bbs 에서 해당 글 삭제
			cnt += boardDAO.del(idx);
			//3.파일 삭제 List로 받아야 겠지 for문으로 돌려서 하나씩 지워라
			logger.info("files : {} ",files);
			delFile(files);
		}
		
		
		return cnt;
	}

	private void delFile(List<String> files) {
		
	}
}
