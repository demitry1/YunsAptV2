package yunsaptv2.notice.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import yunsaptv2.notice.entity.Notice;
import yunsaptv2.notice.service.NoticeService;

@MultipartConfig( // ���� ����������� ��� form���� enctype="multipart/form-data" ����ҋ�
		fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024 * 50 * 5)
@WebServlet("/notice/modify")
public class ModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		NoticeService service = new NoticeService();
		Notice notice = new Notice();
		notice = null;
		try {
			notice = service.getNotice(id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("n", notice);
		request.getRequestDispatcher("/WEB-INF/view/notice/notice_modify.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String writer_id = request.getParameter("writer_id");
		String content = request.getParameter("content");
		String p_ = request.getParameter("p");
		String q = request.getParameter("q");
		String f = request.getParameter("f");
		String oldFiles = request.getParameter("oldFiles");
    	String[] chkAttDel = request.getParameterValues("chkAttDel");
    	String[] oldFile = request.getParameterValues("oldFile");
    		
    	// �������� ������ üũ������ ���ϻ����ϰ� db�� ������ ����
    	if(chkAttDel != null) {
	    	for(int i=0; i < chkAttDel.length; i++) {
	    		if(!chkAttDel[i].equals("")) {
	    			String realPath = request.getServletContext().getRealPath("/upload");
	    			String filePath = realPath + File.separator + chkAttDel[i];
	    			File path = new File(filePath);
	    			path.delete();
	    			oldFiles = oldFiles.replace(chkAttDel[i], "");
	    			oldFiles = oldFiles.replace(",", "");
	    			
	    			for(int j=0; j < oldFile.length; j++) {
	    				if (oldFile[j].equals(chkAttDel[i])){
	    					oldFile[j] = "";
	    				}
	    			}
	    		}
	    	}
    	}	

// ÷������ �ΰ� �̻� ������ 
		Collection<Part> parts = request.getParts();
				
		StringBuilder builder = new StringBuilder();
		int matchCnt = 0; 
		
		for(Part p : parts) {
			if(!p.getName().equals("newFile")) {
				continue;
			}
			matchCnt += 1;   // part���� ÷������ ������ ��� ī��Ʈ
			if(p.getSize() == 0) {
				if(oldFile[matchCnt-1] != "") {
					builder.append(oldFile[matchCnt-1]);
					builder.append(",");
				}
				continue;
			}
			Part filePart = p;
			String fileName = filePart.getSubmittedFileName();
			builder.append(fileName);
			builder.append(",");
//          ������ ��ϵ� ÷�������� �־����� Ȯ���ϰ� ������ �������� �ʾ����� ����..			
			if(oldFile[matchCnt-1] != "") {
    			String realPath = request.getServletContext().getRealPath("/upload");
    			String filePath = realPath + File.separator + oldFile[matchCnt-1];
    			File path = new File(filePath);
    			path.delete();
    			oldFile[matchCnt-1] = "";
			}
			
			InputStream fis = filePart.getInputStream();
			String realPath = request.getServletContext().getRealPath("/upload");
			File path = new File(realPath);
			if (!path.exists())  // ���丮�� �����ϴ��� Ȯ���ϰ� ������
				path.mkdirs();  // ����
						
			String filePath = realPath + File.separator + fileName;
			FileOutputStream fos = new FileOutputStream(filePath);
					
			byte[] buf = new byte[1024];
			int size = 0;
			while((size=fis.read(buf)) != -1) 
				fos.write(buf, 0, size);
		
			fis.close();
			fos.close();
		}
		if(builder.length() > 0)
			builder.delete(builder.length()-1, builder.length());
				
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setId(id);
		notice.setContent(content);
		notice.setWriter_id(writer_id);
//      ÷�������� ���� �Է������� db�� �����Ϸ� ����,  �Է¾������� db�� ���������ͷ� ����		
		if(builder.length() > 0) {
			notice.setFiles(builder.toString());
		}else {
			notice.setFiles(oldFiles);			
		}

		NoticeService service = new NoticeService();
		try {
			service.updateNotice(notice);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		response.sendRedirect("../notice/notice?p="+p_+"&q="+q+"&f="+f+"");

	}
}
