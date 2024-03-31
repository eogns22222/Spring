package kr.co.back.member.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.back.member.dao.MemberDAO;
import kr.co.back.member.dto.MemberDTO;

@Service
public class MemberService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MemberDAO memberDAO;
	
	public int join(Map<String, String> params) {
		int row = 0;
		
		row = memberDAO.join(params);
		
		String perm = params.get("auth");
		if(perm != null) {
			memberDAO.setPermission(params.get("id"), perm);
		}
		
		return row;
	}

	public int overlay(String id) {
		return memberDAO.overlay(id);
	}

	public MemberDTO login(String id, String pw) {
		return memberDAO.login(id, pw);
	}


	
}

























