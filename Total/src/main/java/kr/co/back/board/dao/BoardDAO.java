package kr.co.back.board.dao;

import java.util.List;

import kr.co.back.board.dto.BoardDTO;

public interface BoardDAO {

	List<BoardDTO> list(int pagePerNum, int start);

	Object allCount(int pagePerNum);

	int write(BoardDTO dto);

	void writeFile(String file_name, String new_fileName, int idx);
	
}





























