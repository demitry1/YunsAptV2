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

import yunsaptv2.aptinfo.entity.AptDetail;
import yunsaptv2.aptinfo.service.AptSelectService;

@WebServlet("/aptinfo/aptinfo_select")
public class AptSelectController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		AptSelectService service = new AptSelectService();
		// 파라메타 값 받기
		String city_do_ = request.getParameter("city_do2");
		String city_gu_ = request.getParameter("city_gu2");
		String dong_gu_ = request.getParameter("dong_gu2");
		String apt_name_ = request.getParameter("apt_name2");				
		String scale_ = request.getParameter("scale2");				
		String kind_ = request.getParameter("kind2");				
		
		// 변수 초기화
		String city_do = "%";
		String city_gu = "%";
		String dong_gu = "%";
		String apt_name = "%";
		String kind = "%";
		int scale = 0;

		// 널값 체크 및 값 설정
		if(city_do_ != null && !city_do_.equals(""))
			city_do = city_do_;
		if(city_gu_ != null && !city_gu_.equals(""))
			city_gu = city_gu_;
		if(dong_gu_ != null && !dong_gu_.equals(""))
			dong_gu = dong_gu_;
		if(apt_name_ != null && !apt_name_.equals(""))
			apt_name = apt_name_;
		if(scale_ != null && !scale_.equals(""))
			if(scale_.equals("전체")) {				
				scale = 0;
			}else { 
				scale = Integer.parseInt(scale_);
			}
		if(kind_ != null && !kind_.equals("")) {
			kind = kind_;
		}else {
			kind = "매매";
		}
		
  //      실거래가 마스터 리스트가져오기		
		List<AptDetail> list = null;  //객체
		try {
			list = service.getAptSelectList
					(city_do, city_gu, dong_gu, apt_name, scale, kind);
		} catch (ClassNotFoundException | NamingException e1) {
			e1.printStackTrace();
		}

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(list);
//		 String json = new Gson().toJson(list);
		 out.print(json);
		  
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
