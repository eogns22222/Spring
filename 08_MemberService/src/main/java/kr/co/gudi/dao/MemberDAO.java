package kr.co.gudi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemberDAO {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	Connection conn = null;

	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MariaDB");
			conn = ds.getConnection();
			logger.info("connection : " + conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int join(String id, String pw, String name, String age, String gender, String email) {
		int row = -1;
		
		// 1. 쿼리 준비
		String sql = "insert into member(id,pw,name,age,gender,email)values(?,?,?,?,?,?)";
		try {
			// 2. 실행 객체 준비
			PreparedStatement ps = conn.prepareStatement(sql);
			// 2-1. ? 대입
			ps.setString(1, id);
			ps.setString(2, pw);
			ps.setString(3, name);
			ps.setInt(4, Integer.parseInt(age));
			ps.setString(5, gender);
			ps.setString(6, email);
			// 3. 실행
			row = ps.executeUpdate();
			// 3. 자원 반납
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return row;
	}

	public boolean login(String id, String pw) {
		
		boolean success = false;
		
		// 1. 쿼리문 준비
		String sql = "select id from member where id = ? and pw = ?";
		try {
			// 2. 실행 객체 준비
			PreparedStatement ps = conn.prepareStatement(sql);
			// 2-1. ? 대입
			ps.setString(1, id);
			ps.setString(2, pw);
			// 3. 실행
			ResultSet rs = ps.executeQuery();
			// 4. 값 꺼내기
			success = rs.next();
			// 5. 자원 반납
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return success;
	}

	public void list() {
		// 1. 쿼리문 준비
		String sql = "SELECT id,name,age,email FROM member";
		try {
			// 2. 실행 객체 준비
			PreparedStatement ps = conn.prepareStatement(sql);
			// 3. 실행
			ResultSet rs = ps.executeQuery();
			// 4. 데이터 가져오기
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				logger.info(id + " " + name + " " + age + " " + email);
			}
			// 5. 자원 반납
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}






















