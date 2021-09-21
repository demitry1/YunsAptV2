package yunsaptv2.notice.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yunsaptv2.notice.entity.NoticeView;
import yunsaptv2.notice.service.NoticeService;


@WebServlet("/notice/notice")
public class ListController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String[] selIds = request.getParameterValues("sel-id");
    	String cmd = request.getParameter("cmd");
    	
    	NoticeService service = new NoticeService();
    	
    	int[] ids1 = new int[selIds.length];
    	switch(cmd){
    	case "delete":  // ����
    		for(int i=0 ; i<selIds.length; i++) {
    			ids1[i] = Integer.parseInt(selIds[i]);
    		}    		
    		try {
    			String[] delFiles = service.delFileList(ids1);
    			// ÷�������� ������ ÷�ε� ���� ����
				if(delFiles.length > 0) {
					for(int i=0; i<delFiles.length; i++) {
		    			String realPath = request.getServletContext().getRealPath("/upload");
		    			String filePath = realPath + File.separator + delFiles[i];
		    			File path = new File(filePath);
		    			path.delete();
					}
				}
    	        // DB������ ����		
				service.delNoticeAll(ids1);   
			} catch (NamingException | ClassNotFoundException e) {
				e.printStackTrace();
			}
    		break; 
    	case "insert":   //�۾���
    		break;    		
    	}
    	response.sendRedirect("../notice/notice");
    	
    }
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // list?f=title&q=a
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_  = request.getParameter("p");
		String field = "title";
		String query = "";
				
		int page = 1;
		if(field_ != null && !field_.equals(""))
			field = field_;
		if(query_ != null && !query_.equals(""))
			query = query_;
		if(page_ != null && !page_.equals(""))
			page = Integer.parseInt(page_) ;
		
		NoticeService service = new NoticeService();
//      �������� ����Ʈ��������		
		List<NoticeView> list = null;
		try {
			list = service.getNoticeList(field, query, page);
		} catch (ClassNotFoundException | NamingException e1) {
			e1.printStackTrace();
		}
//      �������� �� ī��Ʈ ��������	
		int[] count = new int[2];
		
		try {
			count = service.getNoticeCount(field, query);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
				
		request.setAttribute("list", list);
		request.setAttribute("count", count[0]);
		request.setAttribute("total_count", count[1]);
		request.getRequestDispatcher("/WEB-INF/view/notice/notice.jsp").forward(request, response);
		
	}
}