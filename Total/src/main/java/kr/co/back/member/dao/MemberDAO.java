package kr.co.back.member.dao;

import java.util.List;
import java.util.Map;

import kr.co.back.member.dto.MemberDTO;

public interface MemberDAO {

	int join(Map<String, String> param);

	void setPermission(String id, String param);

	MemberDTO login(String id, String pw);

	int overlay(String id);

	List<MemberDTO> list();

}
