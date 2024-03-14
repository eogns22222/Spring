package com.sprng.main.service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sprng.main.dao.HomeDAO;

public class HomeService {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeService.class);	

	public String createTable() {
		logger.info("DAO 객체화 후 호출");
		
		String msg = "테이블 생성에 실패 했습니다.";
		HomeDAO dao = new HomeDAO();
		
		try {
			if(dao.createTable()) {
				msg = "테이블 생성에 성공 했습니다.";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return msg;
	}

	public int insert(String id, String pw, String name, String age, String gender, String email) {
		
		HomeDAO dao = new HomeDAO();
		int row = dao.insert(id,pw,name,age,gender,email);
		logger.info("dao 로부터 받은 값 : " + row);
		
		return row;
	}

	public void list() {
		
		HomeDAO dao = new HomeDAO();
		dao.list();
		
	}


}









