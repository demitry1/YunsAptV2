package yunsaptv2.aptinfo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import yunsaptv2.aptinfo.entity.AptDetail;
import yunsaptv2.aptinfo.entity.AptInfoDetail;
import yunsaptv2.aptinfo.entity.AptScale;
import yunsaptv2.common.service.CommonService;

public class AptDetailService {
	private CommonService common;
	
	public AptDetailService() {
		common = new yunsaptv2.common.service.CommonService();
	}
// 아파트 세부정보 실거래가 가져오기
	public List<AptDetail> getAptDetailList(String city_do, String city_gu, 
			String dong_gu, String apt_name, String kind)
			throws NamingException, ClassNotFoundException {


		List<AptDetail> list = new ArrayList<AptDetail>();

		String sql = "";
		
		if(kind.equals("매매")) {			
			sql =     " SELECT * FROM TRADE_MST "
					+ "   WHERE CITY_DO = ? "
					+ "     AND CITY_GU = ? "
					+ "     AND DONG_GU = ? "
					+ "     AND APT_NAME = ? "
					+ " ORDER BY CONTRACT_DATE DESC";
		}else {  //전세, 월세
			sql =     " SELECT * FROM TRADE_MST02 "
					+ "   WHERE CITY_DO = ? "
					+ "     AND CITY_GU = ? "
					+ "     AND DONG_GU = ? "
					+ "     AND APT_NAME = ? "
					+ "     AND KIND = ? "
					+ " ORDER BY CONTRACT_DATE DESC";			
		}
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = common.getConnection(); 
			st = con.prepareStatement(sql);
			if(kind.equals("매매")) {		
				st.setString(1, city_do);
				st.setString(2, city_gu);
				st.setString(3, dong_gu);
				st.setString(4, apt_name);
						
				rs = st.executeQuery();
				while (rs.next()) {
					
					Date contract_date = rs.getDate("contract_date");
					String contract_ym = rs.getString("contract_ym");
					int square = rs.getInt("square");
					int scale = rs.getInt("scale");
					int trade_price = rs.getInt("trade_price");
					int floor = rs.getInt("floor");
					int deposit = 0;
					int monthrent = 0;	
					
					AptDetail n = new AptDetail(contract_date, contract_ym, square, scale, 
							trade_price, floor, kind, deposit, monthrent);
					list.add(n);
				} 
			}else {  //전세,월세
				st.setString(1, city_do);
				st.setString(2, city_gu);
				st.setString(3, dong_gu);
				st.setString(4, apt_name);
				st.setString(5, kind);
						
				rs = st.executeQuery();
				while (rs.next()) {
					
					Date contract_date = rs.getDate("contract_date");
					String contract_ym = rs.getString("contract_ym");
					int square = rs.getInt("square");
					int scale = rs.getInt("scale");
					int deposit = rs.getInt("deposit");
					int monthrent = rs.getInt("monthrent");
					int floor = rs.getInt("floor");
					int trade_price = 0;
					
					AptDetail n = new AptDetail(contract_date, contract_ym, square, scale, 
							trade_price, floor, kind, deposit, monthrent);
					list.add(n);
				} 				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close();} catch (Exception e2) {}
			if(st != null) try { st.close();} catch (Exception e2) {}
			if(con != null) try { con.close();} catch (Exception e2) {}
		}

