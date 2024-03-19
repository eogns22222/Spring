package kr.co.gudi.dao;

import java.util.List;
import java.util.Map;

import kr.co.gudi.dto.MemberDTO;

public interface MemberDAO {

	int join(Map<String, String> param);

	String login(String id, String pw);

	// 만약 결과값이 한개라면 List 를 사용하지 않아도 된다.
	List<MemberDTO> list();

	MemberDTO detail(String id);

	int delete(String id);
	
}
