package com.sprng.main.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeDAO.class);
	
	public boolean dbConnect() {
		boolean result = false;
		
		logger.info("DAO 접근");
		
		//1. 정보 준비
		String id = "web_user";
		String pw = "pass";
		String url = "jdbc:mariadb://localhost:3306/mydb";
		String driver = "org.mariadb.jdbc.Driver";
		
		try {
			//2. 드라이버 매니저 호출
			Class.forName(driver);
			
			//3. 연결
			Connection conn = DriverManager.getConnection(url, id, pw);
			
			//4. 할일
			if (conn != null) {
				result = true;
				conn.close(); // 4. 사용한 커넥션 반납
			}
			
			//5. 자원 반납
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info(result + "");
		return result;
	}
	
	/*
	<Resource
    	name="jdbc/MariaDB" <- 호출할 이름
    	auth="Container" 		<- 제어를 누가 하게 할것인가?(톰캣|Spring|개발자)
    	type="javax.sql.DataSource" <- xml 의 태그를 자바의 어떤 데이터 타입으로 변경할 것인가?
    	driverClassName="org.mariadb.jdbc.Driver" 
    	url="jdbc:mariadb://localhost:3306/mydb"
    	username="web_user"
    	password="pass"
    />
	 */

	public boolean poolConnect() {
		boolean success = false;
		try {
			Context ctx = new InitialContext(); // 1. context.xml 을 자바 객체로 변환
			// 2. Resource 태그를 이름을 사용해서 찾아온다. -> java 형태로 변환한다.
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MariaDB");
			// 3. 커넥션을 불러온다.
			Connection conn = ds.getConnection();
			logger.info("pool connection : " + conn);
			if(conn != null) {
				success = true;
				conn.close(); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}
	
	
	
}









