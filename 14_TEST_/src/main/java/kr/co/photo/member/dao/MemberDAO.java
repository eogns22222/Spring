package kr.co.photo.member.dao;

import java.util.List;
import java.util.Map;

import kr.co.photo.member.dto.MemberDTO;

public interface MemberDAO {

	int join(Map<String, String> param);

	MemberDTO login(String id, String pw);

	List<MemberDTO> memberList();

	void setPermission(String id, String perm);

	int overlay(String id);

}
