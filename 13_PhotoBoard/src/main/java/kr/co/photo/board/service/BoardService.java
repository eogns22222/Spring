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
		return dao.list();
	}
	public void del(int idx) {
		dao.del(idx);
	}
	public void write(Map<String, String> param) {
		dao.write(param);
	}
	public BoardDTO detail(int idx) {
		dao.upHit(idx);
		return dao.detail(idx);
	}
	public BoardDTO updateForm(int idx) {
		return dao.detail(idx);
	}
	public void update(Map<String, String> param) {
		dao.update(param);
	}
}






















