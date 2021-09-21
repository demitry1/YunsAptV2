package yunsaptv2.pricestats.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yunsaptv2.trade.entity.Trade;
import yunsaptv2.trade.service.TradeService;

@WebServlet("/pricestats/highprice")
public class PriceStatsController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �Ķ��Ÿ �� �ޱ�
		String city_do_ = request.getParameter("city_do");
		String city_gu_ = request.getParameter("city_gu");
		String dong_gu_ = request.getParameter("dong_gu");
		String datetype_ = request.getParameter("datetype");
		String contract_date1_ = request.getParameter("contract_date1");
		String contract_date2_ = request.getParameter("contract_date2");
		String trade_gbn_ = request.getParameter("trade_gbn");
		String apt_name_ = request.getParameter("apt_name");				
		String cmd_  = request.getParameter("search");
		String tradeall_  = request.getParameter("tradeall");
		
		// ���ڼ���
		Calendar cal = Calendar.getInstance();
		String format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		// ���� �ʱ�ȭ
		String city_do = "����Ư����";
		String city_gu = "%";
		String dong_gu = "%";
		String cmd = "normal";
		String tradeall = "";
		String apt_name = "%";
	    int trade_tot = 0;
	    	    
		String contract_date2 = sdf.format(cal.getTime());  //��������
		cal.add(Calendar.MONTH, -1); //1����
		String contract_date1 = sdf.format(cal.getTime());	//1��������	

		String datetype = "1";
		String trade_gbn = "�Ÿ�";
		
		// �ΰ� üũ �� �� ����
		if(cmd_ != null && !cmd_.equals(""))
			cmd = cmd_;		
		if(tradeall_ != null && !tradeall_.equals(""))
			tradeall = tradeall_;		
		if(city_do_ != null && !city_do_.equals(""))
			city_do = city_do_;
		if(city_gu_ != null && !city_gu_.equals(""))
			city_gu = city_gu_;
		if(dong_gu_ != null && !dong_gu_.equals(""))
			dong_gu = dong_gu_;
		if(datetype_ != null && !datetype_.equals("")) 
			datetype = datetype_;
		if(contract_date1_ != null && !contract_date1_.equals(""))
			contract_date1 = contract_date1_;
		if(contract_date2_ != null && !contract_date2_.equals(""))
			contract_date2 = contract_date2_;
		if(trade_gbn_ != null && !trade_gbn_.equals(""))
			switch (trade_gbn_) {
			case "1" :
				trade_gbn = "�Ÿ�";
				break;
			case "2" :
				trade_gbn = "����";
				break;
			case "3" :
				trade_gbn = "����";
				break;
			default:
				break;
			}
		
		if(apt_name_ != null && !apt_name_.equals(""))
			apt_name = apt_name_;
		
		TradeService service = new TradeService();
//      �ǰŷ��� ������ ����Ʈ��������		
		List<Trade> list = null;  //��ü
		
		try {
			list = service.getTradeList
					(city_do, city_gu, dong_gu, datetype, contract_date1, contract_date2, 
					 trade_gbn, apt_name, cmd, tradeall);
		} catch (ClassNotFoundException | NamingException e1) {
			e1.printStackTrace();
		}
		
		for(int i=0; i<list.size();i++) {
			trade_tot += list.get(i).getTrade_cnt();
		}
		
		
		request.setAttribute("list", list);
		request.setAttribute("tradetot", trade_tot);
	    request.getRequestDispatcher("/WEB-INF/view/pricestats/highprice.jsp").forward(request, response);
	}

}
