package com.sprng.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeDAO {
	private static final Logger logger = LoggerFactory.getLogger(HomeDAO.class);	
	Connection conn = null;

	public HomeDAO() {
		try {
			// 1. context.xml 을 객체화
			Context ctx = new InitialContext();
			// 2. 이름으로 Resource 태그를 찾아서 DataSource 로 변환
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MariaDB");
			// 3. DataSource 로 Connection 가져오기
			conn = ds.getConnection();
			logger.info("connection : " + conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public boolean createTable() throws SQLException {
		boolean success = false;
		
		// 1. 쿼리문 준비(쿼리문의 ; 은 반드시 빼줘야 한다.)
		String sql = "create table member("
				+ "	ID varchar(50) primary key,"
				+ "	PW varchar(100),"
				+ "	NAME varchar(50),"
				+ "	AGE int(4),"
				+ "	GENDER varchar(4),"
				+ "	EMAIL varchar(100)"
				+ ")";
		
		// 2. 실행할 객체 준비(Statement | PreparedStatement)
		Statement stmt = conn.createStatement();
		
		// 3. 쿼리문 실행(executeQuery | executeUpdate)
		int row = stmt.executeUpdate(sql);
		logger.info("영향받는 데이터 수 : " + row);
		success = true;
		
		// 4. 자원 정리
		stmt.close();
		conn.close();
		
		return success;
	}

	public int insert(String id, String pw, String name, String age, String gender, String email) {
		int row = -1;
		// 1. 쿼리문 준비
		String sql = "insert into member(id,pw,name,age,gender,email)values(?,?,?,?,?,?)";
		try {
			// 2. 실행 객체(Statement | PreparedStatement)
			PreparedStatement ps = conn.prepareStatement(sql);
			// 2-1. ? 대입
			ps.setString(1, id);
			ps.setString(2, pw);
			ps.setString(3, name);
			ps.setInt(4, Integer.parseInt(age));
			ps.setString(5, gender);
			ps.setString(6, email);
			// 3. 쿼리 실행(executeQuery | executeUpdate)
			row = ps.executeUpdate();
			// 4. 자원 정리
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return row;
	}

	public void list() {
		// 1. 쿼리문 준비
		String sql = "select * from member";
		try {
			// 2. 실행 객체 준비
			PreparedStatement ps = conn.prepareStatement(sql);
			// 3. 쿼리 실행
			ResultSet rs = ps.executeQuery(sql);
			
			// 4. 값 가져오기
			while(rs.next()) {
				logger.info("id : {}", rs.getString("id")); // column label
				logger.info("pw : {}", rs.getString(2)); // column index
				logger.info("name : {}", rs.getString("name"));
				logger.info("age : {}", rs.getInt("age"));
				logger.info("gender : {}", rs.getString("gender"));
				logger.info("email : {}", rs.getString("email"));
				logger.info("======================");
			}
			
			// 5. 자원 정리
			ps.close();
			rs.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

}










