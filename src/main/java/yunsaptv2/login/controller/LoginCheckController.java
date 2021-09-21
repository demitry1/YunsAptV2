package yunsaptv2.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import yunsaptv2.login.entity.Member;
import yunsaptv2.login.service.LoginService;

@WebServlet("/login/logincheck")
public class LoginCheckController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cmd_ = request.getParameter("cmd");
		String userid_ = request.getParameter("userid");
		String password_ = request.getParameter("password");

		String cmd = "";
		String userid = "";
		String password = "";

		if (cmd_ != null && !cmd_.equals(""))
			cmd = cmd_;
		if (userid_ != null && !userid_.equals(""))
			userid = userid_;
		if (password_ != null && !password_.equals(""))
			password = password_;

		LoginService service = new LoginService();
		List<Member> list = null;

		if (cmd.equals("join")) { //ȸ������
			request.getRequestDispatcher("/WEB-INF/view/login/join.jsp").forward(request, response);
		} else if (cmd.equals("forget")) { //��й�ȣ ã��
			request.getRequestDispatcher("/WEB-INF/view/error404.jsp").forward(request, response);
		} else if (cmd.equals("login")) { // �α���
			try {
				list = service.checkLogin(userid, password);
			} catch (NamingException e) {
				e.printStackTrace();
			}

			if (list.isEmpty()) { // ȸ�������� ��ġ���� ������
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println(
						"<script>alert('���̵� �Ǵ� ��й�ȣ�� �߸��Ǿ����ϴ�. �ٽ� �õ��ϼ���'); " + "location.href='/login/login';</script>");
				out.flush();
			} else { // �α��ο� ����������
				HttpSession session = request.getSession(); //���ǻ���
				session.setAttribute("userid", list.get(0).getId());
				session.setAttribute("password", list.get(0).getPassword());
				session.setAttribute("username", list.get(0).getName());
				session.setAttribute("regdate", list.get(0).getRegdate());
				session.setAttribute("email", list.get(0).getEmail());
				response.sendRedirect("../index");
			}
		}

	}

}
