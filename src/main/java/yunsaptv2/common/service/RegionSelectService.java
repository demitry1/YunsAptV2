package yunsaptv2.common.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import yunsaptv2.common.entity.SelectRegion;

public class RegionSelectService {
	private CommonService common;
	
	public RegionSelectService() {
		common = new yunsaptv2.common.service.CommonService();
	}

	public List<SelectRegion> getRegionList(String city_do, String city_gu, String dong_gu)
	
			throws NamingException, ClassNotFoundException {

		List<SelectRegion> list = new ArrayList<SelectRegion>();

		// 구 파라미테에 값이 있으면 동 정보를 가져오기 아니면 구 정보를 가져온다
		String sql = "";
		if(city_do == null || city_do.equals("") ) {    //특별시,도 가져오기
		    sql = "SELECT DISTINCT CITY_DO "
					+ " FROM REGION_MST "
					+ " WHERE CITY_DO = ?";		
		}else if(city_gu == null || city_gu.equals("") ) {    // 구를 가져오기
		    sql = "select distinct city_gu "
					+ "from region_mst "
					+ "where city_do = ? "
					+ "order by city_gu" ;			
		}else if(dong_gu == null || dong_gu.equals("") ) {   // 동을 가져오기
			sql = "select distinct dong_gu "
					+ "from region_mst "
					+ "where city_do = ? "
					+ "  and city_gu = ? "
					+ "order by dong_gu" ;			
		}else {                           //  아파트명을 가져오기
			sql = "select distinct apt_name from apt_mst "
					+ "   where city_do = ? "
					+ "     and city_gu = ? "
					+ "     and dong_gu = ? "
					+ "   order by apt_name" ;			
		}
		
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = common.getConnection(); 
			st = con.prepareStatement(sql);
			if(city_do == null || city_do.equals("")) {
				st.setString(1, "서울특별시");
			}
			if(city_do != null && !city_do.equals("")) {
				st.setString(1, city_do);
			}
			if(city_gu != null && !city_gu.equals("")) {
				st.setString(2, city_gu);				
			}
			if(dong_gu != null && !dong_gu.equals("")) {
				st.setString(3, dong_gu);				
			}

			rs = st.executeQuery();
			
			SelectRegion n = new SelectRegion();
			while (rs.next()) {
				if(city_do == null || city_do.equals("")) {
					String city_do_ = rs.getString("CITY_DO");
					n = new SelectRegion(city_do_);
				}else if(city_gu == null || city_gu.equals("")) {
					String city_gu_ = rs.getString("CITY_GU");
					n = new SelectRegion(city_gu_);
				}else if(dong_gu == null || dong_gu.equals("")) {
					String dong_gu_ = rs.getString("DONG_GU");
					n = new SelectRegion(dong_gu_);					
				}else {
					String apt_name_ = rs.getString("APT_NAME");
					n = new SelectRegion(apt_name_);					
				}
				
				list.add(n);
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
		
}
