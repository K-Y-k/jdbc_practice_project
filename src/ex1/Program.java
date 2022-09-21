package ex1;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Program {

	
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";  
		String sql = "SELECT * FROM NOTICE";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1"; // 오라클 thin 타입의 드라이버, 데이터베이스 서버 IP, 서비스하는 리스너의 포트번호, 서비스이름 
		String user = "kyk";
		String password = "kim690715";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
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
				
				System.out.printf("id : %d, title: %s, writerId: %s, date: %s, content: %s, hit: %d\n"
						, id, title, writerId, regDate, content, hit);
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


