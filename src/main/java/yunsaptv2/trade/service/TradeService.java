package yunsaptv2.trade.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import yunsaptv2.common.service.CommonService;
import yunsaptv2.trade.entity.Trade;

public class TradeService {
	private CommonService common;

	public TradeService() {
		common = new yunsaptv2.common.service.CommonService();
	}

	public List<Trade> getTradeList(String city_do, String city_gu, String dong_gu, String datetype,
			String contract_date1, String contract_date2, String trade_gbn, String apt_name, String cmd,
			String tradeall) throws NamingException, ClassNotFoundException {

		List<Trade> list = new ArrayList<Trade>();
		contract_date1 = contract_date1.replaceAll("-","");
		contract_date2 = contract_date2.replaceAll("-","");
		
		String sql = "";
////    일반검색
		if (cmd.equals("normal")) {   //일반검색
			if(datetype.equals("1")) {   //일자별 검색
				if(trade_gbn.equals("매매")) {  //매매검색
					sql = "  SELECT TRADE_DD, TRADE_CNT, (TRADE_CNT - PREV_CNT) AS CHANGE_CNT, "
							+ "       ROUND((NVL(TRADE_CNT / DECODE(PREV_CNT,0,NULL,PREV_CNT),0) * 100 - 100), 2) AS CHANGE_RATE "
							+ "    FROM ( "
							+ "        WITH DAILY_COUNT AS (      "
							+ "            SELECT contract_date trade_dd, count(contract_date) trade_cnt "
							+ "                FROM TRADE_MST " 
							+ "                WHERE CITY_DO LIKE ? "
							+ "                  AND CITY_GU LIKE ? " 
							+ "                  AND DONG_GU LIKE ? "
							+ "                  AND APT_NAME LIKE ? " 
							+ "                  AND CONTRACT_DATE BETWEEN ? AND ? "
							+ "                  group by contract_date " 
							+ "                  ORDER BY CONTRACT_DATE DESC ) "
							+ "         SELECT TRADE_DD, TRADE_CNT, LAG(TRADE_CNT,1,0) OVER (ORDER BY TRADE_DD) AS  PREV_CNT "
							+ "            FROM DAILY_COUNT " 
							+ "            ORDER BY TRADE_DD DESC ) "
							+ "    WHERE TRADE_DD BETWEEN ? AND ? ";
				}else if(trade_gbn.equals("전세") || trade_gbn.equals("월세")) {  //전세_월세 검색
					sql = "  SELECT TRADE_DD, TRADE_CNT, (TRADE_CNT - PREV_CNT) AS CHANGE_CNT, "
							+ "       ROUND((NVL(TRADE_CNT / DECODE(PREV_CNT,0,NULL,PREV_CNT),0) * 100 - 100), 2) AS CHANGE_RATE "
							+ "    FROM ( "
							+ "        WITH DAILY_COUNT AS (      "
							+ "            SELECT contract_date trade_dd, count(contract_date) trade_cnt "
							+ "                FROM TRADE_MST02 " 
							+ "                WHERE CITY_DO LIKE ? "
							+ "                  AND CITY_GU LIKE ? " 
							+ "                  AND DONG_GU LIKE ? "
							+ "                  AND APT_NAME LIKE ? " 
							+ "                  AND CONTRACT_DATE BETWEEN ? AND ? "
							+ "                  AND KIND = ? " 							
							+ "                  group by contract_date " 
							+ "                  ORDER BY CONTRACT_DATE DESC ) "
							+ "         SELECT TRADE_DD, TRADE_CNT, LAG(TRADE_CNT,1,0) OVER (ORDER BY TRADE_DD) AS  PREV_CNT "
							+ "            FROM DAILY_COUNT " 
							+ "            ORDER BY TRADE_DD DESC ) "
							+ "    WHERE TRADE_DD BETWEEN ? AND ? ";
				}
			}else if(datetype.equals("2")) {  //월별 검색
				if(trade_gbn.equals("매매")) {  //매매검색
					sql = "  SELECT TRADE_DD, TRADE_CNT, (TRADE_CNT - PREV_CNT) AS CHANGE_CNT, "
							+ "     ROUND((NVL(TRADE_CNT / DECODE(PREV_CNT,0,NULL,PREV_CNT),0) * 100 - 100), 2) AS CHANGE_RATE "
							+ "    FROM ( "
							+ "        WITH DAILY_COUNT AS (      "
							+ "            SELECT contract_ym trade_dd, count(contract_date) trade_cnt "
							+ "                FROM TRADE_MST " 
							+ "                WHERE CITY_DO LIKE ? "
							+ "                  AND CITY_GU LIKE ? " 
							+ "                  AND DONG_GU LIKE ? "
							+ "                  AND APT_NAME LIKE ? " 
							+ "                  AND CONTRACT_DATE BETWEEN ? AND ? "
							+ "                  group by contract_ym " 
							+ "                  ORDER BY contract_ym DESC ) "
							+ "         SELECT TRADE_DD, TRADE_CNT, LAG(TRADE_CNT,1,0) OVER (ORDER BY TRADE_DD) AS  PREV_CNT "
							+ "            FROM DAILY_COUNT " 
							+ "            ORDER BY TRADE_DD DESC ) "
							+ "    WHERE TRADE_DD BETWEEN ?  AND ? "; 
				}else if(trade_gbn.equals("전세") || trade_gbn.equals("월세")) {  //전세_월세 검색
					sql = "  SELECT TRADE_DD, TRADE_CNT, (TRADE_CNT - PREV_CNT) AS CHANGE_CNT, "
							+ "     ROUND((NVL(TRADE_CNT / DECODE(PREV_CNT,0,NULL,PREV_CNT),0) * 100 - 100), 2) AS CHANGE_RATE "
							+ "    FROM ( "
							+ "        WITH DAILY_COUNT AS (      "
							+ "            SELECT contract_ym trade_dd, count(contract_date) trade_cnt "
							+ "                FROM TRADE_MST02 " 
							+ "                WHERE CITY_DO LIKE ? "
							+ "                  AND CITY_GU LIKE ? " 
							+ "                  AND DONG_GU LIKE ? "
							+ "                  AND APT_NAME LIKE ? " 
							+ "                  AND CONTRACT_DATE BETWEEN ? AND ? "
							+ "                  AND KIND = ? "
							+ "                  group by contract_ym " 
							+ "                  ORDER BY contract_ym DESC ) "
							+ "         SELECT TRADE_DD, TRADE_CNT, LAG(TRADE_CNT,1,0) OVER (ORDER BY TRADE_DD) AS  PREV_CNT "
							+ "            FROM DAILY_COUNT " 
							+ "            ORDER BY TRADE_DD DESC ) "
							+ "    WHERE TRADE_DD BETWEEN ?  AND ? "; 					
				}
			}else if(datetype.equals("3")) {  //연도별 검색
				if(trade_gbn.equals("매매")) {  //매매검색
					sql = "  SELECT TRADE_DD, TRADE_CNT, (TRADE_CNT - PREV_CNT) AS CHANGE_CNT, "
							+ "       ROUND((NVL(TRADE_CNT / DECODE(PREV_CNT,0,NULL,PREV_CNT),0) * 100 - 100), 2) AS CHANGE_RATE "
							+ "       FROM ( "
							+ "	      WITH DAILY_COUNT AS (      "
							+ "	           SELECT substr(contract_ym,0,4) trade_dd, count(contract_date) trade_cnt "
							+ "               FROM TRADE_MST  "
							+ "               WHERE CITY_DO LIKE ? "
							+ "                 AND CITY_GU LIKE ? "
							+ "                 AND DONG_GU LIKE ? "
							+ "                 AND APT_NAME LIKE ? "
							+ "                 AND CONTRACT_DATE BETWEEN ? AND ? "
							+ "                 group by SUBSTR(contract_ym,0,4) "
							+ "                 ORDER BY SUBSTR(contract_ym,0,4) DESC ) "
							+ "        SELECT TRADE_DD, TRADE_CNT, LAG(TRADE_CNT,1,0) OVER (ORDER BY TRADE_DD) AS  PREV_CNT "
							+ "           FROM DAILY_COUNT "
							+ "           ORDER BY TRADE_DD DESC ) "
							+ " WHERE TRADE_DD BETWEEN ? AND ? ";
				}else if(trade_gbn.equals("전세") || trade_gbn.equals("월세")) {  //전세_월세 검색
					sql = "  SELECT TRADE_DD, TRADE_CNT, (TRADE_CNT - PREV_CNT) AS CHANGE_CNT, "
							+ "       ROUND((NVL(TRADE_CNT / DECODE(PREV_CNT,0,NULL,PREV_CNT),0) * 100 - 100), 2) AS CHANGE_RATE "
							+ "       FROM ( "
							+ "	      WITH DAILY_COUNT AS (      "
							+ "	           SELECT substr(contract_ym,0,4) trade_dd, count(contract_date) trade_cnt "
							+ "               FROM TRADE_MST02  "
							+ "               WHERE CITY_DO LIKE ? "
							+ "                 AND CITY_GU LIKE ? "
							+ "                 AND DONG_GU LIKE ? "
							+ "                 AND APT_NAME LIKE ? "
							+ "                 AND CONTRACT_DATE BETWEEN ? AND ? "
							+ "                 AND KIND = ? "
							+ "                 group by SUBSTR(contract_ym,0,4) "
							+ "                 ORDER BY SUBSTR(contract_ym,0,4) DESC ) "
							+ "        SELECT TRADE_DD, TRADE_CNT, LAG(TRADE_CNT,1,0) OVER (ORDER BY TRADE_DD) AS  PREV_CNT "
							+ "           FROM DAILY_COUNT "
							+ "           ORDER BY TRADE_DD DESC ) "
							+ " WHERE TRADE_DD BETWEEN ? AND ? ";
				}	
			}
////    전체검색			
		}else if(cmd.equals("all")) {  //전체검색
			if(datetype.equals("1")) {   //일자별 검색
				if(trade_gbn.equals("매매")) {  //매매검색
					sql = "  SELECT TRADE_DD, TRADE_CNT, (TRADE_CNT - PREV_CNT) AS CHANGE_CNT, "
							+ "       ROUND((NVL(TRADE_CNT / DECODE(PREV_CNT,0,NULL,PREV_CNT),0) * 100 - 100), 2) AS CHANGE_RATE "
							+ "    FROM ( "
							+ "        WITH DAILY_COUNT AS (      "
							+ "            SELECT contract_date trade_dd, count(contract_date) trade_cnt "
							+ "                FROM TRADE_MST " 
							+ "                WHERE ( CITY_DO LIKE ? "
							+ "                   OR CITY_GU LIKE ? " 
							+ "                   OR DONG_GU LIKE ? "
							+ "                   OR APT_NAME LIKE ? ) " 
							+ "                  AND CONTRACT_DATE BETWEEN ? AND ? "
							+ "                 group by contract_date " 
							+ "                 ORDER BY CONTRACT_DATE DESC ) "
							+ "         SELECT TRADE_DD, TRADE_CNT, LAG(TRADE_CNT,1,0) OVER (ORDER BY TRADE_DD) AS  PREV_CNT "
							+ "            FROM DAILY_COUNT " 
							+ "            ORDER BY TRADE_DD DESC ) "
							+ "    WHERE TRADE_DD BETWEEN ? AND ? "; 	
				}else if(trade_gbn.equals("전세") || trade_gbn.equals("월세")) {  //전세_월세 검색
					sql = "  SELECT TRADE_DD, TRADE_CNT, (TRADE_CNT - PREV_CNT) AS CHANGE_CNT, "
							+ "       ROUND((NVL(TRADE_CNT / DECODE(PREV_CNT,0,NULL,PREV_CNT),0) * 100 - 100), 2) AS CHANGE_RATE "
							+ "    FROM ( "
							+ "        WITH DAILY_COUNT AS (      "
							+ "            SELECT contract_date trade_dd, count(contract_date) trade_cnt "
							+ "                FROM TRADE_MST02 " 
							+ "                WHERE ( CITY_DO LIKE ? "
							+ "                   OR CITY_GU LIKE ? " 
							+ "                   OR DONG_GU LIKE ? "
							+ "                   OR APT_NAME LIKE ? ) " 
							+ "                  AND CONTRACT_DATE BETWEEN ? AND ? "
							+ "                  AND KIND = ? "
							+ "                 group by contract_date " 
							+ "                 ORDER BY CONTRACT_DATE DESC ) "
							+ "         SELECT TRADE_DD, TRADE_CNT, LAG(TRADE_CNT,1,0) OVER (ORDER BY TRADE_DD) AS  PREV_CNT "
							+ "            FROM DAILY_COUNT " 
							+ "            ORDER BY TRADE_DD DESC ) "
							+ "    WHERE TRADE_DD BETWEEN ? AND ? "; 
				}
			}else if(datetype.equals("2")) {  //월별 검색
				if(trade_gbn.equals("매매")) {  //매매검색
					sql = "  SELECT TRADE_DD, TRADE_CNT, (TRADE_CNT - PREV_CNT) AS CHANGE_CNT, "
							+ "     ROUND((NVL(TRADE_CNT / DECODE(PREV_CNT,0,NULL,PREV_CNT),0) * 100 - 100), 2) AS CHANGE_RATE "
							+ "    FROM ( "
							+ "        WITH DAILY_COUNT AS (      "
							+ "            SELECT contract_ym trade_dd, count(contract_date) trade_cnt "
							+ "                FROM TRADE_MST " 
							+ "                WHERE ( CITY_DO LIKE ? "
							+ "                  OR CITY_GU LIKE ? " 
							+ "                  OR DONG_GU LIKE ? "
							+ "                  OR APT_NAME LIKE ? ) " 
							+ "                  AND CONTRACT_DATE BETWEEN ? AND ? "
							+ "                  group by contract_ym " 
							+ "                  ORDER BY contract_ym DESC ) "
							+ "         SELECT TRADE_DD, TRADE_CNT, LAG(TRADE_CNT,1,0) OVER (ORDER BY TRADE_DD) AS  PREV_CNT "
							+ "            FROM DAILY_COUNT " 
							+ "            ORDER BY TRADE_DD DESC ) "
							+ "    WHERE TRADE_DD BETWEEN ?  AND ? "; 	
				}else if(trade_gbn.equals("전세") || trade_gbn.equals("월세")) {  //전세_월세 검색
					sql = "  SELECT TRADE_DD, TRADE_CNT, (TRADE_CNT - PREV_CNT) AS CHANGE_CNT, "
							+ "     ROUND((NVL(TRADE_CNT / DECODE(PREV_CNT,0,NULL,PREV_CNT),0) * 100 - 100), 2) AS CHANGE_RATE "
							+ "    FROM ( "
							+ "        WITH DAILY_COUNT AS (      "
							+ "            SELECT contract_ym trade_dd, count(contract_date) trade_cnt "
							+ "                FROM TRADE_MST02 " 
							+ "                WHERE ( CITY_DO LIKE ? "
							+ "                  OR CITY_GU LIKE ? " 
							+ "                  OR DONG_GU LIKE ? "
							+ "                  OR APT_NAME LIKE ? ) " 
							+ "                  AND CONTRACT_DATE BETWEEN ? AND ? "
							+ "                  AND KIND = ? "
							+ "                  group by contract_ym " 
							+ "                  ORDER BY contract_ym DESC ) "
							+ "         SELECT TRADE_DD, TRADE_CNT, LAG(TRADE_CNT,1,0) OVER (ORDER BY TRADE_DD) AS  PREV_CNT "
							+ "            FROM DAILY_COUNT " 
							+ "            ORDER BY TRADE_DD DESC ) "
							+ "    WHERE TRADE_DD BETWEEN ?  AND ? "; 	
				}
			}else if(datetype.equals("3")) {  //연도별 검색
				if(trade_gbn.equals("매매")) {  //매매검색
					sql = "  SELECT TRADE_DD, TRADE_CNT, (TRADE_CNT - PREV_CNT) AS CHANGE_CNT, "
							+ "       ROUND((NVL(TRADE_CNT / DECODE(PREV_CNT,0,NULL,PREV_CNT),0) * 100 - 100), 2) AS CHANGE_RATE "
							+ "       FROM ( "
							+ "	      WITH DAILY_COUNT AS (      "
							+ "	           SELECT substr(contract_ym,0,4) trade_dd, count(contract_date) trade_cnt "
							+ "               FROM TRADE_MST  "
							+ "               WHERE ( CITY_DO LIKE ? "
							+ "                  OR CITY_GU LIKE ? "
							+ "                  OR DONG_GU LIKE ? "
							+ "                  OR APT_NAME LIKE ? ) "
							+ "                 AND CONTRACT_DATE BETWEEN ? AND ? "
							+ "                 group by SUBSTR(contract_ym,0,4) "
							+ "                 ORDER BY SUBSTR(contract_ym,0,4) DESC ) "
							+ "        SELECT TRADE_DD, TRADE_CNT, LAG(TRADE_CNT,1,0) OVER (ORDER BY TRADE_DD) AS  PREV_CNT "
							+ "           FROM DAILY_COUNT "
							+ "           ORDER BY TRADE_DD DESC ) "
							+ " WHERE TRADE_DD BETWEEN ? AND ? ";
				}else if(trade_gbn.equals("전세") || trade_gbn.equals("월세")) {  //전세_월세 검색
					sql = "  SELECT TRADE_DD, TRADE_CNT, (TRADE_CNT - PREV_CNT) AS CHANGE_CNT, "
							+ "       ROUND((NVL(TRADE_CNT / DECODE(PREV_CNT,0,NULL,PREV_CNT),0) * 100 - 100), 2) AS CHANGE_RATE "
							+ "       FROM ( "
							+ "	      WITH DAILY_COUNT AS (      "
							+ "	           SELECT substr(contract_ym,0,4) trade_dd, count(contract_date) trade_cnt "
							+ "               FROM TRADE_MST02  "
							+ "               WHERE ( CITY_DO LIKE ? "
							+ "                  OR CITY_GU LIKE ? "
							+ "                  OR DONG_GU LIKE ? "
							+ "                  OR APT_NAME LIKE ? ) "
							+ "                 AND CONTRACT_DATE BETWEEN ? AND ? "
							+ "                 AND KIND = ? "
							+ "                 group by SUBSTR(contract_ym,0,4) "
							+ "                 ORDER BY SUBSTR(contract_ym,0,4) DESC ) "
							+ "        SELECT TRADE_DD, TRADE_CNT, LAG(TRADE_CNT,1,0) OVER (ORDER BY TRADE_DD) AS  PREV_CNT "
							+ "           FROM DAILY_COUNT "
							+ "           ORDER BY TRADE_DD DESC ) "
							+ " WHERE TRADE_DD BETWEEN ? AND ? ";
				}
			}
		}
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = common.getConnection();
			st = con.prepareStatement(sql);

			if (cmd.equals("normal")) {  //일반검색
				st.setString(1, city_do);
				st.setString(2, city_gu);
				st.setString(3, dong_gu);
				st.setString(4, apt_name);
				st.setString(5, contract_date1);
				st.setString(6, contract_date2);
				if(trade_gbn.equals("매매")) {  //매매검색
					if(datetype.equals("1")) {  //일자별
						st.setString(7, contract_date1);
						st.setString(8, contract_date2);
					}else if(datetype.equals("2")) {  //월별
						st.setString(7, contract_date1.substring(0,6));
						st.setString(8, contract_date2.substring(0,6));					
					}else if(datetype.equals("3")) {  //년도별
						st.setString(7, contract_date1.substring(0,4));
						st.setString(8, contract_date2.substring(0,4));					
					}
				}else if(trade_gbn.equals("전세") || trade_gbn.equals("월세")) {  //전세_월세 검색
					st.setString(7, trade_gbn);
					if(datetype.equals("1")) {  //일자별
						st.setString(8, contract_date1);
						st.setString(9, contract_date2);
					}else if(datetype.equals("2")) {  //월별
						st.setString(8, contract_date1.substring(0,6));
						st.setString(9, contract_date2.substring(0,6));					
					}else if(datetype.equals("3")) {  //년도별
						st.setString(8, contract_date1.substring(0,4));
						st.setString(9, contract_date2.substring(0,4));					
					}					
				}
			}else if(cmd.equals("all")) {  //전체검색
				st.setString(1, '%'+tradeall+'%');
				st.setString(2, '%'+tradeall+'%');
				st.setString(3, '%'+tradeall+'%');
				st.setString(4, '%'+tradeall+'%');
				st.setString(5, contract_date1);
				st.setString(6, contract_date2);
				if(trade_gbn.equals("매매")) {  //매매검색
					if(datetype.equals("1")) {  //일자별
						st.setString(7, contract_date1);
						st.setString(8, contract_date2);
					}else if(datetype.equals("2")) {  //월별
						st.setString(7, contract_date1.substring(0,6));
						st.setString(8, contract_date2.substring(0,6));					
					}else if(datetype.equals("3")) {  //년도별
						st.setString(7, contract_date1.substring(0,4));
						st.setString(8, contract_date2.substring(0,4));					
					}
				}else if(trade_gbn.equals("전세") || trade_gbn.equals("월세")) {  //전세_월세 검색
					st.setString(7, trade_gbn);
					if(datetype.equals("1")) {  //일자별
						st.setString(8, contract_date1);
						st.setString(9, contract_date2);
					}else if(datetype.equals("2")) {  //월별
						st.setString(8, contract_date1.substring(0,6));
						st.setString(9, contract_date2.substring(0,6));					
					}else if(datetype.equals("3")) {  //년도별
						st.setString(8, contract_date1.substring(0,4));
						st.setString(9, contract_date2.substring(0,4));					
					}					
				}			
			}
			
			rs = st.executeQuery();
			while (rs.next()) {
				String trade_dd = rs.getString("trade_dd");
				if(trade_dd.length() > 10)
					trade_dd = trade_dd.substring(0,10);
				int trade_cnt = rs.getInt("trade_cnt");
				int change_cnt = rs.getInt("change_cnt");
				Double change_rate = rs.getDouble("change_rate");				
				Trade n = new Trade(trade_dd, trade_cnt, change_cnt, change_rate);
				list.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e2) {
				}
			if (st != null)
				try {
					st.close();
				} catch (Exception e2) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e2) {
				}
		}

		return list;
	}

}
