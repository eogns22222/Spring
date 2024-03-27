package kr.co.back.board.dao;

import java.util.List;

import kr.co.back.board.dto.BoardDTO;

public interface BoardDAO {

	List<BoardDTO> list();

	List<String> photos(String s);

	int del(String s);

}
