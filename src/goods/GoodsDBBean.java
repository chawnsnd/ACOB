package goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.ConnectionProvider;

public class GoodsDBBean {
	private static GoodsDBBean instance = new GoodsDBBean();

	public static GoodsDBBean getInstance() {
		return instance;
	}

	private GoodsDBBean() {	}
	
	
	
	//상품 기입
	public void insertGoods(GoodsDataBean goods) throws Exception {
		String sql = "insert into goods values(?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, goods.getNo());
			pstmt.setString(2, goods.getName());
			pstmt.setInt(3, goods.getPrice());
			pstmt.setString(4, goods.getCompany());
			pstmt.setInt(5, goods.getStock());
			pstmt.setString(6, goods.getCategory());

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
	
	
	
	//상품검색
	public GoodsDataBean getGoodsInfo(String name) throws Exception{
		String sql = "select * from MEMBER where id like %?%";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GoodsDataBean goods = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				goods = new GoodsDataBean();
				//아이디 , 비밀번호, 이름, 
				goods.setNo(rs.getInt("no"));
				goods.setName(rs.getString("name"));
				goods.setPrice(rs.getInt("price"));
				goods.setCompany(rs.getString("company"));
				goods.setStock(rs.getInt("stock"));
				goods.setCategory(rs.getString("category"));
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
		return goods; 
	}
	
	//상품정보 수정
	public void updateGoods(GoodsDataBean goods) throws Exception{

		String sql = "update MEMBER set no=?, name=?, price=?, company=?, stock=?, category=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, goods.getNo());
			pstmt.setString(2, goods.getName());
			pstmt.setInt(3, goods.getPrice());
			pstmt.setString(4, goods.getCompany());
			pstmt.setInt(5, goods.getStock());
			pstmt.setString(6, goods.getCategory());
			
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
	//상품 삭제
	@SuppressWarnings("resource")
	public void deleteMember(GoodsDataBean goods) throws Exception{

		String sql ="delete from goods where no=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, goods.getNo());
			
			rs = pstmt.executeQuery();

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
		return;
	}

	//쿼리문 전체 값 수정
	public void setAllValues(PreparedStatement pstmt, GoodsDataBean goods) {
		try {
					
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null)
				try {pstmt.close();}catch(SQLException sqle) {}
		}
	}
}
