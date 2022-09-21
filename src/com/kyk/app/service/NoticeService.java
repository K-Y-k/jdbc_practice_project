package com.kyk.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kyk.app.entity.Notice;

public class NoticeService {
	private String driver = "oracle.jdbc.driver.OracleDriver";  // JDBC 드라이버
	private String url = "jdbc:oracle:thin:@localhost:1521/xepdb1"; // 오라클 thin 타입의 드라이버, 데이터베이스 서버 IP, 서비스하는 리스너의 포트번호, 서비스이름 
	
	private String user = "kyk";           // 데이터베이스 서버 id password
	private String password = "kim690715";
	
	private int result_Update = 0; // 업데이트 반환 변수
	
	
	public List<Notice> getList() {
		String sql = "SELECT * FROM NOTICE";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<Notice> list = new ArrayList<Notice>();
		
		try { // JDBC 드라이버를 로딩
			Class.forName(driver);  
			System.out.println("jdbc driver 로딩 성공");
			
		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
		}
		
		try { // 데이터베이스의 연결 및 sql 작업실행
			conn = DriverManager.getConnection(url, user, password); // 데이터베이스의 연결 설정
			System.out.println("오라클 연결 성공");
			
			stmt = conn.createStatement(); // Statement 가져옴
			rs = stmt.executeQuery(sql);   // SQL문 실행
			
			while(rs.next()) {
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regDate = rs.getDate("REGDATE");
				String content = rs.getString("CONTENT");
				int hit = rs.getInt("hit");
				String files = rs.getString("FILES");
				
				Notice notice = new Notice(id, title, writerId, regDate, content, hit, files);
				
				list.add(notice);
				
			}
			
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} finally { // 마지막 연결한 자원 종료
			try {
				rs.close(); 
				stmt.close(); 
				conn.close();
				
			} catch (SQLException e) {
			}
		}
		return list;
	}

	public int insert(Notice notice) {
		Connection conn = null;
		
		try { // JDBC 드라이버를 로딩
			Class.forName(driver);  
			System.out.println("jdbc driver 로딩 성공");
			
		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
		}
		
		try { // 데이터베이스의 연결 및 sql 작업실행
			String title = notice.getTitle();
			String writerId = notice.getWriterId();
			String content = notice.getContent();
			String files = notice.getFiles();
			
			String sql = "INSERT INTO NOTICE (title, writer_id, content, files) VALUES(?, ?, ?, ?)";
			
			conn = DriverManager.getConnection(url, user, password); // 데이터베이스의 연결 설정
			System.out.println("오라클 연결 성공");
			
			PreparedStatement ppstmt = conn.prepareStatement(sql);
			ppstmt.setString(1, title);
			ppstmt.setString(2, writerId);
			ppstmt.setString(3, content);
			ppstmt.setString(4, files);
			
			result_Update = ppstmt.executeUpdate();
			System.out.println(result_Update);
			
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} finally { // 마지막 연결한 자원 종료
			try {
				conn.close();
				
			} catch (SQLException e) {
			}
		}
		return result_Update;
	}
	
	public int update(Notice notice) {
		Connection conn = null;
		
		try { // JDBC 드라이버를 로딩
			Class.forName(driver);  
			System.out.println("jdbc driver 로딩 성공");
			
		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
		}
		
		try { // 데이터베이스의 연결 및 sql 작업실행
			String title = notice.getTitle();
			String content = notice.getContent();
			String files = notice.getFiles();
			int id = notice.getId();
			
			String sql = "UPDATE NOTICE "
					+ "SET"
					+ "	TITLE=?,"
					+ "	CONTENT=?,"
					+ "	FILES=?"
					+ "WHERE  ID=?";
			
			
			conn = DriverManager.getConnection(url, user, password); // 데이터베이스의 연결 설정
			System.out.println("오라클 연결 성공");
			
			PreparedStatement ppstmt = conn.prepareStatement(sql);
			ppstmt.setString(1, title);
			ppstmt.setString(2, content);
			ppstmt.setString(3, files);
			ppstmt.setInt(4, id);
			
			result_Update = ppstmt.executeUpdate();
			System.out.println(result_Update);
		
			
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} finally { // 마지막 연결한 자원 종료
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
		return result_Update;
	}
	
	public int delete(int id) {
		String driver = "oracle.jdbc.driver.OracleDriver";  
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1"; // 오라클 thin 타입의 드라이버, 데이터베이스 서버 IP, 서비스하는 리스너의 포트번호, 서비스이름 
		String user = "kyk";
		String password = "kim690715";
		
		Connection conn = null;
		
		
		try { // JDBC 드라이버를 로딩
			Class.forName(driver);  
			System.out.println("jdbc driver 로딩 성공");
			
		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
		}
		
		try { // 데이터베이스의 연결 및 sql 작업실행
			
			String sql = "DELETE NOTICE WHERE ID=?";
				
			conn = DriverManager.getConnection(url, user, password); // 데이터베이스의 연결 설정
			System.out.println("오라클 연결 성공");
			
			PreparedStatement ppstmt = conn.prepareStatement(sql);
			ppstmt.setInt(1, id);
			
			result_Update = ppstmt.executeUpdate();
			System.out.println(result_Update);
		
			
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} finally { // 마지막 연결한 자원 종료
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
		return result_Update;
	}
	
}
