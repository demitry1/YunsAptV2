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

@MultipartConfig( // 파일 보내고받을때 사용 form에서 enctype="multipart/form-data" 사용할떄
		fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024 * 50 * 5)
@WebServlet("/notice/insert")
public class InsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/view/notice/notice_insert.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		String writer_id = request.getParameter("writer_id");
		String content = request.getParameter("content");

		// 첨부파일 하나 받을때
//		Part filePart = request.getPart("file");
//		String fileName = filePart.getSubmittedFileName();
//		InputStream fis = filePart.getInputStream();
//		String realPath = request.getServletContext().getRealPath("/upload");
//		String filePath = realPath + File.separator + fileName;
//		FileOutputStream fos = new FileOutputStream(filePath);
//				
//		byte[] buf = new byte[1024];
//		int size = 0;
//		while((size=fis.read(buf)) != -1) {
//			fos.write(buf, 0, size);
//		}
//		fis.close();
//		fos.close();

// 첨부파일 두개 이상 받을때 
		Collection<Part> parts = request.getParts();
		StringBuilder builder = new StringBuilder();
		
		for(Part p : parts) {
			if(!p.getName().equals("file"))
				continue;
			if(p.getSize() == 0) continue;
			Part filePart = p;
			String fileName = filePart.getSubmittedFileName();
			builder.append(fileName);
			builder.append(",");
			
			InputStream fis = filePart.getInputStream();
			String realPath = request.getServletContext().getRealPath("/upload");
			File path = new File(realPath);
			if (!path.exists())  // 디렉토리가 존재하는지 확인하고 없으면
				path.mkdirs();  // 생성
						
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
		notice.setContent(content);
		notice.setWriter_id(writer_id);
		notice.setFiles(builder.toString());

		NoticeService service = new NoticeService();
		try {
			service.insertNotice(notice);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		response.sendRedirect("../notice/notice");

	}
}
