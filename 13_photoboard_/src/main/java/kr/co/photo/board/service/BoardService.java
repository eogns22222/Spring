package kr.co.photo.board.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.photo.board.dao.BoardDAO;
import kr.co.photo.board.dto.BoardDTO;

@Service
public class BoardService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BoardDAO dao; 
	
	public List<BoardDTO> list() {
		logger.info("[리스트]도착");
		return dao.list();
	}

	public void del(String idx) {
		dao.del(idx);
		
	}

	public int write(Map<String, String> param) {
		int row = -1;
		row = dao.write(param);
		return row;
	}

	public BoardDTO detail(String idx) {
		dao.upHit(idx);
		return dao.detail(idx);
	}

	public BoardDTO updateForm(String idx) {
		return dao.detail(idx);
	}

	public void update(Map<String, String> param) {
		dao.update(param);
	}

}















