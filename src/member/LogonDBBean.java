package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogonDBBean {
	// 싱글턴 패턴 <== 쓰레드로 여러사람이 이 빈의 객체에 동시 접근 하는것을 방지하기 위해 사용
	private static LogonDBBean instance = new LogonDBBean();

	public static LogonDBBean getInstance() {
		return instance;
	}

	private LogonDBBean() {
	}

	// 연결하는건데 이게 뭔지 모르겠다. ServletContextEvent
	private Connection getConnection() throws Exception {
		// 반드시 수정 필요
		Connection conn = null;
		return conn;
	}

	public void insertMember(LogonDataBean member) throws Exception {
		// sql 쿼리 삽입 테이블 칼럼이 몇 개 필요한지 몰라서 작성 안함
		String sql = "insert into 테이블명 values(?,?,?,?.....)";
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			// 맴버 정보 선언
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 연결 닫아줌
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle) {
				}
		}
	}

	public int userCheck(String id, String passwd) throws Exception{
		//sql 문
		String sql = "select passwd from member where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd ="";
		//x는 인증관련 숫자
		int x = -1;
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbpasswd = rs.getString("passwd");
				if(dbpasswd.equals(passwd))
					x=1;		//인증 성공
				else
					x=0;		//인증 실패
			}else
				x=-1;			//해당 아이디 없다
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(pstmt != null)
				try { pstmt.close();}catch(SQLException sqle) {}
			if(conn != null)
				try { conn.close();}catch(SQLException sqle) {}
			if(rs !=null)
				try { rs.close(); } catch(SQLException sqle) {}
		}
		return x;
	}
}
