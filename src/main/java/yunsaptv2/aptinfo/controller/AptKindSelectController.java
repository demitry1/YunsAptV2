package yunsaptv2.aptinfo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import yunsaptv2.aptinfo.entity.AptScale;
import yunsaptv2.aptinfo.service.AptDetailService;

@WebServlet("/aptinfo/aptinfo_kindselect")
public class AptKindSelectController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		AptDetailService service = new AptDetailService();
		// 파라메타 값 받기
		String city_do_ = request.getParameter("city_do2");
		String city_gu_ = request.getParameter("city_gu2");
		String dong_gu_ = request.getParameter("dong_gu2");
		String apt_name_ = request.getParameter("apt_name2");				
		String kind_ = request.getParameter("kind2");							
		
		// 변수 초기화
		String city_do = "%";
		String city_gu = "%";
		String dong_gu = "%";
		String apt_name = "%";
		String kind = "%";

		// 널값 체크 및 값 설정
		if(city_do_ != null && !city_do_.equals(""))
			city_do = city_do_;
		if(city_gu_ != null && !city_gu_.equals(""))
			city_gu = city_gu_;
		if(dong_gu_ != null && !dong_gu_.equals(""))
			dong_gu = dong_gu_;
		if(apt_name_ != null && !apt_name_.equals(""))
			apt_name = apt_name_;
		if(kind_ != null && !kind_.equals("")) {
			kind = kind_;
		}else {
			kind = "매매";
		}
						
  //   아파트 평형정보 가져오기		
		List<AptScale> scales = null;  //객체
		try {
			scales = service.getAptScale(city_do, city_gu, dong_gu, apt_name, kind);
		} catch (ClassNotFoundException | NamingException e1) {
			e1.printStackTrace();
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(scales);
	    out.print(json);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
