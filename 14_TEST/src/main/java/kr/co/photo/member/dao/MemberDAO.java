package kr.co.photo.member.dao;

import java.util.List;
import java.util.Map;

import kr.co.photo.board.dto.BoardDTO;

public interface MemberDAO {

	boolean join(Map<String, String> param);

	String login(String id, String pw);

	List<BoardDTO> list02();

}
