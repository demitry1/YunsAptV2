package yunsaptv2.login.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import yunsaptv2.common.service.CommonService;
import yunsaptv2.login.entity.Member;

public class LoginService {
	private CommonService common;
	
	public LoginService() {
		common = new CommonService();
	}
// 회원가입 저장	
	public int saveJoin(Member member)
			throws NamingException, ClassNotFoundException {

		String sql = "";
	    sql = "INSERT INTO MEMBER (ID, PASSWORD, NAME, REGDATE, EMAIL) "
	    		+ "VALUES( ?, ?, ?, to_char(sysdate,'yyyy.mm.dd'), ? )";

	    int i = 0;
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = common.getConnection(); 
			st = con.prepareStatement(sql);
			st.setString(1, member.getId());
			st.setString(2, member.getPassword());
			st.setString(3, member.getName());
			st.setString(4, member.getEmail());	
					
			i = st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(st != null) try { st.close();} catch (Exception e2) {}
			if(con != null) try { con.close();} catch (Exception e2) {}
		}

		return i;
	}
	
// 로그인 처리	
	public List<Member> checkLogin(String userid, String password) throws NamingException {
		
		String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PASSWORD = ? ";
		
		List<Member> list = new ArrayList<Member>();
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = common.getConnection(); 
			st = con.prepareStatement(sql);
			st.setString(1, userid);
			st.setString(2, password);					
			rs = st.executeQuery();
			
			while(rs.next()) {
				String sid = rs.getString("id");
				String spassword = rs.getString("password");
				String sname = rs.getString("name");
				Date sregdate = rs.getDate("regdate");
				String semail = rs.getString("email");
				
				Member m = new Member(sid, spassword, sname, sregdate, semail);
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(st != null) try { st.close();} catch (Exception e2) {}
			if(con != null) try { con.close();} catch (Exception e2) {}
		}
		
		
		return list;
	}

// 회원정보 수정
	public int saveMember(Member member)
			throws NamingException, ClassNotFoundException {

		String sql = "";
	    sql = " UPDATE MEMBER SET PASSWORD = ?, NAME = ?, EMAIL = ?"
	    	+ "  WHERE ID = ? ";

	    int i = 0;
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = common.getConnection(); 
			st = con.prepareStatement(sql);
			st.setString(1, member.getPassword());
			st.setString(2, member.getName());
			st.setString(3, member.getEmail());	
			st.setString(4, member.getId());
					
			i = st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(st != null) try { st.close();} catch (Exception e2) {}
			if(con != null) try { con.close();} catch (Exception e2) {}
		}

		return i;
	}
	
	
}
