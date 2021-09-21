package yunsaptv2.trade.controller;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yunsaptv2.trade.entity.TradeArea;
import yunsaptv2.trade.service.TradeAreaService;

@WebServlet("/trade/tradearea")
public class TradeAreaController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라메타 값 받기
		String city_do_ = request.getParameter("city_do");
		String city_gu_ = request.getParameter("city_gu");
		String dong_gu_ = request.getParameter("dong_gu");
		String datetype_ = request.getParameter("datetype");
		String trade_gbn_ = request.getParameter("trade_gbn");
		String search_year_ = request.getParameter("search_year");
		String cmd_  = request.getParameter("search");
		String tradearea_all_  = request.getParameter("tradearea_all");
		
		// 변수 초기화
		String city_do = "서울특별시";
		String city_gu = "%";
		String dong_gu = "%";
		String cmd = "normal";
		String tradearea_all = "";
	    int trade_tot = 0;
		String datetype = "1";
		String search_year = "2021";
		String search_from = "";
		String search_to = "";
		String trade_gbn = "매매";
		
		// 널값 체크 및 값 설정
		if(cmd_ != null && !cmd_.equals(""))
			cmd = cmd_;		
		if(tradearea_all_ != null && !tradearea_all_.equals(""))
			tradearea_all = tradearea_all_;		
		if(city_do_ != null && !city_do_.equals(""))
			city_do = city_do_;
		if(city_gu_ != null && !city_gu_.equals(""))
			city_gu = city_gu_;
		if(dong_gu_ != null && !dong_gu_.equals(""))
			dong_gu = dong_gu_;
		if(datetype_ != null && !datetype_.equals("")) 
			datetype = datetype_;
		if(search_year_ != null && !search_year_.equals(""))
			search_year = search_year_;
		if(trade_gbn_ != null && !trade_gbn_.equals(""))
			switch (trade_gbn_) {
			case "1" :
				trade_gbn = "매매";
				break;
			case "2" :
				trade_gbn = "전세";
				break;
			case "3" :
				trade_gbn = "월세";
				break;
			default:
				break;
			}
		
		if(datetype.equals("1") || datetype.equals("")) {
			search_from = search_year + "01";
			search_to = search_year + "12";
		}else if(datetype.equals("2")) {
			int tmp_from = Integer.parseInt(search_year) - 12;
			if(tmp_from < 2010)
				tmp_from = 2010;
			search_from = String.valueOf(tmp_from);
			search_to = search_year;			
		}
		TradeAreaService service = new TradeAreaService();
//      실거래가 마스터 리스트가져오기		
		List<TradeArea> list = null;  //객체
		
		try {
			list = service.getTradeList
					(city_do, city_gu, dong_gu, datetype, search_from, search_to, 
					 trade_gbn, cmd, tradearea_all);
		} catch (ClassNotFoundException | NamingException e1) {
			e1.printStackTrace();
		}
		
		for(int i=0; i<list.size();i++) {
			trade_tot += list.get(i).getTotal();
		}
		
		
		request.setAttribute("list", list);
		request.setAttribute("tradetot", trade_tot);
	    request.getRequestDispatcher("/WEB-INF/view/trade/tradearea.jsp").forward(request, response);
	}

}
