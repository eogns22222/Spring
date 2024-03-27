package kr.co.back.board.service;

import java.io.File;
import java.util.List;

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
	String file_root = "C:/upload";
	
	public List<BoardDTO> list() {
		return boardDAO.list();
	}
	public int del(List<String> delList) {
		int cnt = 0;
		// 1. 게시글에 연결된 파일명 확보
		for (String s : delList) {
			
			List<String> list = boardDAO.photos(s);
			// 2. bbs 에서 해당 글 삭제
			cnt += boardDAO.del(s);
			logger.info("cnt : " + cnt);
			// 3. 파일 삭제
			delFile(list);
		}
		
		return cnt;
	}
	private void delFile(List<String> list) {
		for (String s : list) {
			File file = new File(file_root + s);
			if(file.exists()) {
				file.delete();
			}
		}
	}
	
}













