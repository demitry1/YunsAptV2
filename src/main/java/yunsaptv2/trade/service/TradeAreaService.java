package yunsaptv2.trade.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import yunsaptv2.common.service.CommonService;
import yunsaptv2.trade.entity.TradeArea;

public class TradeAreaService {
	private CommonService common;

	public TradeAreaService() {
		common = new yunsaptv2.common.service.CommonService();
	}
	
	public List<TradeArea> getTradeList(String city_do, String city_gu, String dong_gu, String datetype,
			String search_from, String search_to, String trade_gbn, String cmd,
			String tradeall) throws NamingException, ClassNotFoundException {

		List<TradeArea> list = new ArrayList<TradeArea>();
		
		int i_search_to = Integer.parseInt(search_to);
		String sql = "";
		String v_sql1 = "";   //입력조건에 따라 지역명에 구, 동을 가변적으로 출력하기 위한 sql 변수 
		String v_sql2 = "";   //입력조건에 따라 지역명에 구, 동을 가변적으로 출력하기 위한 sql 변수 
		String v_sql3 = "";   //입력조건에 따라 지역명에 구, 동을 가변적으로 출력하기 위한 sql 변수 
		String v_sql4 = "";   //입력조건에 따라 지역명에 구, 동을 가변적으로 출력하기 위한 sql 변수 
		if(!city_gu.equals("%")) {
			v_sql1 = " SELECT DONG_GU AREA, ";
			v_sql2 = "      SELECT city_do, city_gu, dong_gu, ";
			if(datetype.equals("1")) {  //월별
				v_sql3 = "      GROUP BY city_do, city_gu, dong_gu, CONTRACT_YM ";
			}else if(datetype.equals("2")) {  //년도별
				v_sql3 = "      GROUP BY city_do, city_gu, dong_gu, SUBSTR(CONTRACT_YM,1,4) ";
			}
			v_sql4 = "  GROUP BY CITY_DO, CITY_GU, DONG_GU ";
		}else {
			v_sql1 = " SELECT CITY_GU AREA, ";
			v_sql2 = "      SELECT city_do, city_gu, ";
			if(datetype.equals("1")) {  //월별
				v_sql3 = "      GROUP BY city_do, city_gu, CONTRACT_YM ";
			}else if(datetype.equals("2")) {  //년도별
				v_sql3 = "      GROUP BY city_do, city_gu, SUBSTR(CONTRACT_YM,1,4) ";
			}
			v_sql4 = "  GROUP BY CITY_DO, CITY_GU ";
		}
		
////    일반검색
		if (cmd.equals("normal")) {   //일반검색
			if(datetype.equals("1")) {   //월별 검색
				if(trade_gbn.equals("매매")) {  //매매검색
					sql = v_sql1 + "     MAX(M01) M01, "
							+ "         MAX(M02) M02, "
							+ "         MAX(M03) M03, "
							+ "         MAX(M04) M04, "
							+ "         MAX(M05) M05, "
							+ "         MAX(M06) M06, "
							+ "         MAX(M07) M07, "
							+ "         MAX(M08) M08, "
							+ "         MAX(M09) M09, "
							+ "         MAX(M10) M10, "
							+ "         MAX(M11) M11, "
							+ "         MAX(M12) M12, "
							+ "         SUM(TOTAL) TOTAL "
							+ "  FROM ( ";
					sql = sql + v_sql2 + " CONTRACT_YM, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'01',count(contract_date)),0) M01, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'02',count(contract_date)),0) M02,  "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'03',count(contract_date)),0) M03, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'04',count(contract_date)),0) M04, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'05',count(contract_date)),0) M05, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'06',count(contract_date)),0) M06, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'07',count(contract_date)),0) M07, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'08',count(contract_date)),0) M08, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'09',count(contract_date)),0) M09, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'10',count(contract_date)),0) M10, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'11',count(contract_date)),0) M11, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'12',count(contract_date)),0) M12, "
							+ "           count(contract_date) TOTAL "
							+ "      FROM TRADE_MST   "
							+ "      WHERE CITY_DO LIKE ? "
							+ "        AND CITY_GU LIKE ? "
							+ "        AND DONG_GU LIKE ? "
							+ "        AND CONTRACT_YM BETWEEN ? AND ? ";
					sql = sql + v_sql3 + "      ORDER BY CONTRACT_YM ) ";
					sql = sql + v_sql4 + "  ORDER BY TOTAL DESC ";
				}else if(trade_gbn.equals("전세") || trade_gbn.equals("월세")) {  //전세_월세 검색
					sql = v_sql1 + "     MAX(M01) M01, "
							+ "         MAX(M02) M02, "
							+ "         MAX(M03) M03, "
							+ "         MAX(M04) M04, "
							+ "         MAX(M05) M05, "
							+ "         MAX(M06) M06, "
							+ "         MAX(M07) M07, "
							+ "         MAX(M08) M08, "
							+ "         MAX(M09) M09, "
							+ "         MAX(M10) M10, "
							+ "         MAX(M11) M11, "
							+ "         MAX(M12) M12, "
							+ "         SUM(TOTAL) TOTAL "
							+ "  FROM ( ";
					sql = sql + v_sql2 + " CONTRACT_YM, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'01',count(contract_date)),0) M01, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'02',count(contract_date)),0) M02,  "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'03',count(contract_date)),0) M03, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'04',count(contract_date)),0) M04, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'05',count(contract_date)),0) M05, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'06',count(contract_date)),0) M06, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'07',count(contract_date)),0) M07, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'08',count(contract_date)),0) M08, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'09',count(contract_date)),0) M09, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'10',count(contract_date)),0) M10, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'11',count(contract_date)),0) M11, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'12',count(contract_date)),0) M12, "
							+ "           count(contract_date) TOTAL "
							+ "      FROM TRADE_MST02   "
							+ "      WHERE CITY_DO LIKE ? "
							+ "        AND CITY_GU LIKE ? "
							+ "        AND DONG_GU LIKE ? "
							+ "        AND KIND = ? "
							+ "        AND CONTRACT_YM BETWEEN ? AND ? ";
					sql = sql + v_sql3 + "      ORDER BY CONTRACT_YM ) ";
					sql = sql + v_sql4 + "  ORDER BY TOTAL DESC ";
				}
			}else if(datetype.equals("2")) {  //연도별 검색
				if(trade_gbn.equals("매매")) {  //매매검색
					sql = v_sql1 + "     MAX(M01) M01, "
							+ "         MAX(M02) M02, "
							+ "         MAX(M03) M03, "
							+ "         MAX(M04) M04, "
							+ "         MAX(M05) M05, "
							+ "         MAX(M06) M06, "
							+ "         MAX(M07) M07, "
							+ "         MAX(M08) M08, "
							+ "         MAX(M09) M09, "
							+ "         MAX(M10) M10, "
							+ "         MAX(M11) M11, "
							+ "         MAX(M12) M12, "
							+ "         SUM(TOTAL) TOTAL "
							+ "  FROM ( ";
					sql = sql + v_sql2 + " SUBSTR(CONTRACT_YM,1,4), "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 11) + ",count(contract_date)),0) M01, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 10) + ",count(contract_date)),0) M02,  "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 9) + ",count(contract_date)),0) M03, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 8) + ",count(contract_date)),0) M04, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 7) + ",count(contract_date)),0) M05, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 6) + ",count(contract_date)),0) M06, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 5) + ",count(contract_date)),0) M07, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 4) + ",count(contract_date)),0) M08, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 3) + ",count(contract_date)),0) M09, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 2) + ",count(contract_date)),0) M10, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 1) + ", count(contract_date)),0) M11, ";
					sql = sql  + "        NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + i_search_to + ", count(contract_date)),0) M12, "
							+ "           count(contract_date) TOTAL "
							+ "      FROM TRADE_MST   "
							+ "      WHERE CITY_DO LIKE ? "
							+ "        AND CITY_GU LIKE ? "
							+ "        AND DONG_GU LIKE ? "
							+ "        AND SUBSTR(CONTRACT_YM,1,4) BETWEEN ? AND ? ";
					sql = sql + v_sql3 + "      ORDER BY CONTRACT_YM ) ";
					sql = sql + v_sql4 + "  ORDER BY TOTAL DESC ";
				}else if(trade_gbn.equals("전세") || trade_gbn.equals("월세")) {  //전세_월세 검색
					sql = v_sql1 + "     MAX(M01) M01, "
							+ "         MAX(M02) M02, "
							+ "         MAX(M03) M03, "
							+ "         MAX(M04) M04, "
							+ "         MAX(M05) M05, "
							+ "         MAX(M06) M06, "
							+ "         MAX(M07) M07, "
							+ "         MAX(M08) M08, "
							+ "         MAX(M09) M09, "
							+ "         MAX(M10) M10, "
							+ "         MAX(M11) M11, "
							+ "         MAX(M12) M12, "
							+ "         SUM(TOTAL) TOTAL "
							+ "  FROM ( ";
					sql = sql + v_sql2 + " SUBSTR(CONTRACT_YM,1,4), "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 11) + ",count(contract_date)),0) M01, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 10) + ",count(contract_date)),0) M02,  "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 9) + ",count(contract_date)),0) M03, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 8) + ",count(contract_date)),0) M04, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 7) + ",count(contract_date)),0) M05, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 6) + ",count(contract_date)),0) M06, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 5) + ",count(contract_date)),0) M07, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 4) + ",count(contract_date)),0) M08, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 3) + ",count(contract_date)),0) M09, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 2) + ",count(contract_date)),0) M10, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 1) + ", count(contract_date)),0) M11, ";
					sql = sql  + "        NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + i_search_to + ", count(contract_date)),0) M12, "
							+ "           count(contract_date) TOTAL "
							+ "      FROM TRADE_MST02   "
							+ "      WHERE CITY_DO LIKE ? "
							+ "        AND CITY_GU LIKE ? "
							+ "        AND DONG_GU LIKE ? "
							+ "        AND KIND = ? "
							+ "        AND SUBSTR(CONTRACT_YM,1,4) BETWEEN ? AND ? ";
					sql = sql + v_sql3 + "      ORDER BY CONTRACT_YM ) ";
					sql = sql + v_sql4 + "  ORDER BY TOTAL DESC ";				
				}
			}
