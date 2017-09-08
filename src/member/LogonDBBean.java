package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogonDBBean {
	// �̱��� ���� <== ������� ��������� �� ���� ��ü�� ���� ���� �ϴ°��� �����ϱ� ���� ���
	private static LogonDBBean instance = new LogonDBBean();

	public static LogonDBBean getInstance() {
		return instance;
	}

	private LogonDBBean() {
	}

	// �����ϴ°ǵ� �̰� ���� �𸣰ڴ�. ServletContextEvent
	private Connection getConnection() throws Exception {
		// �ݵ�� ���� �ʿ�
		Connection conn = null;
		return conn;
	}

	public void insertMember(LogonDataBean member) throws Exception {
		// sql ���� ���� ���̺� Į���� �� �� �ʿ����� ���� �ۼ� ����
		String sql = "insert into ���̺��� values(?,?,?,?.....)";
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			// �ɹ� ���� ����
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// ���� �ݾ���
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
		//sql ��
		String sql = "select passwd from member where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd ="";
		//x�� �������� ����
		int x = -1;
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dbpasswd = rs.getString("passwd");
				if(dbpasswd.equals(passwd))
					x=1;		//���� ����
				else
					x=0;		//���� ����
			}else
				x=-1;			//�ش� ���̵� ����
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