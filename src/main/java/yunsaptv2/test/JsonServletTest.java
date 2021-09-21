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

        //배열을 저장할 totalObject
//        JSONObject totalObject = new JSONObject();

        //memberInfo JSON 객체를 저장할 배열
//        JSONArray memberArray = new JSONArray();
//        JSONObject memberInfo = new JSONObject();

        //{"name":"김네임", "age":25, "nick":"하하"}
//        memberInfo.put("name", "김네임");
//        memberInfo.put("age", 25);
//        memberInfo.put("nick", "하하");

        //[{"name":"김네임", "age":25, "nick":"하하"}]
//        memberArray.add(memberInfo);

//        memberInfo = new JSONObject();
//        memberInfo.put("name", "김이상");
//        memberInfo.put("age", 26);
//        memberInfo.put("nick", "호호");        
//        memberArray.add(memberInfo);
        
        //"members":[{"name":"김네임", "age":25, "nick":"하하"}]
//        
//        totalObject.put("members", memberArray);
//         
//        String jsonInfo = totalObject.toJSONString();
//
//        out.print(jsonInfo);
    }
}