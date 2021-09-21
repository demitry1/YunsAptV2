package yunsaptv2.realprice.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import yunsaptv2.common.service.CommonService;
import yunsaptv2.realprice.entity.RealPriceRent;

public class RealPriceServiceRent {
	private CommonService common;
	
	public RealPriceServiceRent() {
		common = new yunsaptv2.common.service.CommonService();
	}

	public List<RealPriceRent> getRealPriceListRent(String city_do, String city_gu, 
			String dong_gu, int scale_beg, int scale_end, String contract_date1, 
			String contract_date2, int construction_year_beg, int construction_year_end, 
			String apt_name, int page, String cmd, String realall, int listcnt, String kind)
			throws NamingException, ClassNotFoundException {

		List<RealPriceRent> list = new ArrayList<RealPriceRent>();

		String sql = "";
		
		if(cmd.equals("normal")) 
		    sql = "   SELECT * FROM ( "
  				+ "		SELECT ROWNUM NUM, T.* "
				+ "		   FROM (  SELECT * FROM TRADE_MST02 "
				+ "                    WHERE CITY_DO LIKE ? "
				+ "                      AND CITY_GU LIKE ? "
				+ "                      AND DONG_GU LIKE ? "
				+ "                      AND APT_NAME LIKE ? "
				+ "                      AND CONTRACT_DATE BETWEEN ? AND ? "
				+ "                      AND KIND LIKE ? "
				+ "                      AND SCALE BETWEEN ? AND ? "
				+ "                      AND CONSTRUCTION_YEAR BETWEEN ? AND ? "
				+ "                    ORDER BY CONTRACT_DATE DESC ) T ) "
				+ "		   WHERE NUM BETWEEN ? AND ?";
		else if(cmd.equals("all"))
			sql = "   SELECT * FROM ( "
			    + "		SELECT ROWNUM NUM, T.* "
				+ "		   FROM (  SELECT * FROM TRADE_MST02 "
				+ "		             WHERE ( CITY_DO LIKE ? "
				+ "			            OR CITY_GU LIKE ? "
				+ "                     OR DONG_GU LIKE ? "
				+ "				        OR APT_NAME LIKE ? ) "
				+ "                    AND CONTRACT_DATE BETWEEN ? AND ? "
				+ "                    AND KIND LIKE ? "
				+ "                    AND SCALE BETWEEN ? AND ? "
				+ "                    AND CONSTRUCTION_YEAR BETWEEN ? AND ? "
				+ "				      ORDER BY CONTRACT_DATE DESC ) T ) "
				+ "		WHERE NUM BETWEEN ? AND ?";
		
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = common.getConnection(); 
			st = con.prepareStatement(sql);
			
			if(cmd.equals("normal")) {
				st.setString(1, city_do);
				st.setString(2, city_gu);
				st.setString(3, dong_gu);
				st.setString(4, apt_name);
				st.setString(5, contract_date1);
				st.setString(6, contract_date2);
				st.setString(7, kind);
				st.setInt(8, scale_beg);
				st.setInt(9, scale_end);
				st.setInt(10, construction_year_beg);
				st.setInt(11, construction_year_end);
				st.setInt(12, 1 + (page - 1) * listcnt);
				st.setInt(13, page * listcnt);
			}else if(cmd.equals("all")) {
				st.setString(1, '%'+realall+'%');
				st.setString(2, '%'+realall+'%');
				st.setString(3, '%'+realall+'%');
				st.setString(4, '%'+realall+'%');
				st.setString(5, contract_date1);
				st.setString(6, contract_date2);
				st.setString(7, kind);
				st.setInt(8, scale_beg);
				st.setInt(9, scale_end);
				st.setInt(10, construction_year_beg);
				st.setInt(11, construction_year_end);				
				st.setInt(12, 1 + (page - 1) * listcnt);
				st.setInt(13, page * listcnt);				
			}
					
			rs = st.executeQuery();
			while (rs.next()) {
				String city_do_ = rs.getString("CITY_DO");
				String city_gu_ = rs.getString("CITY_GU");
				String dong_gu_ = rs.getString("DONG_GU");
				String dong_ = rs.getString("DONG");
				String apt_name_ = rs.getString("APT_NAME");
				Date contract_date_ = rs.getDate("CONTRACT_DATE");
				String contract_ym_ = rs.getString("CONTRACT_YM");
				String kind_ = rs.getString("KIND");
				int square_ = rs.getInt("SQUARE");
				int scale_ = rs.getInt("SCALE");
				int deposit_ = rs.getInt("DEPOSIT");
				int monthrent_ = rs.getInt("MONTHRENT");
				int floor_ = rs.getInt("FLOOR");
				int construction_year_ = rs.getInt("CONSTRUCTION_YEAR");
				String address_road_ = rs.getString("ADDRESS_ROAD");
				String address_jibun_ = rs.getString("ADDRESS_JIBUN");

				RealPriceRent n = new RealPriceRent(city_do_, city_gu_, dong_gu_, dong_, 
						apt_name_, contract_date_, contract_ym_, kind_, square_, scale_, deposit_, 
						monthrent_, floor_, construction_year_, address_road_, address_jibun_);
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

	public int[] getRealPriceCountRent(String city_do, String city_gu, 
			String dong_gu, int scale_beg, int scale_end, String contract_date1, 
			String contract_date2, int construction_year_beg, int construction_year_end, 
			String apt_name, String cmd, String realall, String kind)
			throws NamingException, ClassNotFoundException {
		int[] count = new int[2];
   
 	    String sql = "";
 	    if(cmd.equals("normal")) 
		 sql =    "   SELECT COUNT(CITY_GU) AS COUNT FROM ( "
  				+ "		SELECT ROWNUM NUM, T.* "
				+ "		   FROM (  SELECT * FROM TRADE_MST02 "
				+ "                    WHERE CITY_DO LIKE ? "
				+ "                      AND CITY_GU LIKE ? "
				+ "                      AND DONG_GU LIKE ? "
				+ "                      AND APT_NAME LIKE ? "
				+ "                      AND CONTRACT_DATE BETWEEN ? AND ? "
				+ "                      AND KIND LIKE ? "
				+ "                      AND SCALE BETWEEN ? AND ? "
				+ "                      AND CONSTRUCTION_YEAR BETWEEN ? AND ? "
				+ "                    ORDER BY CONTRACT_DATE DESC ) T ) ";
 	   else if(cmd.equals("all"))
 		 sql =    "   SELECT COUNT(CITY_GU) AS COUNT FROM ( "
 	  			+ "		SELECT ROWNUM NUM, T.* "
 				+ "		   FROM (  SELECT * FROM TRADE_MST02 "
 				+ "                    WHERE ( CITY_DO LIKE ? "
 				+ "                       OR CITY_GU LIKE ? "
 				+ "                       OR DONG_GU LIKE ? "
 				+ "                       OR APT_NAME LIKE ? ) "
 				+ "                      AND CONTRACT_DATE BETWEEN ? AND ? "
				+ "                      AND KIND LIKE ? "
				+ "                      AND SCALE BETWEEN ? AND ? "
				+ "                      AND CONSTRUCTION_YEAR BETWEEN ? AND ? "
 				+ "                    ORDER BY CONTRACT_DATE DESC ) T ) "; 		   
 	    
		String sql2 = "SELECT COUNT(CITY_GU) TOTAL_COUNT FROM TRADE_MST02";

		Connection con = null;
		PreparedStatement st = null;
		PreparedStatement st2 = null;
		ResultSet rs = null;
		try {
			con = common.getConnection(); 
			st = con.prepareStatement(sql);
			if(cmd.equals("normal")) {
				st.setString(1, city_do);
				st.setString(2, city_gu);
				st.setString(3, dong_gu);
				st.setString(4, apt_name);
				st.setString(5, contract_date1);
				st.setString(6, contract_date2);
				st.setString(7, kind);
				st.setInt(8, scale_beg);
				st.setInt(9, scale_end);
				st.setInt(10, construction_year_beg);
				st.setInt(11, construction_year_end);
			}else if(cmd.equals("all")) {
				st.setString(1, '%'+realall+'%');
				st.setString(2, '%'+realall+'%');
				st.setString(3, '%'+realall+'%');
				st.setString(4, '%'+realall+'%');
				st.setString(5, contract_date1);
				st.setString(6, contract_date2);	
				st.setString(7, kind);
				st.setInt(8, scale_beg);
				st.setInt(9, scale_end);
				st.setInt(10, construction_year_beg);
				st.setInt(11, construction_year_end);
			}
			
			rs = st.executeQuery();

			rs.next();
			count[0] = rs.getInt("count");
			rs.close();

			st2 = con.prepareStatement(sql2);
			rs = st2.executeQuery();
			rs.next();
			count[1] = rs.getInt("total_count");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close();} catch (Exception e2) {}
			if(st != null) try { st.close();} catch (Exception e2) {}
			if(st2 != null) try { st2.close();} catch (Exception e2) {}
			if(con != null) try { con.close();} catch (Exception e2) {}
		}

		return count;
	}
	
	
}
