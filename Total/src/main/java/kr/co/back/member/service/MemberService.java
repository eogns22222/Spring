package kr.co.back.member.service;

import java.util.List;
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
	
	public int join(Map<String, String> param) {
		int row = -1;
		
		row = memberDAO.join(param);
		
		String perm = param.get("auth");
		
		if(perm != null) {
			memberDAO.setPermission(param.get("id"), perm);
		}
		
		return row;
	}

	public MemberDTO login(String id, String pw) {
		return memberDAO.login(id, pw);
	}

	public int overlay(String id) {
		return memberDAO.overlay(id);
	}

	public List<MemberDTO> list() {
		return memberDAO.list();
	}
	
}






























