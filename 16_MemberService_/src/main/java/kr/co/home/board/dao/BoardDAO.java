package kr.co.home.board.dao;

import java.util.List;

import kr.co.home.board.dto.BoardDTO;

public interface BoardDAO {

	List<BoardDTO> list(int pagePerCnt, int start);

	int del(String idx);

	List<String> getFiles(String idx);

	int allCount(int pagePerCnt);

}
