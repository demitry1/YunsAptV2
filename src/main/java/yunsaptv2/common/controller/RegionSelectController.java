package yunsaptv2.common.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yunsaptv2.common.entity.SelectRegion;
import yunsaptv2.common.service.RegionSelectService;

@WebServlet("/RegionSelect")
public class RegionSelectController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();

		String city_do = request.getParameter("city_do");
		String city_gu = request.getParameter("city_gu");
		String dong_gu = request.getParameter("dong_gu");

		RegionSelectService service = new RegionSelectService();

		List<SelectRegion> list = null;

		try {
			list = service.getRegionList(city_do, city_gu, dong_gu);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}

		
		  for(int i=0; i < list.size(); i++) 
		  	 out.print(list.get(i));
			/*
			 * System.out.println(list.get(i)); }
			 */ 

		
		  //�迭�� ������ totalObject JSONObject totalObject = new JSONObject();
		  
		  //memberInfo JSON ��ü�� ������ �迭 JSONArray memberArray = new JSONArray();
			/*
			 * JSONObject memberInfo = new JSONObject();
			 * 
			 * memberInfo.put("name", "�����"); memberArray.add(memberInfo);
			 * 
			 * memberInfo = new JSONObject(); memberInfo.put("name", "���̻�");
			 * memberArray.add(memberInfo);
			 * 
			 * //"members":[{"name":"�����", "age":25, "nick":"����"}]
			 * totalObject.put("members", memberArray);
			 * 
			 * String jsonInfo = totalObject.toJSONString();
			 * 
			 * out.print(jsonInfo);
			 */
		 
	}

}