		return list;
	}
	// 아파트 세부정보 펑형 정보가져오기
	public List<AptScale> getAptScale(String city_do, String city_gu, 
			String dong_gu, String apt_name, String kind)
			throws NamingException, ClassNotFoundException {
	
		List<AptScale> scales = new ArrayList<AptScale>();
	
		String sql = "";
		
		sql =     "  SELECT DISTINCT SCALE FROM TRADE_MST "
				+ "		WHERE CITY_DO = ? "
				+ "    	  AND CITY_GU = ? "
				+ "		  AND DONG_GU = ? "
				+ "		  AND APT_NAME = ? "
				+ "   UNION "
				+ "	  SELECT DISTINCT SCALE FROM TRADE_MST02  "
				+ "		WHERE CITY_DO = ? "
				+ "    	  AND CITY_GU = ?  "
				+ "		  AND DONG_GU = ? "
				+ "		  AND APT_NAME = ? "
				+ "      ORDER BY SCALE";

		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = common.getConnection(); 
			st = con.prepareStatement(sql);
			st.setString(1, city_do);
			st.setString(2, city_gu);
			st.setString(3, dong_gu);
			st.setString(4, apt_name);
			st.setString(5, city_do);
			st.setString(6, city_gu);
			st.setString(7, dong_gu);
			st.setString(8, apt_name);

					
			rs = st.executeQuery();
			while (rs.next()) {				
				int scale = rs.getInt("scale");
				AptScale n = new AptScale(scale);
				scales.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close();} catch (Exception e2) {}
			if(st != null) try { st.close();} catch (Exception e2) {}
			if(con != null) try { con.close();} catch (Exception e2) {}
		}
	
		return scales;
	}	
// 아파트 세부정보 기본정보 가져오기	
	public List<AptInfoDetail> getAptInfoDetail(String city_do, String city_gu, 
			String dong_gu, String apt_name, String construction_year, 
			String address_road, String address_jibun)
			throws NamingException, ClassNotFoundException {

		List<AptInfoDetail> info = new ArrayList<AptInfoDetail>();

		String sql = "";
		int dong_cnt = 0;
		int household_cnt = 0;
		String heating = "";
		String corridor = "";
		String constructor = "";
		int park_tot = 0;
		String office_tel = "";
		
	    sql =     " select * from apt_detail  "
	    		+ "    where city_do = ? "
	    		+ "      and city_gu = ? "
	    		+ "      and dong_gu = ? "
	    		+ "      and ( address_road = ? or "
	    		+ "            address_jibun = ? ) " ;
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = common.getConnection(); 
			st = con.prepareStatement(sql);
			st.setString(1, city_do);
			st.setString(2, city_gu);
			st.setString(3, dong_gu);
			st.setString(4, address_road);
			st.setString(5, address_jibun);
					
			rs = st.executeQuery();
			
			while (rs.next()) {
				dong_cnt = rs.getInt("dong_cnt");
				household_cnt = rs.getInt("household_cnt");
				heating = rs.getString("heating");
				corridor = rs.getString("corridor");
				constructor = rs.getString("constructor");
				park_tot = rs.getInt("park_tot");
				office_tel = rs.getString("office_tel");
			}
			AptInfoDetail n = new AptInfoDetail(city_do, city_gu, dong_gu, apt_name, construction_year, address_road, address_jibun,
					dong_cnt, household_cnt, heating, corridor, constructor, park_tot,
					office_tel);
			info.add(n);								
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close();} catch (Exception e2) {}
			if(st != null) try { st.close();} catch (Exception e2) {}
			if(con != null) try { con.close();} catch (Exception e2) {}
		}

		return info;
	}
	
// 아파트 도로명, 지번주소 가져오기	
	public String[] getAddress(String city_do, String city_gu, String dong_gu, String apt_name)
			throws NamingException, ClassNotFoundException {

		String[] addr = new String[2];

		String sql = "";
	
	    sql =     " select * from apt_mst  "
	    		+ "    where city_do = ? "
	    		+ "      and city_gu = ? "
	    		+ "      and dong_gu = ? "
	    		+ "      and apt_name = ? " 
	    		+ "      and rownum = 1 " 
	    		+ "    order by seqnr desc "; 
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = common.getConnection(); 
			st = con.prepareStatement(sql);
			st.setString(1, city_do);
			st.setString(2, city_gu);
			st.setString(3, dong_gu);
			st.setString(4, apt_name);
					
			rs = st.executeQuery();
			
			while (rs.next()) {
				addr[0] = rs.getString("address_road");
				addr[1] = rs.getString("address_jibun");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close();} catch (Exception e2) {}
			if(st != null) try { st.close();} catch (Exception e2) {}
			if(con != null) try { con.close();} catch (Exception e2) {}
		}
		return addr;
	}	
	
}
