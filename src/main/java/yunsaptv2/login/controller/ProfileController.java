package yunsaptv2.login.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import yunsaptv2.login.entity.Member;
import yunsaptv2.login.service.LoginService;

@WebServlet("/login/profile")
public class ProfileController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userid_ = request.getParameter("userid");
		String password_ = request.getParameter("password");
		String username_ = request.getParameter("username");
		String email_ = request.getParameter("email");
		String cmd_ = request.getParameter("cmd");

		String userid = "";
		String password = "";
		String username = "";
		String email = "";
		String cmd = "";
		
		if(userid_ != null && !userid_.equals(""))
			userid	= userid_;
		if(password_ != null && !password_.equals(""))
			password	= password_;
		if(username_ != null && !username_.equals(""))
			username	= username_;
		if(email_ != null && !email_.equals(""))
			email	= email_;
		if(cmd_ != null && !cmd_.equals(""))
			cmd	= cmd_;
		
		Member member = new Member();
		member.setId(userid);
		member.setPassword(password);
		member.setName(username);
		member.setEmail(email);
		
		LoginService service = new LoginService();
		int i = 0;
		if (cmd.equals("profilesave")) { //프로필 수정
			try {
				i = service.saveMember(member);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
			
			if(i>0) {
				HttpSession session = request.getSession();
				session.setAttribute("userid", userid);
				session.setAttribute("password", password);
				session.setAttribute("username", username);
				session.setAttribute("email", email);
				
				response.setContentType("text/html; charset=UTF-8"); 
				PrintWriter out = response.getWriter(); 
				out.println("<script>alert('회원정보가 수정되었습니다.');" + "location.href='/index';</script>");
				out.flush();
			}
		} else if (cmd.equals("display")) { //프로필 조회
			request.getRequestDispatcher("/WEB-INF/view/login/profile.jsp").forward(request, response);
		} else if (cmd.equals("profileconfirm")) { //확인
			response.sendRedirect("../index");
		}
	}

	
}
