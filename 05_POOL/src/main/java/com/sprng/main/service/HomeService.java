package com.sprng.main.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sprng.main.dao.HomeDAO;

public class HomeService {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeService.class);
	
	public String service() {
		
		logger.info("서비스 접근");
		HomeDAO dao = new HomeDAO();
		boolean seccess = dao.dbConnect();
		
		String msg = "DB 접속에 실패 하였습니다.";
		
		if (seccess == true) {
			msg = "DB 접속에 성공 하였습니다.";
		}
		logger.info(msg);
		
		return msg;
	}

	public String poolConnect() {
		logger.info("pool service");
		String msg = "pool 접속에 실패 했습니다";
		
		HomeDAO dao = new HomeDAO();
		
		if(dao.poolConnect()) {
			msg = "pool 접속에 성공 했습니다.";
		} 
		
		return msg;
	}

}
