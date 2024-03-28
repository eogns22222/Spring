package kr.co.home.member.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.home.member.dao.MemberDAO;
import kr.co.home.member.dto.MemberDTO;

@Service
public class MemberService {
	@Autowired MemberDAO MemberDAO;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public int join(Map<String, String> param) {
		logger.info("회원 가입 param 값");
		int row = MemberDAO.join(param);
		String perm = param.get("auth");
		if(perm != null) {
			MemberDAO.setPermission(param.get("id"),perm);
			
		}
		return row;
	}

	public MemberDTO login(String id, String pw) {
		logger.info("Id, Pw 확인");
		return MemberDAO.login(id,pw);
	}

	public int overlay(String id) {
		return MemberDAO.overlay(id);
	}

}
