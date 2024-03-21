package kr.co.photo.board.dao;

import java.util.List;
import java.util.Map;

import kr.co.photo.board.dto.BoardDTO;

public interface BoardDAO {

	List<BoardDTO> list();

	void del(int idx);

	void write(Map<String, String> param);

	BoardDTO detail(int idx);

	void upHit(int idx);

	void update(Map<String, String> param);


}
