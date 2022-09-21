package ex1;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Program_INSERT {

	
	public static void main(String[] args) {
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
			String title = "자바란 무엇인가?";
			String writerId = "kyk6";
			String content = "test content6";
			String files = "";
			
			String sql = "INSERT INTO NOTICE (title, writer_id, content, files) VALUES(?, ?, ?, ?)";
			
			
			conn = DriverManager.getConnection(url, user, password); // 데이터베이스의 연결 설정
			System.out.println("오라클 연결 성공");
			
			PreparedStatement ppstmt = conn.prepareStatement(sql);
			ppstmt.setString(1, title);
			ppstmt.setString(2, writerId);
			ppstmt.setString(3, content);
			ppstmt.setString(4, files);
			
			int result = ppstmt.executeUpdate();
			System.out.println(result);
		
			
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} finally { // 마지막 연결한 자원 종료
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
}
	
	
// public static void main(String[] args) throws ClassNotFoundException, SQLException {
//  
//  String url = "jdbc:oracle:thin:@localhost:1521/xepdb1"; 
//  String sql = "SELECT * FROM NOTICE";
//  
//  Class.forName("oracle.jdbc.driber.OracleDriver"); 
//  Connection con =DriverManager.getConnection(url, "kyk", "kim690715"); 
//  Statement st = con.createStatement(); 
//  ResultSet rs = st.executeQuery(sql);
//  
//  if(rs.next()) { // 레코드를 하나씩 가져오는 작업 String title = rs.getString("TITLE");
//  	System.out.println(title); 
//  }
//  
// rs.close(); 
// st.close(); 
// con.close();
// 
//}


