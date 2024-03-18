package kr.co.gudi.dao;

public interface MemberDAO {

	int join(String id, String pw, String name, String age, String gender, String email);

}
