package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class adminDAO {
	Connection con ;
	PreparedStatement pstmt ;
	ResultSet rs ;
	
	private void freeResource() {
		try {
			if(rs != null) {rs.close();}
			if(pstmt != null) {pstmt.close();}
			if(con != null) {con.close();}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	//커넥션풀을 얻은후 connticeDB접속
	private Connection getConnection() throws Exception{
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/travel");
		//커넥션풀에 존재하는 커넥션 얻기
		Connection con = ds.getConnection();
		//커넥션 반환
		return con ;
	}
	
	public void insertadmin(adminVO admin) {
		Connection con = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		String sql ="" ;
		int num=0 ;
		
		try {
			con=getConnection();
			sql="select max(num) from board ";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				num=rs.getInt(1)+1;
			}else {
				num=1 ;
			}
			sql = "insert into board (id,num,title,content,date,pos,dept) values(?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, admin.getId());
			pstmt.setInt(2, num);
			pstmt.setString(3, admin.getTitle());
			pstmt.setString(4, admin.getContent());
			pstmt.setDate(5, admin.getDate());
			pstmt.setString(6, admin.getPos());
			pstmt.setString(7, admin.getDept());
			
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			freeResource();
		}
	}
	
	public int updateadmin(adminVO admin) {
		int rowCount = 0 ;
		Connection connection = null ;
		PreparedStatement statement = null ;
		String sql = "Update board SET title=?,content=?,where num=?, id=?";
		try {
			connection = this.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, admin.getTitle());
			statement.setString(2, admin.getContent());
			statement.setInt(3, admin.getNum());
			statement.setString(4, admin.getId());
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			freeResource();
		}
		return rowCount; 
	}
}
