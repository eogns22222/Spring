package kr.co.photo.board.dao;

import java.util.List;
import java.util.Map;

import kr.co.photo.board.dto.BoardDTO;

public interface BoardDAO {

	List<BoardDTO> list();

	void del(String idx);

	int write(Map<String, String> param);

	BoardDTO detail(String idx);

	void upHit(String idx);

	void update(Map<String, String> param);



}
