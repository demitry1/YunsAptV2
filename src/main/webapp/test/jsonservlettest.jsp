<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type='submit' id='checkJson' value='OK'>

<div id='output'></div>
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
<script>
$(function() {
    $('#checkJson').on('click', () => {
        const jsonInfo = '{"name":"루루","age":25,"nick":"이땅콩"}';
        $.ajax({
            type:'post',
            url:'/jsonServletTest',
            success:function (data, textStatus) {
                console.log('data')
                console.log(data)
                const jsonInfo = JSON.parse(data)
                let memberInfo = '회원 정보 <hr>'

                for(const i in jsonInfo.members) {
                    memberInfo += '이름 : ' + jsonInfo.members[i].name + '<br>'
                    memberInfo += '나이 : ' + jsonInfo.members[i].age + '<br>'
                    memberInfo += '별명 : ' + jsonInfo.members[i].nick + '<hr>'

                    $('#output').html(memberInfo)
                }
            },
            error : function(data,textStatus) {
                console.log('error!!')
            }
        }) //ajax
    }) //checkJson
})
</script>
</body>
</html>