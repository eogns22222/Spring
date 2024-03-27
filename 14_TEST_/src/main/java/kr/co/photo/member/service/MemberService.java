package kr.co.photo.member.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.photo.member.dao.MemberDAO;
import kr.co.photo.member.dto.MemberDTO;

@Service
public class MemberService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired MemberDAO dao;
	
	public int join(Map<String, String> param) {
		logger.info("회원가입 param값");
		
		int row = dao.join(param); // 부모 테이블에 데이터를 넣고
		
		String perm = param.get("auth"); 
		if(perm != null) {
			dao.setPermission(param.get("id"), perm); // 이후 자식 테이블에 넣어야 한다.
		}
		
		return row;
	}

	public MemberDTO login(String id, String pw) {
		logger.info("아이디 패스워드 들어왔다.");
		return dao.login(id,pw);
	}

	public List<MemberDTO> memberList() {
		return dao.memberList();
	}

	public int overlay(String id) {
		return dao.overlay(id);
	}

}

























