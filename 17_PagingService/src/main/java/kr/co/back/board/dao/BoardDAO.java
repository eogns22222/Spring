package kr.co.back.board.dao;

import java.util.List;

import kr.co.back.board.dto.BoardDTO;

public interface BoardDAO {

	List<BoardDTO> list(int pagePerCnt, int start);

	Object allCount(int pagePerCnt);

}
