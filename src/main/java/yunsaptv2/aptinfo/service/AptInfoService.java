package yunsaptv2.aptinfo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import yunsaptv2.aptinfo.entity.AptInfo;
import yunsaptv2.aptinfo.entity.AptInfoDetail;
import yunsaptv2.common.service.CommonService;

public class AptInfoService {
	private CommonService common;
	
	public AptInfoService() {
		common = new yunsaptv2.common.service.CommonService();
	}

	public List<AptInfo> getAptInfoList(String city_do, String city_gu, 
			String dong_gu, int construction_year_beg, int construction_year_end, 
			String apt_name, int page, String cmd, String infoall, int listcnt)
			throws NamingException, ClassNotFoundException {

		List<AptInfo> list = new ArrayList<AptInfo>();

		String sql = "";
		
		if(cmd.equals("normal")) 
		    sql = "   select * from ( "
		    		+ " select rownum num, t.* "
		    		+ " from ( select * from apt_mst "
		    		+ "         where city_do like ? "
		    		+ "           and city_gu like ? "
		    		+ "           and dong_gu like ? "
		    		+ "           and apt_name like ? "
		    		+ "           and construction_year between ? and ? "
		    		+ "           and seqnr in ( select max(seqnr) from apt_mst "
		    		+ "                              where city_do like ? "
		    		+ "                                and city_gu like ? "
		    		+ "                                and dong_gu like ? "
		    		+ "                                and apt_name like ? "
		    		+ "                                and construction_year between ? and ? "		    		
		    		+ "                              group by apt_name, address_jibun ) "
		    		+ "              order by city_do, city_gu, dong_gu, apt_name, seqnr ) t ) "
		    		+ " where num between ? and ? ";
		else if(cmd.equals("all"))			
		    sql = "   select * from ( "
		    		+ " select rownum num, t.* "
		    		+ " from ( select * from apt_mst "
		    		+ "         where ( city_do like ? "
		    		+ "           or city_gu like ? "
		    		+ "           or dong_gu like ? "
		    		+ "           or apt_name like ? ) "
		    		+ "           and construction_year between ? and ? "
		    		+ "           and seqnr in ( select max(seqnr) from apt_mst "
		    		+ "                              where ( city_do like ? "
		    		+ "                                or city_gu like ? "
		    		+ "                                or dong_gu like ? "
		    		+ "                                or apt_name like ? ) "
		    		+ "                                and construction_year between ? and ? "		    		
		    		+ "                              group by apt_name, address_jibun ) "
		    		+ "              order by city_do, city_gu, dong_gu, apt_name, seqnr ) t ) "
		    		+ " where num between ? and ? ";
		
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
				st.setInt(5, construction_year_beg);
				st.setInt(6, construction_year_end);
				st.setString(7, city_do);
				st.setString(8, city_gu);
				st.setString(9, dong_gu);
				st.setString(10, apt_name);
				st.setInt(11, construction_year_beg);
				st.setInt(12, construction_year_end);
				st.setInt(13, 1 + (page - 1) * listcnt);
				st.setInt(14, page * listcnt);
			}else if(cmd.equals("all")) {
				st.setString(1, '%'+infoall+'%');
				st.setString(2, '%'+infoall+'%');
				st.setString(3, '%'+infoall+'%');
				st.setString(4, '%'+infoall+'%');
				st.setInt(5, construction_year_beg);
				st.setInt(6, construction_year_end);
				st.setString(7, '%'+infoall+'%');
				st.setString(8, '%'+infoall+'%');
				st.setString(9, '%'+infoall+'%');
				st.setString(10, '%'+infoall+'%');
				st.setInt(11, construction_year_beg);
				st.setInt(12, construction_year_end);
				st.setInt(13, 1 + (page - 1) * listcnt);
				st.setInt(14, page * listcnt);			
			}
					
