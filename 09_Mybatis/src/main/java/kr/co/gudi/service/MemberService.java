package kr.co.gudi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.gudi.dao.MemberDAO;

@Service // 서비스 라고 표시해주는 어노테이션(이걸하면 Controller 에서 객체화하지 않고 사용 가능)
public class MemberService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	// MemberDAO 를 가져다가 사용한다.
	@Autowired MemberDAO dao;

	public int join(String id, String pw, String name, String age, String gender, String email) {
		
		int row = dao.join(id, pw, name, age, gender, email);
		logger.info("들어간 데이터 수 : " + row);
		
		return row;
	}
	
}





















