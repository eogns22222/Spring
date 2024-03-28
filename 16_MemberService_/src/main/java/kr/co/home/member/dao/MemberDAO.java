package kr.co.home.member.dao;

import java.util.Map;

import kr.co.home.member.dto.MemberDTO;

public interface MemberDAO {

	int join(Map<String, String> param);

	void setPermission(String string, String perm);

	MemberDTO login(String id, String pw);

	int overlay(String id);

}
