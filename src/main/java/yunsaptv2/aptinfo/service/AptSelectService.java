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
import yunsaptv2.common.service.CommonService;

public class AptSelectService {
	private CommonService common;
	
	public AptSelectService() {
		common = new yunsaptv2.common.service.CommonService();
	}
// 아파트 세부정보 실거래가 가져오기
	public List<AptDetail> getAptSelectList(String city_do, String city_gu, 
			String dong_gu, String apt_name, int scale, String kind)
			throws NamingException, ClassNotFoundException {


		List<AptDetail> list = new ArrayList<AptDetail>();

		String sql = "";
		
		if(kind.equals("매매")) {
			if(scale == 0) {
				sql =     " SELECT * FROM TRADE_MST "
						+ "   WHERE CITY_DO = ? "
						+ "     AND CITY_GU = ? "
						+ "     AND DONG_GU = ? "
						+ "     AND APT_NAME = ? "
						+ " ORDER BY CONTRACT_DATE DESC";
			}else {
				sql =     " SELECT * FROM TRADE_MST "
			    		+ "   WHERE CITY_DO = ? "
			    		+ "     AND CITY_GU = ? "
			    		+ "     AND DONG_GU = ? "
			    		+ "     AND APT_NAME = ? "
			    		+ "     AND SCALE = ? "
			    		+ " ORDER BY CONTRACT_DATE DESC";
			}			
		}else {  //전세, 월세
			if(scale == 0) {
				sql =     " SELECT * FROM TRADE_MST02 "
						+ "   WHERE CITY_DO = ? "
						+ "     AND CITY_GU = ? "
						+ "     AND DONG_GU = ? "
						+ "     AND APT_NAME = ? "
						+ "     AND KIND = ? "
						+ " ORDER BY CONTRACT_DATE DESC";
			}else {
				sql =     " SELECT * FROM TRADE_MST02 "
			    		+ "   WHERE CITY_DO = ? "
			    		+ "     AND CITY_GU = ? "
			    		+ "     AND DONG_GU = ? "
			    		+ "     AND APT_NAME = ? "
			    		+ "     AND KIND = ? "
			    		+ "     AND SCALE = ? "
			    		+ " ORDER BY CONTRACT_DATE DESC";
			}						
		}
		

		 
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = common.getConnection(); 
			st = con.prepareStatement(sql);
			
			if(kind.equals("매매")) {
				if(scale == 0) {				
					st.setString(1, city_do);
					st.setString(2, city_gu);
					st.setString(3, dong_gu);
					st.setString(4, apt_name);
				}else {				
					st.setString(1, city_do);
					st.setString(2, city_gu);
					st.setString(3, dong_gu);
					st.setString(4, apt_name);
					st.setInt(5, scale);
				}
				
				rs = st.executeQuery();
				while (rs.next()) {
					
					Date contract_date = rs.getDate("contract_date");
					String contract_ym = rs.getString("contract_ym");
					int square = rs.getInt("square");
					int scale_n = rs.getInt("scale");
					int trade_price = rs.getInt("trade_price");
					int floor = rs.getInt("floor");
					int deposit = 0;
					int monthrent = 0;	
					
					AptDetail n = new AptDetail(contract_date, contract_ym, square, scale_n, 
							trade_price, floor, kind, deposit, monthrent);
					list.add(n);
				}
			}else {  //전세, 월세
				if(scale == 0) {				
					st.setString(1, city_do);
					st.setString(2, city_gu);
					st.setString(3, dong_gu);
					st.setString(4, apt_name);
					st.setString(5, kind);
				}else {				
					st.setString(1, city_do);
					st.setString(2, city_gu);
					st.setString(3, dong_gu);
					st.setString(4, apt_name);
					st.setString(5, kind);
					st.setInt(6, scale);
				}
				
				rs = st.executeQuery();
				while (rs.next()) {
					
					Date contract_date = rs.getDate("contract_date");
					String contract_ym = rs.getString("contract_ym");
					int square = rs.getInt("square");
					int scale_n = rs.getInt("scale");
					int deposit = rs.getInt("deposit");
					int monthrent = rs.getInt("monthrent");
					int floor = rs.getInt("floor");
					int trade_price = 0;
					
					AptDetail n = new AptDetail(contract_date, contract_ym, square, scale_n, 
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
	
}
