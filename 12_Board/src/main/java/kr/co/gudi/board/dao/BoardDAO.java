package kr.co.gudi.board.dao;

import java.util.List;
import java.util.Map;

import kr.co.gudi.board.dto.BoardDTO;

public interface BoardDAO {

	List<BoardDTO> list();

	void del(int idx);

	int write(Map<String, String> param);

	void upHit(String idx);
	
	BoardDTO detail(String idx);

}
