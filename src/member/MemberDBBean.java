package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.ConnectionProvider;

public class MemberDBBean {
	private static MemberDBBean instance = new MemberDBBean();

	public static MemberDBBean getInstance() {
		return instance;
	}

	private MemberDBBean() {	}
	//회원 가입
	public void insertMember(MemberDataBean member) throws Exception {
		String sql = "insert into MEMBER values(?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setTimestamp(4, member.getReg_date());
			pstmt.setInt(5, member.getNum());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getAddress());
			pstmt.setInt(8, member.getSex());
			pstmt.setDate(9,member.getBirth());	
			
			pstmt.executeQuery();
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
	//정보 수정하기 위해서 정보 게시판 올려놓기 위해 필요한 데이터 
	public MemberDataBean getMemberInfo(String id) throws Exception{
		String sql = "select * from MEMBER where id=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDataBean member = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new MemberDataBean();
				//아이디 , 비밀번호, 이름, 
				member.setId(rs.getString("id"));
				member.setPasswd(rs.getString("passwd"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setAddress(rs.getString("address"));
				member.setSex(rs.getInt("sex"));
				member.setBirth(rs.getDate("birth"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null)
				try { conn.close();}catch(SQLException sqle) {}
			if(pstmt !=null)
				try { pstmt.close();}catch(SQLException sqle) {}
			if(rs != null)
				try { rs.close();} catch(SQLException sqle) {}
		}
		return member; 
	}
	//회원정보 수정
	public void updateMember(MemberDataBean member) throws Exception{
		//sql 작성	//맞는지 확인
		String sql = "update MEMBER set passwd=?, name=?, email=?, address=?, sex=?, birth=? where id=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPasswd());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getAddress());
			pstmt.setInt(5, member.getSex());
			pstmt.setDate(6, member.getBirth());
			pstmt.setString(7,member.getId());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try { pstmt.close();} catch (SQLException sqle) {}
			if (conn != null)
				try { conn.close();	} catch (SQLException sqle) {}
		}
	}
	//회원 탈퇴
	@SuppressWarnings("resource")
	public int deleteMember(String id, String passwd) throws Exception{
		//비밀번호 찾기 쿼리
		String sql1 = "select passwd from member where id=?";
		//지우는 쿼리
		String sql2 ="delete from MEMBER where id=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//db에서 찾은 패스워드
		String dbpasswd = "";
		
		int x = -1;
		try{
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql1);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dbpasswd = rs.getString("passwd");
				if(dbpasswd.equals(passwd)) {
					pstmt = conn.prepareStatement(sql2);
					
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					x = 1;		//탈퇴 성공
				}else 
					x = 0;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(conn != null)
				try { conn.close(); }catch(SQLException sqle) {}
			if(pstmt != null)
				try { pstmt.close();}catch(SQLException sqle) {}
			if(rs !=null)
				try { rs.close(); } catch(SQLException sqle) {}
		}
		return x;
	}
	//중복 확인
	public int confirmId(String id) throws Exception{
		String sql = "select id from MEMBER where id=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x=1;
		try {
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				x=1;	//아이디 있다
			else
				x=0;	//아이디 없다
		}catch(Exception e) {e.printStackTrace();
		}finally {
			if(conn != null)
				try {conn.close();}catch(SQLException sqle) {}
			if(pstmt != null)
				try {pstmt.close();}catch(SQLException sqle) {}
			if(rs !=null)
				try {rs.close();}catch(SQLException sqle) {}
		}
		return x;
	}
	
	//가입 여부
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
	//쿼리문 전체 값 수정
	public void setAllValues(PreparedStatement pstmt, MemberDataBean member) {
		try {
					
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null)
				try {pstmt.close();}catch(SQLException sqle) {}
		}
	}
}
