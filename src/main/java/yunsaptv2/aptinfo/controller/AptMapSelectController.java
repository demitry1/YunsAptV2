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

import yunsaptv2.aptinfo.entity.AptInfoDetail;
import yunsaptv2.aptinfo.service.AptInfoService;

@WebServlet("/aptinfo/aptinfo_mapselect")
public class AptMapSelectController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		AptInfoService service = new AptInfoService();
		// 파라메타 값 받기
		String address_road_ = request.getParameter("selRoadAddress");
		String address_jibun_ = request.getParameter("selJibunAddress");
		
		// 변수 초기화
		String address_road = "%";
		String address_jibun = "%";


		// 널값 체크 및 값 설정
		if(address_road_ != null && !address_road_.equals(""))
			address_road = address_road_;
		if(address_jibun_ != null && !address_jibun_.equals(""))
			address_jibun = address_jibun_;
		
  //     주소로 아파트 세부정보 가져오기		
		List<AptInfoDetail> list = null;  //객체
		try {
			list = service.getAptInfo
					(address_road, address_jibun);
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
