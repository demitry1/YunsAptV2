<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>

function sub(){
 var x = f.sel1.value;
 $.ajax({  
  type: "POST", 
  url: "selectajaxtest1.jsp",  
  data: "region="+x,   //&a=xxx 식으로 뒤에 더 붙이면 됨
  success: result    //function result 를 의미함
   }
 );

}

function result(msg){
 //sub()가 실행되면 결과 값을 가지고 와서 action 을 취해주는 callback 함수
 var sel  =  document.f.sel2;
 $("#sp1").html(msg); //innerHTML 을 이런 방식으로 사용함
 //id 는 $("#id")   name 의 경우 $("name") 으로 접근함
}

</script>

<script src="../js/jquery-3.6.0.min.js"></script>

</head>
<body>
<form name="f">
 <select name="sel1" onchange="sub();">
  <option value="">선택하세요</option>
  <option value="seoul">서울</option>
  <option value="kyung">경기</option>
 </select>
 <span id="sp1">
  <select name="sel2">
   <option value="">선택하세요</option>
  </select>
 </span>
</form>
</body>
</html>