package yunsaptv2.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet("/ajaxCon")
public class Serv extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
     protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		 throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("application/x-json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String age = request.getParameter("age");    
        
        System.out.println("doget :"+name);
        System.out.println("deget :"+age);
            
        out.print("Get 통신 : 안녕 내 이름은 "+name+"이고 나이는 "+age+"란다"); //response    
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/x-json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String age = request.getParameter("age");    
        
        System.out.println("doget :"+name);
        System.out.println("deget :"+age);
            
        out.println("Post통신 : 안녕 내 이름은 "+name+"이고 나이는 "+age+"란다"); //response
    }
  }
        