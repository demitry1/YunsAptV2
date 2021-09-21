package yunsaptv2.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/jsonServletTest")
public class JsonServletTest extends HttpServlet {
 
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request,response);
    }

    protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

//        PrintWriter out = response.getWriter();

        //�迭�� ������ totalObject
//        JSONObject totalObject = new JSONObject();

        //memberInfo JSON ��ü�� ������ �迭
//        JSONArray memberArray = new JSONArray();
//        JSONObject memberInfo = new JSONObject();

        //{"name":"�����", "age":25, "nick":"����"}
//        memberInfo.put("name", "�����");
//        memberInfo.put("age", 25);
//        memberInfo.put("nick", "����");

        //[{"name":"�����", "age":25, "nick":"����"}]
//        memberArray.add(memberInfo);

//        memberInfo = new JSONObject();
//        memberInfo.put("name", "���̻�");
//        memberInfo.put("age", 26);
//        memberInfo.put("nick", "ȣȣ");        
//        memberArray.add(memberInfo);
        
        //"members":[{"name":"�����", "age":25, "nick":"����"}]
//        
//        totalObject.put("members", memberArray);
//         
//        String jsonInfo = totalObject.toJSONString();
//
//        out.print(jsonInfo);
    }
}