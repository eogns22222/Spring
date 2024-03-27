package kr.ajax.board.dao;

import java.util.List;

import kr.ajax.board.dto.BoardDTO;

public interface BoardDAO {

	List<BoardDTO> list();

	void del(String idx);

}
