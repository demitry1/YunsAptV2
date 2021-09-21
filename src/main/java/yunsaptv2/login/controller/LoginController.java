package yunsaptv2.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/login")
public class LoginController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();  //���ǰ�������
		
		String cmd = request.getParameter("cmd");
		
		if(cmd == null) cmd = "";
		
		if(cmd.equals("login") || cmd.equals("")) {  //�α���
			request.getRequestDispatcher("/WEB-INF/view/login/login.jsp").forward(request, response);
		}else if(cmd.equals("logout")) {   //�α׾ƿ�
			session.invalidate();  //��������
			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
		}
	}
	
	
}
