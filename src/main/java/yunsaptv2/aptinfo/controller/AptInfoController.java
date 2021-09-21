package yunsaptv2.aptinfo.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yunsaptv2.aptinfo.entity.AptInfo;
import yunsaptv2.aptinfo.service.AptInfoService;

@WebServlet("/aptinfo/aptinfo")
public class AptInfoController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				// 파라메타 값 받기
				String city_do_ = request.getParameter("city_do");
				String city_gu_ = request.getParameter("city_gu");
				String dong_gu_ = request.getParameter("dong_gu");
				String construction_year_ = request.getParameter("construction_year");
				String apt_name_ = request.getParameter("apt_name");				
				String page_  = request.getParameter("p");
				String cmd_  = request.getParameter("search");
				String infoall_  = request.getParameter("infoall");
				String listcnt_  = request.getParameter("listcnt");
				
				// 날자설정
				Calendar cal = Calendar.getInstance();
				
				// 변수 초기화
				String city_do = "%";
				String city_gu = "%";
				String dong_gu = "%";
				String cmd = "normal";
				String infoall = "";
		
				
				int construction_year_beg = 0;
				int construction_year_end = cal.get(Calendar.YEAR); //현재년도
				String apt_name = "%";
				int page = 1;
		
				// 널값 체크 및 값 설정
				if(cmd_ != null && !cmd_.equals(""))
					cmd = cmd_;		
				if(infoall_ != null && !infoall_.equals(""))
					infoall = infoall_;		
				if(city_do_ != null && !city_do_.equals(""))
					city_do = city_do_;
				if(city_gu_ != null && !city_gu_.equals(""))
					city_gu = city_gu_;
				if(dong_gu_ != null && !dong_gu_.equals(""))
					dong_gu = dong_gu_;
				if(construction_year_ != null && !construction_year_.equals("")) {		
					switch (construction_year_) {
					case "1":  // 전체
						construction_year_beg = 0;
						break;
					case "2":  // 3년이하
						construction_year_beg = construction_year_end - 2;
						break;
					case "3":  // 5년이하
						construction_year_beg = construction_year_end - 4;
						break;
					case "4":  // 10년이하
						construction_year_beg = construction_year_end - 9;
						break;
					case "5":  // 15년이하
						construction_year_beg = construction_year_end - 14;
						break;
					case "6":  // 20년이하
						construction_year_beg = construction_year_end - 19;
						break;
					case "7":  // 25년이하
						construction_year_beg = construction_year_end - 24;
						break;
					case "8":  // 30년이하
						construction_year_beg = construction_year_end - 29;
						break;
					case "9":  // 30년초과
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
				
				
				AptInfoService service = new AptInfoService();
		//      실거래가 마스터 리스트가져오기		
				List<AptInfo> list = null;  //객체
				try {
					list = service.getAptInfoList
							(city_do, city_gu, dong_gu, construction_year_beg, construction_year_end, 
									apt_name, page, cmd, infoall, listcnt);
				} catch (ClassNotFoundException | NamingException e1) {
					e1.printStackTrace();
				}
		//      실거래가 마스터 총 카운트 가져오기	
				int[] count = new int[2];
				
				try {
					count = service.getAptInfoCount(city_do, city_gu, dong_gu, construction_year_beg, construction_year_end,
							 apt_name, cmd, infoall);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (NamingException e) {
					e.printStackTrace();
				}
				
		request.setAttribute("list", list);
		request.setAttribute("count", count[0]);
		request.setAttribute("total_count", count[1]);
		request.setAttribute("listcnt", listcnt);
		request.getRequestDispatcher("/WEB-INF/view/aptinfo/aptinfo.jsp").forward(request, response);
	}

}
