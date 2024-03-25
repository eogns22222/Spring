package kr.co.photo.member.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.photo.board.dto.BoardDTO;
import kr.co.photo.member.dao.MemberDAO;

@Service
public class MemberService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MemberDAO dao;
	
	public boolean join(Map<String, String> param) {
		return dao.join(param);
	}

	public String login(String id, String pw) {
		return dao.login(id, pw);
	}

	public List<BoardDTO> list02() {
		return dao.list02();
	}
	
}
