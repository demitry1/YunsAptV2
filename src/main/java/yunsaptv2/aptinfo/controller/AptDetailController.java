package yunsaptv2.aptinfo.controller;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yunsaptv2.aptinfo.entity.AptDetail;
import yunsaptv2.aptinfo.entity.AptInfoDetail;
import yunsaptv2.aptinfo.entity.AptScale;
import yunsaptv2.aptinfo.service.AptDetailService;

@WebServlet("/aptinfo/aptinfo_detail")
public class AptDetailController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AptDetailService service = new AptDetailService();
		// 파라메타 값 받기
		String city_do_ = request.getParameter("city_do2");
		String city_gu_ = request.getParameter("city_gu2");
		String dong_gu_ = request.getParameter("dong_gu2");
		String apt_name_ = request.getParameter("apt_name2");				
		String kind_ = request.getParameter("kind2");				
		String construction_year_ = request.getParameter("construction_year2");				
		String address_road_ = request.getParameter("address_road2");				
		String address_jibun_ = request.getParameter("address_jibun2");				
		
		// 변수 초기화
		String city_do = "%";
		String city_gu = "%";
		String dong_gu = "%";
		String apt_name = "%";
		String kind = "%";
		String construction_year = "%";
		String address_road = "%";
		String address_jibun = "%";

		// 널값 체크 및 값 설정
		if(city_do_ != null && !city_do_.equals(""))
			city_do = city_do_;
		if(city_gu_ != null && !city_gu_.equals(""))
			city_gu = city_gu_;
		if(dong_gu_ != null && !dong_gu_.equals(""))
			dong_gu = dong_gu_;
		if(apt_name_ != null && !apt_name_.equals(""))
			apt_name = apt_name_;
		if(construction_year_ != null && !construction_year_.equals(""))
			construction_year = construction_year_;
		if(address_road_ != null && !address_road_.equals(""))
			address_road = address_road_;
		if(address_jibun_ != null && !address_jibun_.equals(""))
			address_jibun = address_jibun_;
		if(kind_ != null && !kind_.equals("")) {
			kind = kind_;
		}else {
			kind = "매매";
		}
						
  //      실거래가 마스터 리스트가져오기		
		List<AptDetail> list = null;  //객체
			try {
				list = service.getAptDetailList
						(city_do, city_gu, dong_gu, apt_name, kind);
			} catch (ClassNotFoundException | NamingException e1) {
				e1.printStackTrace();
			}
  //   아파트 평형정보 가져오기		
		List<AptScale> scales = null;  //객체
		try {
			scales = service.getAptScale(city_do, city_gu, dong_gu, apt_name, kind);
		} catch (ClassNotFoundException | NamingException e1) {
			e1.printStackTrace();
		}
		
   //      아파트 세부정보 마스터 
		List<AptInfoDetail> info = null;
		String[] addr = new String[2];
		if(address_road.equals("%") || address_jibun.equals("%"))  // 주소정보가 없으면 가져오기
			try {
				addr = service.getAddress(city_do, city_gu, dong_gu, apt_name);
				address_road = addr[0];
				address_jibun = addr[1];
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		
		try {
			info = service.getAptInfoDetail  //아파트 세부정보 가져오기
					(city_do, city_gu, dong_gu, apt_name, construction_year, 
					  address_road, address_jibun);
		} catch (ClassNotFoundException | NamingException e1) {
			e1.printStackTrace();
		}	
		
		request.setAttribute("list", list);
		request.setAttribute("info", info);
		request.setAttribute("scales", scales);
		request.setAttribute("kind2", kind);
		request.setAttribute("city_do2", city_do);
		request.setAttribute("city_gu2", city_gu);
		request.setAttribute("dong_gu2", dong_gu);
		request.setAttribute("apt_name2", apt_name);
		request.getRequestDispatcher("/WEB-INF/view/aptinfo/aptinfo_detail.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
