package member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.tomcat.jni.Mmap;


public class MemberDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//커넥션풀(DataSource)을 얻은 후 ConnecionDB접속
		private Connection getConnection() throws Exception{
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/travel");
			//커넥션풀에 존재하는 커넥션 얻기
			Connection con = ds.getConnection();
			//커넥션 반환
			return con;
		}
		
		private void freeResource() {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(con != null) {con.close();}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

	
	public void addMember(MemberVO m) {
		try {
			con = getConnection();
			String id = m.getId();
			String passwd = m.getPasswd();
			String name = m.getName();
			String birth = m.getBirth();
			System.out.println(birth);
			String email = m.getEmail();
			String phone = m.getPhone();
			String address = m.getAddress();
			String query = "INSERT INTO member(id,passwd,name,birth,email,phone,address) VALUES(?,?,?,?,?,?,?)";
			
			System.out.println(query);
			//PreparedStatement객체를 생성하면서 SQL문을 인자로 전달합니다.
			pstmt = con.prepareStatement(query);
			//?값 셋팅
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			pstmt.setString(3, name);
			pstmt.setString(4, birth);
			pstmt.setString(5, email);
			pstmt.setString(6, phone);
			pstmt.setString(7, address);
			
			//SQL문을 실행합니다.
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			freeResource();
		}
	}
	
	
	public int loginCheck(String id,String passwd) {
		int chk = 0;
		try {
			con = getConnection();
			String query = "select * from member where id = ?";
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				if(rs.getString("passwd").equals(passwd)) {
					System.out.println(rs.getString("passwd"));
					System.out.println(passwd);
					chk = 1;
				}
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			freeResource();
		}
		
		return chk;
	}
	
	
	public MemberVO selectAll(String id) {
		MemberVO vo = new MemberVO();
		try {
			con = getConnection();
			String query = "select * from member where id = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				vo.setId(rs.getString("id"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setName(rs.getString("name"));
				vo.setAddress(rs.getString("Address"));
				vo.setBirth(rs.getString("birth"));
				vo.setPhone(rs.getString("phone"));
				vo.setEmail(rs.getString("email"));
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			freeResource();
		}
		return vo;
	}
	
	public List listMembers() {
		List membersList = new ArrayList();
		try {
			con = getConnection();
			String query = "select * from member order by joinDate desc";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String passwd = rs.getString("passwd");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String birth = rs.getString("birth");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				
				MemberVO memberVO = new MemberVO(id,passwd,name,address,birth,phone,email);
				membersList.add(memberVO);
			}
			freeResource();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return membersList ;
	}
	
	
	public void updateAll(MemberVO vo) {
		try {
			con = getConnection();
			String query = "update member set passwd=?,name=?,birth=?,email=?,phone=?,address=?";
			pstmt = con.prepareStatement(query);
			 
			pstmt.setString(1,vo.getPasswd());
			pstmt.setString(2,vo.getName());
			pstmt.setString(3,vo.getBirth());
			pstmt.setString(4,vo.getEmail());
			pstmt.setString(5,vo.getPhone());
			pstmt.setString(6,vo.getAddress());
			
			pstmt.executeUpdate();	//update 실행 

				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			freeResource();
		}
	}
	
	
	public void deleteMem(String id) {
		try {
			con = getConnection();
			String query = "delete from member where id = ?";
			pstmt = con.prepareStatement(query);
			 
			pstmt.setString(1,id);
			
			pstmt.executeUpdate();	//delete 실행 

				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			freeResource();
		
		}
	}
	public MemberVO findMember(String id) {
		MemberVO memInfo = null ;
		try {
			con = getConnection();
			String query = "select * from member where id =?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			System.out.println(query);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String id1 = rs.getString("id");
			String passwd = rs.getString("passwd");
			String name = rs.getString("name");
			String address = rs.getString("address");
			String birth = rs.getString("birth");
			String phone = rs.getString("phone");
			String email = rs.getString("email");
			memInfo = new MemberVO(id,passwd,name,address,birth,phone,email);
			freeResource();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return memInfo;
	}
	
	public void modMember(MemberVO memberVO) {
		String id = memberVO.getId();
		String passwd = memberVO.getPasswd();
		String name = memberVO.getName();
		String address = memberVO.getAddress();
		String birth = memberVO.getBirth();
		String phone = memberVO.getPhone();
		String email = memberVO.getEmail();
		try {
			con = getConnection();
			String query = "update member set pwd=?,name=?,address=?birth=?,phone=?,email=? where id=?";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, passwd);
			pstmt.setString(2, name);
			pstmt.setString(3, address);
			pstmt.setString(4, birth);
			pstmt.setString(5, phone);
			pstmt.setString(6, email);
			pstmt.setString(7, id);
			freeResource();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delMember(String id) {
		try { 
			con =getConnection();
			
			String query = "delete from member where id = ?" ;
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}//delMember
	
}