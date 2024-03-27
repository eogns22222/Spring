package kr.ajax.board.dao;

import java.util.List;

import kr.ajax.board.dto.BoardDTO;
import kr.ajax.board.dto.PhotoDTO;

public interface BoardDAO {

	List<BoardDTO> list();

	List<PhotoDTO> photos(String s);

	void del(String s);

	List<String> getFiles(String idx);

	int del2(String idx);

}
