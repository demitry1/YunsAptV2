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
		// �Ķ��Ÿ �� �ޱ�
		String address_road_ = request.getParameter("selRoadAddress");
		String address_jibun_ = request.getParameter("selJibunAddress");
		
		// ���� �ʱ�ȭ
		String address_road = "%";
		String address_jibun = "%";


		// �ΰ� üũ �� �� ����
		if(address_road_ != null && !address_road_.equals(""))
			address_road = address_road_;
		if(address_jibun_ != null && !address_jibun_.equals(""))
			address_jibun = address_jibun_;
		
  //     �ּҷ� ����Ʈ �������� ��������		
		List<AptInfoDetail> list = null;  //��ü
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
