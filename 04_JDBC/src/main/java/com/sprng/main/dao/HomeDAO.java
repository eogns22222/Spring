package com.sprng.main.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeDAO {
	private static final Logger logger = LoggerFactory.getLogger(HomeDAO.class);
	
	/**
	 *  데이터베이스 접속 후 성공 여부를 반환해 주는 메서드
	 * @return 성공 여부를 true 또는 false 로 반환
	 */
	public boolean dbConnect() {
		
		logger.info("DB가 필요하면 이게 실행되어야 한다.");
		boolean success = false;
		
		// 1. 접속 정보 준비
		String id = "web_user";
		String pw = "pass";
		String url = "jdbc:mariadb://localhost:3306/mydb";
		String driver = "org.mariadb.jdbc.Driver";
		
		try {
			// 2. 드라이버 매니저 호출
			Class.forName(driver);
			
			// 접속이 성공 되면 Connection 객체 생성
			
			// 3. 만능 열쇠(Connection) 을 받는다.
			Connection conn = DriverManager.getConnection(url, id, pw);
			
			// 4. 할거 하고....
			if (conn != null) {
				success = true;
			}
			
			// 5. Connection 반납
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return success;
	}
	
}
