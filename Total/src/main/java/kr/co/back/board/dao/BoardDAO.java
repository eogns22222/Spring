package kr.co.back.board.dao;

import java.util.List;
import java.util.Map;

import kr.co.back.board.dto.BoardDTO;
import kr.co.back.board.dto.PhotoDTO;

public interface BoardDAO {

	List<BoardDTO> list(int pagePerCnt, int start);

	List<String> getFiles(String n);

	int del(String n);

	int allCnt(int pagePerCnt);

	int write(BoardDTO dto);

	void fileWrite(String file_name, String new_fileName, int idx);

	BoardDTO detail(String idx);

	List<PhotoDTO> photoWrite(String idx);
	
	void upHit(String idx);

	int update(Map<String, String> param);

}
