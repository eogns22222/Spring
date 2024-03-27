package kr.co.back.member.dao;

import java.util.Map;

import kr.co.back.member.dto.MemberDTO;

public interface MemberDAO {

	int join(Map<String, String> param);

	void setPermission(String id, String perm);

	Object overlay(String id);

	MemberDTO login(String id, String pw);

}