			rs = st.executeQuery();
			while (rs.next()) {
				String city_do_ = rs.getString("CITY_DO");
				String city_gu_ = rs.getString("CITY_GU");
				String dong_gu_ = rs.getString("DONG_GU");
				String dong_ = rs.getString("DONG");
				String apt_name_ = rs.getString("APT_NAME");
				int construction_year_ = rs.getInt("CONSTRUCTION_YEAR");
				String address_road_ = rs.getString("ADDRESS_ROAD");
				String address_jibun_ = rs.getString("ADDRESS_JIBUN");

				AptInfo n = new AptInfo(city_do_, city_gu_, dong_gu_, dong_, 
						apt_name_, construction_year_, address_road_, address_jibun_);
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

	public int[] getAptInfoCount(String city_do, String city_gu, 
			String dong_gu, int construction_year_beg, int construction_year_end, 
			String apt_name, String cmd, String infoall)
			throws NamingException, ClassNotFoundException {
		int[] count = new int[2];
   
		String sql = "";
		if(cmd.equals("normal")) 
		    sql = "   select count(city_gu) as count from ( "
		    		+ " select rownum num, t.* "
		    		+ " from ( select * from apt_mst "
		    		+ "         where city_do like ? "
		    		+ "           and city_gu like ? "
		    		+ "           and dong_gu like ? "
		    		+ "           and apt_name like ? "
		    		+ "           and construction_year between ? and ? "
		    		+ "           and seqnr in ( select max(seqnr) from apt_mst "
		    		+ "                              where city_do like ? "
		    		+ "                                and city_gu like ? "
		    		+ "                                and dong_gu like ? "
		    		+ "                                and apt_name like ? "
		    		+ "                                and construction_year between ? and ? "		    		
		    		+ "                              group by apt_name, address_jibun ) "
		    		+ "              order by city_do, city_gu, dong_gu, apt_name, seqnr ) t ) ";
			   else if(cmd.equals("all"))
				    sql = "   select count(city_gu) as count from ( "
				    		+ " select rownum num, t.* "
				    		+ " from ( select * from apt_mst "
				    		+ "         where ( city_do like ? "
				    		+ "           or city_gu like ? "
				    		+ "           or dong_gu like ? "
				    		+ "           or apt_name like ? ) "
				    		+ "           and construction_year between ? and ? "
				    		+ "           and seqnr in ( select max(seqnr) from apt_mst "
				    		+ "                              where ( city_do like ? "
				    		+ "                                or city_gu like ? "
				    		+ "                                or dong_gu like ? "
				    		+ "                                or apt_name like ? ) "
				    		+ "                                and construction_year between ? and ? "		    		
				    		+ "                              group by apt_name, address_jibun ) "
				    		+ "              order by city_do, city_gu, dong_gu, apt_name, seqnr ) t ) ";	   
		
			String sql2 = "	select count(city_gu) as total_count from ( "
					+ "		     select rownum num, t.* "
					+ "		     from ( select * from apt_mst "
					+ "		               where seqnr in ( select max(seqnr) from apt_mst 	"
					+ "		                                  group by apt_name, address_jibun ) "
					+ "		                  order by city_do, city_gu, dong_gu, apt_name, seqnr ) t ) ";
		
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
					st.setInt(5, construction_year_beg);
					st.setInt(6, construction_year_end);
					st.setString(7, city_do);
					st.setString(8, city_gu);
					st.setString(9, dong_gu);
					st.setString(10, apt_name);
					st.setInt(11, construction_year_beg);
					st.setInt(12, construction_year_end);
				}else if(cmd.equals("all")) {
					st.setString(1, '%'+infoall+'%');
					st.setString(2, '%'+infoall+'%');
					st.setString(3, '%'+infoall+'%');
					st.setString(4, '%'+infoall+'%');
					st.setInt(5, construction_year_beg);
					st.setInt(6, construction_year_end);
					st.setString(7, '%'+infoall+'%');
					st.setString(8, '%'+infoall+'%');
					st.setString(9, '%'+infoall+'%');
					st.setString(10, '%'+infoall+'%');
					st.setInt(11, construction_year_beg);
					st.setInt(12, construction_year_end);			
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
	public List<AptInfoDetail> getAptInfo(String address_road, String address_jibun)
			throws NamingException, ClassNotFoundException {

		List<AptInfoDetail> list = new ArrayList<AptInfoDetail>();

		String sql = "";
				
	    sql = "   select a.city_do, a.city_gu, a.dong_gu, a.apt_name, a.construction_year, a.address_road, a.address_jibun,  "
	    		+ "        b.dong_cnt, b.household_cnt, b.heating, b.corridor,  "
	    		+ "        b.constructor, b.park_tot, b.office_tel  "
	    		+ "    from (  "
	    		+ "     select * from  "
	    		+ "        ( select * "
	    		+ "           from apt_mst "
	    		+ "             where ( address_road = ? "
	    		+ "                     or address_jibun = ? ) "
	    		+ "                 and apt_name not like '%юс╢К%' "
	    		+ "                order by seqnr desc ) "
	    		+ "     where rownum = 1 ) a  "
	    		+ "     LEFT OUTER JOIN apt_detail b "
	    		+ "     on    a.city_do = b.city_do "
	    		+ "       and a.city_gu = b.city_gu "
	    		+ "       and a.dong_gu = b.dong_gu "
	    		+ " where a.address_road = b.address_road "
	    		+ "    or a.address_jibun = b.address_jibun ";
	
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = common.getConnection(); 
			st = con.prepareStatement(sql);
			
			st.setString(1, address_road);
			st.setString(2, address_jibun);
					
			rs = st.executeQuery();
			while (rs.next()) {
				String city_do = rs.getString("city_do");
				String city_gu = rs.getString("city_gu");
				String dong_gu = rs.getString("dong_gu");
				String apt_name = rs.getString("apt_name");
				String construction_year = rs.getString("CONSTRUCTION_YEAR");
				String address_road_ = rs.getString("ADDRESS_ROAD");
				String address_jibun_ = rs.getString("ADDRESS_JIBUN");
				int dong_cnt = rs.getInt("dong_cnt");
				int household_cnt = rs.getInt("household_cnt");
				String heating = rs.getString("heating");
				String corridor = rs.getString("corridor");
				String constructor = rs.getString("constructor");
				int park_tot = rs.getInt("park_tot");
				String office_tel = rs.getString("office_tel");

				AptInfoDetail n = new AptInfoDetail(city_do, city_gu, dong_gu, apt_name, construction_year, address_road_, address_jibun_,
						dong_cnt, household_cnt, heating, corridor, constructor, park_tot, office_tel);
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
