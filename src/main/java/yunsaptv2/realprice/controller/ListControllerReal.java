package yunsaptv2.realprice.controller;

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

import yunsaptv2.realprice.entity.RealPrice;
import yunsaptv2.realprice.service.RealPriceService;

@WebServlet("/realprice/realprice")
public class ListControllerReal extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �Ķ��Ÿ �� �ޱ�
		String city_do_ = request.getParameter("city_do");
		String city_gu_ = request.getParameter("city_gu");
		String dong_gu_ = request.getParameter("dong_gu");
		String scale_ = request.getParameter("scale");
		String contract_date1_ = request.getParameter("contract_date1");
		String contract_date2_ = request.getParameter("contract_date2");
		String construction_year_ = request.getParameter("construction_year");
		String apt_name_ = request.getParameter("apt_name");				
		String page_  = request.getParameter("p");
		String cmd_  = request.getParameter("search");
		String realall_  = request.getParameter("realall");
		String listcnt_  = request.getParameter("listcnt");
		
		// ���ڼ���
		Calendar cal = Calendar.getInstance();
		String format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
//		String date = sdf.format(cal.getTime());
		
		// ���� �ʱ�ȭ
		String city_do = "%";
		String city_gu = "%";
		String dong_gu = "%";
		String cmd = "normal";
		String realall = "";

		
		int scale_beg = 0;
		int scale_end = 999; 
		int construction_year_beg = 0;
		int construction_year_end = cal.get(Calendar.YEAR); //����⵵
		String contract_date2 = sdf.format(cal.getTime());  //��������
		cal.add(Calendar.MONTH, -3); //3����
		String contract_date1 = sdf.format(cal.getTime());	//1��������	
		String apt_name = "%";
		int page = 1;

		// �ΰ� üũ �� �� ����
		if(cmd_ != null && !cmd_.equals(""))
			cmd = cmd_;		
		if(realall_ != null && !realall_.equals(""))
			realall = realall_;		
		if(city_do_ != null && !city_do_.equals(""))
			city_do = city_do_;
		if(city_gu_ != null && !city_gu_.equals(""))
			city_gu = city_gu_;
		if(dong_gu_ != null && !dong_gu_.equals(""))
			dong_gu = dong_gu_;
		if(scale_ != null && !scale_.equals("")) {
			switch (scale_) {
			case "1":  //��ü
				scale_beg = 0;
				scale_end = 999;
				break;
			case "2": //40���̻�
				scale_beg = 40;
				scale_end = 999;
				break;
			case "3":  //30���̻�
				scale_beg = 30;
				scale_end = 39;
				break;
			case "4": //20���̻�
				scale_beg = 20;
				scale_end = 29;
				break;
			case "5": //20��̸�
				scale_beg = 0;
				scale_end = 19;
				break;
			default:
				break;
			}
		}
		if(contract_date1_ != null && !contract_date1_.equals(""))
			contract_date1 = contract_date1_;
		if(contract_date2_ != null && !contract_date2_.equals(""))
			contract_date2 = contract_date2_;
		if(construction_year_ != null && !construction_year_.equals("")) {		
			switch (construction_year_) {
			case "1":  // ��ü
				construction_year_beg = 0;
				break;
			case "2":  // 3������
				construction_year_beg = construction_year_end - 2;
				break;
			case "3":  // 5������
				construction_year_beg = construction_year_end - 4;
				break;
			case "4":  // 10������
				construction_year_beg = construction_year_end - 9;
				break;
			case "5":  // 15������
				construction_year_beg = construction_year_end - 14;
				break;
			case "6":  // 20������
				construction_year_beg = construction_year_end - 19;
				break;
			case "7":  // 25������
				construction_year_beg = construction_year_end - 24;
				break;
			case "8":  // 30������
				construction_year_beg = construction_year_end - 29;
				break;
			case "9":  // 30���ʰ�
				construction_year_beg = 0;
				construction_year_end = construction_year_end - 30;
				break;
			default:
				break;
			}
		}
		if(apt_name_ != null && !apt_name_.equals(""))
			apt_name = apt_name_;
		if(page_ != null && !page_.equals(""))
			page = Integer.parseInt(page_) ;
		
		int listcnt = 40;
		if(listcnt_ != null && !listcnt_.equals(""))
			listcnt = Integer.parseInt(listcnt_) ;
		
		
		RealPriceService service = new RealPriceService();
//      �ǰŷ��� ������ ����Ʈ��������		
		List<RealPrice> list = null;  //��ü
		try {
			list = service.getRealPriceList
					(city_do, city_gu, dong_gu, scale_beg, scale_end, contract_date1, contract_date2, 
					 construction_year_beg, construction_year_end, apt_name, page, cmd, realall, listcnt);
		} catch (ClassNotFoundException | NamingException e1) {
			e1.printStackTrace();
		}
//      �ǰŷ��� ������ �� ī��Ʈ ��������	
		int[] count = new int[2];
		
		try {
			count = service.getRealPriceCount(city_do, city_gu, dong_gu, scale_beg, scale_end, 
					 contract_date1, contract_date2, construction_year_beg, construction_year_end, 
					 apt_name, cmd, realall);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
				
		request.setAttribute("list", list);
		request.setAttribute("count", count[0]);
		request.setAttribute("total_count", count[1]);
		request.setAttribute("listcnt", listcnt);
		request.getRequestDispatcher("/WEB-INF/view/realprice/realprice.jsp").forward(request, response);
	}

}
