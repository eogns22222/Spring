package kr.co.photo.board.dao;

import java.util.List;
import java.util.Map;

import kr.co.photo.board.dto.BoardDTO;
import kr.co.photo.board.dto.PhotoDTO;

public interface BoardDAO {

	List<BoardDTO> list();

	int write(BoardDTO dto);

	void writeFile(String fileName, String new_fileName, int idx);

	void upHit(String idx);

	BoardDTO detail(String idx);

	List<PhotoDTO> photos(String idx);

	int update(Map<String, String> param);

	void del(String idx);


}
