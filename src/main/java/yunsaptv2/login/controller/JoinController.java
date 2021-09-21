package yunsaptv2.login.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yunsaptv2.login.entity.Member;
import yunsaptv2.login.service.LoginService;

@WebServlet("/login/join")
public class JoinController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userid_ = request.getParameter("userid");
		String password_ = request.getParameter("password");
		String username_ = request.getParameter("username");
		String email_ = request.getParameter("email");
		String email2_ = request.getParameter("email2");
		String cmd_ = request.getParameter("cmd");
		
		
		String userid = "";
		String password = "";
		String username = "";
		String email = "";
		String email2 = "";
		String cmd = "";

		if(userid_ != null && !userid_.equals(""))
			userid	= userid_;
		if(password_ != null && !password_.equals(""))
			password	= password_;
		if(username_ != null && !username_.equals(""))
			username	= username_;
		if(email_ != null && !email_.equals(""))
			email	= email_;
		if(email2_ != null && !email2_.equals(""))
			email2	= email2_;
		if(cmd_ != null && !cmd_.equals(""))
			cmd	= cmd_;
		
		email = email + '@' + email2;
		
		Member member = new Member(); 
		member.setId(userid);
		member.setPassword(password);
		member.setName(username);
		member.setEmail(email);
		
		LoginService service = new LoginService();
		int i = 0;
		if(cmd.equals("joinsave")) {   //회원가입
			try {
				i = service.saveJoin(member);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
			
			if(i>0) {
				response.setContentType("text/html; charset=UTF-8"); 
				PrintWriter out = response.getWriter(); 
				out.println("<script>alert('가입이 완료되었습니다. 로그인 해주세요'); "
	  					   + "location.href='/login/login';</script>"); 
				out.flush();
			}
		}else if(cmd.equals("joincancel")) {
			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);			
		}
	
	}

}
