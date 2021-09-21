<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script type="text/javascript" src = "../js/jquery-3.6.0.min.js"></script>

<script>
$(document).ready(function(){
    $('#ajaxConGetButton').click(function(){
        AjaxConGet();
    })
    
    $('#ajaxConPostButton').click(function(){
        AjaxConPost();
    })
    
})
 
function AjaxConGet(){
    var url = "http://localhost:8080/ajaxCon";
    $.ajax({
        type:"GET",
        url:url,
        dataType:"html",
        data:{
            name : $('#ajaxConName').val(),
            age : $('#ajaxConAge').val()
        },
        success : function(data){
            alert('ajax GET 통신 성공');
            var $div = $('');
            var text = document.createTextNode(data);
            alert(data);
            $div.append(data);
            $div.appendTo($('#myDiv'))
        
        },
        error : function(request,status,error){
            alert('code:'+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error); //에러 상태에 대한 세부사항 출력
            alert(e);
        }
    })
    
}
 
function AjaxConPost(){
    var url = "http://localhost:8080/ajaxCon";
    $.ajax({
        type:"POST",
        url:url,
        dataType:"html",
        data:{
            name : $('#ajaxConName').val(),
            age : $('#ajaxConAge').val()
        },
        success : function(data){
            alert('ajax POST 통신 성공');
            var $div = $('');
            var text = document.createTextNode(data);
            $div.append(data);
            $div.appendTo($('#myDiv'))
        },
        error : function(request,status,error){
            alert('code:'+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error); //에러 상태에 대한 세부사항 출력
            alert(e);
        }
    })
    
}

</script>

</head>
<body>
    <h2>Ajax Communication</h2>
    이름 : <input type="text" id = "ajaxConName"/>
    나이 : <input type="text" id = "ajaxConAge"/>
    <br>
    <input type="button" id = "ajaxConGetButton" value ="Get통신">
    <input type="button" id = "ajaxConPostButton" value ="Post 통신">
    <div id="myDiv"></div>
</body>
</html>
