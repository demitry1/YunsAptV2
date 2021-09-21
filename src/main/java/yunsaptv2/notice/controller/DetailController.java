package yunsaptv2.notice.controller;

import java.io.File;
import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yunsaptv2.notice.entity.Notice;
import yunsaptv2.notice.service.NoticeService;

@WebServlet("/notice/detail")
public class DetailController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("ndid"));
    	String cmd = request.getParameter("cmd");
	    String p = request.getParameter("p");
		String f = request.getParameter("f");
		String q = request.getParameter("q");
    	
    	NoticeService service = new NoticeService();
    	
    	int[] ids1 = new int[1];
    	int result = 0;
    	switch(cmd){
    	case "delete":  // 한건 삭제
    		ids1[0] = id;
    		try {
    			String[] delFiles = service.delFileList(ids1);
    			// 첨부파일이 있으면 첨부도 같이 삭제
				if(delFiles.length > 0) {
					for(int i=0; i<delFiles.length; i++) {
		    			String realPath = request.getServletContext().getRealPath("/upload");
		    			String filePath = realPath + File.separator + delFiles[i];
		    			File path = new File(filePath);
		    			path.delete();
					}
				}
    			// DB 삭제
				result = service.delNoticeAll(ids1);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
    		break; 
    	}
    	String url = "../notice/notice?p="+p+"&q="+q+"&f="+f;
    	if(result > 0)   // 삭제에 성공했으면
    		response.sendRedirect(url);    	
    	
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int id = Integer.parseInt(request.getParameter("id"));
	    int hitCnt = Integer.parseInt(request.getParameter("h"));
	    String page_ = request.getParameter("p");
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
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
	    Notice notice = null;
		try {
			notice = service.getNotice(id, hitCnt);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	    
	    request.setAttribute("n", notice);
	    request.setAttribute("p", page);
	    request.setAttribute("q", query);
	    request.setAttribute("f", field);
			
		request.getRequestDispatcher("/WEB-INF/view/notice/notice_detail.jsp").forward(request, response);
		

	}
}
