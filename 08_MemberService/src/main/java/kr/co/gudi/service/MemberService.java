package kr.co.gudi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.gudi.dao.MemberDAO;

public class MemberService {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	public int join(String id, String pw, String name, String age, String gender, String email) {
		MemberDAO dao = new MemberDAO();
		
		return dao.join(id,pw,name,age,gender,email);
	}

	public boolean login(String id, String pw) {
		MemberDAO dao = new MemberDAO();
		
		return dao.login(id, pw);
	}

	public void list() {
		MemberDAO dao = new MemberDAO();
		dao.list();
	}


}