////    전체검색			
		}else if(cmd.equals("all")) {  //전체검색
			if(datetype.equals("1")) {   //월별 검색
				if(trade_gbn.equals("매매")) {  //매매검색
					sql = v_sql1 + "     MAX(M01) M01, "
							+ "         MAX(M02) M02, "
							+ "         MAX(M03) M03, "
							+ "         MAX(M04) M04, "
							+ "         MAX(M05) M05, "
							+ "         MAX(M06) M06, "
							+ "         MAX(M07) M07, "
							+ "         MAX(M08) M08, "
							+ "         MAX(M09) M09, "
							+ "         MAX(M10) M10, "
							+ "         MAX(M11) M11, "
							+ "         MAX(M12) M12, "
							+ "         SUM(TOTAL) TOTAL "
							+ "  FROM ( ";
					sql = sql + v_sql2 + " CONTRACT_YM, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'01',count(contract_date)),0) M01, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'02',count(contract_date)),0) M02,  "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'03',count(contract_date)),0) M03, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'04',count(contract_date)),0) M04, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'05',count(contract_date)),0) M05, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'06',count(contract_date)),0) M06, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'07',count(contract_date)),0) M07, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'08',count(contract_date)),0) M08, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'09',count(contract_date)),0) M09, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'10',count(contract_date)),0) M10, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'11',count(contract_date)),0) M11, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'12',count(contract_date)),0) M12, "
							+ "           count(contract_date) TOTAL "
							+ "      FROM TRADE_MST   "
							+ "      WHERE ( CITY_DO LIKE ? "
							+ "           OR CITY_GU LIKE ? "
							+ "           OR DONG_GU LIKE ? ) "
							+ "        AND CONTRACT_YM BETWEEN ? AND ? ";
					sql = sql + v_sql3 + "      ORDER BY CONTRACT_YM ) ";
					sql = sql + v_sql4 + "  ORDER BY TOTAL DESC ";
				}else if(trade_gbn.equals("전세") || trade_gbn.equals("월세")) {  //전세_월세 검색
					sql = v_sql1 + "     MAX(M01) M01, "
							+ "         MAX(M02) M02, "
							+ "         MAX(M03) M03, "
							+ "         MAX(M04) M04, "
							+ "         MAX(M05) M05, "
							+ "         MAX(M06) M06, "
							+ "         MAX(M07) M07, "
							+ "         MAX(M08) M08, "
							+ "         MAX(M09) M09, "
							+ "         MAX(M10) M10, "
							+ "         MAX(M11) M11, "
							+ "         MAX(M12) M12, "
							+ "         SUM(TOTAL) TOTAL "
							+ "  FROM ( ";
					sql = sql + v_sql2 + " CONTRACT_YM, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'01',count(contract_date)),0) M01, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'02',count(contract_date)),0) M02,  "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'03',count(contract_date)),0) M03, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'04',count(contract_date)),0) M04, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'05',count(contract_date)),0) M05, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'06',count(contract_date)),0) M06, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'07',count(contract_date)),0) M07, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'08',count(contract_date)),0) M08, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'09',count(contract_date)),0) M09, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'10',count(contract_date)),0) M10, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'11',count(contract_date)),0) M11, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,5,2),'12',count(contract_date)),0) M12, "
							+ "           count(contract_date) TOTAL "
							+ "      FROM TRADE_MST02   "
							+ "      WHERE ( CITY_DO LIKE ? "
							+ "           OR CITY_GU LIKE ? "
							+ "           OR DONG_GU LIKE ? ) "
							+ "        AND KIND = ? "
							+ "        AND CONTRACT_YM BETWEEN ? AND ? ";
					sql = sql + v_sql3 + "      ORDER BY CONTRACT_YM ) ";
					sql = sql + v_sql4 + "  ORDER BY TOTAL DESC ";
				}
			}else if(datetype.equals("2")) {  //연도별 검색
				if(trade_gbn.equals("매매")) {  //매매검색
					sql = v_sql1 + "     MAX(M01) M01, "
							+ "         MAX(M02) M02, "
							+ "         MAX(M03) M03, "
							+ "         MAX(M04) M04, "
							+ "         MAX(M05) M05, "
							+ "         MAX(M06) M06, "
							+ "         MAX(M07) M07, "
							+ "         MAX(M08) M08, "
							+ "         MAX(M09) M09, "
							+ "         MAX(M10) M10, "
							+ "         MAX(M11) M11, "
							+ "         MAX(M12) M12, "
							+ "         SUM(TOTAL) TOTAL "
							+ "  FROM ( ";
					sql = sql + v_sql2 + " SUBSTR(CONTRACT_YM,1,4), "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 11) + ",count(contract_date)),0) M01, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 10) + ",count(contract_date)),0) M02,  "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 9) + ",count(contract_date)),0) M03, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 8) + ",count(contract_date)),0) M04, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 7) + ",count(contract_date)),0) M05, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 6) + ",count(contract_date)),0) M06, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 5) + ",count(contract_date)),0) M07, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 4) + ",count(contract_date)),0) M08, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 3) + ",count(contract_date)),0) M09, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 2) + ",count(contract_date)),0) M10, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 1) + ", count(contract_date)),0) M11, ";
					sql = sql  + "        NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + i_search_to + ", count(contract_date)),0) M12, "
							+ "           count(contract_date) TOTAL "
							+ "      FROM TRADE_MST   "
							+ "      WHERE ( CITY_DO LIKE ? "
							+ "           OR CITY_GU LIKE ? "
							+ "           OR DONG_GU LIKE ? ) "
							+ "        AND SUBSTR(CONTRACT_YM,1,4) BETWEEN ? AND ? ";
					sql = sql + v_sql3 + "      ORDER BY CONTRACT_YM ) ";
					sql = sql + v_sql4 + "  ORDER BY TOTAL DESC ";
				}else if(trade_gbn.equals("전세") || trade_gbn.equals("월세")) {  //전세_월세 검색
					sql = v_sql1 + "     MAX(M01) M01, "
							+ "         MAX(M02) M02, "
							+ "         MAX(M03) M03, "
							+ "         MAX(M04) M04, "
							+ "         MAX(M05) M05, "
							+ "         MAX(M06) M06, "
							+ "         MAX(M07) M07, "
							+ "         MAX(M08) M08, "
							+ "         MAX(M09) M09, "
							+ "         MAX(M10) M10, "
							+ "         MAX(M11) M11, "
							+ "         MAX(M12) M12, "
							+ "         SUM(TOTAL) TOTAL "
							+ "  FROM ( ";
					sql = sql + v_sql2 + " SUBSTR(CONTRACT_YM,1,4), "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 11) + ",count(contract_date)),0) M01, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 10) + ",count(contract_date)),0) M02,  "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 9) + ",count(contract_date)),0) M03, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 8) + ",count(contract_date)),0) M04, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 7) + ",count(contract_date)),0) M05, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 6) + ",count(contract_date)),0) M06, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 5) + ",count(contract_date)),0) M07, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 4) + ",count(contract_date)),0) M08, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 3) + ",count(contract_date)),0) M09, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 2) + ",count(contract_date)),0) M10, "
							+ "           NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + (i_search_to - 1) + ", count(contract_date)),0) M11, ";
					sql = sql  + "        NVL(DECODE(SUBSTR(CONTRACT_YM,1,4), " + i_search_to + ", count(contract_date)),0) M12, "
							+ "           count(contract_date) TOTAL "
							+ "      FROM TRADE_MST02   "
							+ "      WHERE ( CITY_DO LIKE ? "
							+ "           OR CITY_GU LIKE ? "
							+ "           OR DONG_GU LIKE ? ) "
							+ "        AND KIND = ? "
							+ "        AND SUBSTR(CONTRACT_YM,1,4) BETWEEN ? AND ? ";
					sql = sql + v_sql3 + "      ORDER BY CONTRACT_YM ) ";
					sql = sql + v_sql4 + "  ORDER BY TOTAL DESC ";				
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
				if(trade_gbn.equals("매매")) {  //매매검색
					st.setString(4, search_from);
					st.setString(5, search_to);
				}else if(trade_gbn.equals("전세") || trade_gbn.equals("월세")) {  //전세_월세 검색
					st.setString(4, trade_gbn);
					st.setString(5, search_from);
					st.setString(6, search_to);
				}
			}else if(cmd.equals("all")) {  //전체검색
				st.setString(1, '%'+tradeall+'%');
				st.setString(2, '%'+tradeall+'%');
				st.setString(3, '%'+tradeall+'%');
				if(trade_gbn.equals("매매")) {  //매매검색
					st.setString(4, search_from);
					st.setString(5, search_to);
				}else if(trade_gbn.equals("전세") || trade_gbn.equals("월세")) {  //전세_월세 검색
					st.setString(4, trade_gbn);
					st.setString(5, search_from);
					st.setString(6, search_to);
				}			
			}
			
			rs = st.executeQuery();
			while (rs.next()) {
				String area = rs.getString("area");
				int m01 = rs.getInt("m01");
				int m02 = rs.getInt("m02");
				int m03 = rs.getInt("m03");
				int m04 = rs.getInt("m04");
				int m05 = rs.getInt("m05");
				int m06 = rs.getInt("m06");
				int m07 = rs.getInt("m07");
				int m08 = rs.getInt("m08");
				int m09 = rs.getInt("m09");
				int m10 = rs.getInt("m10");
				int m11 = rs.getInt("m11");
				int m12 = rs.getInt("m12");
				int total = rs.getInt("total");
				TradeArea n = new TradeArea(area, m01, m02, m03, m04, m05, m06, m07, m08, 
						                m09, m10, m11, m12, total);
				
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
