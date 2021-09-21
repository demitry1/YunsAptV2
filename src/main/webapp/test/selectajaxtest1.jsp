<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String region  =  request.getParameter("region");

out.println("<select name='sel2'>");
if(region.equals("seoul")){
 out.println("<option value=''>선택하세요</option> "); 
 out.println("<option value='seocho'>서초</option> "); 
 out.println("<option value='gangnam'>강남</option> "); 
}else{
 out.println("<option value=''>선택하세요</option> "); 
 out.println("<option value='guri'>구리</option> "); 
 out.println("<option value='hanam'>하남</option> "); 
}
out.println("</select>");

%>
