package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.ConnectionProvider;

public class LogonDBBean {
	private static LogonDBBean instance = new LogonDBBean();

	public static LogonDBBean getInstance() {
		return instance;
	}

	private LogonDBBean() {
	}

	public void insertMember(LogonDataBean member) throws Exception {
		String sql = "insert into MEMBER values(?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);

			setAllValues(pstmt, member);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	public void updateMember(LogonDataBean member) throws Exception{
		String sql = "update MEMBER set ";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);

			setAllValues(pstmt, member);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		String sql = "select passwd from member where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd ="";
		int x = -1;
		try {
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbpasswd = rs.getString("passwd");
				if(dbpasswd.equals(passwd))
					x=1;		
				else
					x=0;		
			}else
				x=-1;			
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
	public void setAllValues(PreparedStatement pstmt, LogonDataBean member) {
		try {
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setTimestamp(4, member.getReg_date());
			pstmt.setInt(5, member.getNum());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getAddress());
			pstmt.setInt(8, member.getSex());
			pstmt.setDate(9,member.getBirth());			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null)
				try {pstmt.close();}catch(SQLException sqle) {}
		}
	}
}
