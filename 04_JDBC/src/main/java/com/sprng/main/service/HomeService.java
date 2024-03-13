package com.sprng.main.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sprng.main.dao.HomeDAO;

// 클래스에서 일을 시키면 그 일을 처리하고 컨트롤러에 전달
public class HomeService {
	private static final Logger logger = LoggerFactory.getLogger(HomeService.class);
	
	public String dbConnect() {
		
		logger.info("컨트롤러에서 호출하면....");
		logger.info("DB 가 필요한 일인지에 따라 내가 처리하던지");
		logger.info("DAO에 시키던지 결정한다.");
		
		String msg = "DB 접속에 실패 했습니다.";
		
		HomeDAO dao = new HomeDAO();
		
		if (dao.dbConnect() == true) {
			msg = "DB 접속에 성공 했습니다.";
		}
		
		return msg;
	}
}
