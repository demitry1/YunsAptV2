/* global bootstrap: false */
(function () {
  'use strict'
  var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
  tooltipTriggerList.forEach(function (tooltipTriggerEl) {
    new bootstrap.Tooltip(tooltipTriggerEl)
  })
})()

//			$(function(){   //문서로드 이벤트
//				$("input:checkbox[name=sel-id]").length : 전체갯수
//        $("input:checkbox[name=sel-id]:checked").length // 선택된 갯수
//        $("input[name=sel-id]:checkbox").prop("checked", true); // 전체체크
       // 전체 체크 순회
//        $("input:checkbox[name=sel-id]").each(function() {
//           this.checked = true;
//        });         
       // 체크여부 확인
//          if($("input:checkbox[name=sel-id]").is(":checked") == true) {
            //작업
//          }			  
//			})
$(function(){
// 공지사항 리스트 화면에서 삭제버튼 눌렀을 때 체크 펑션
		$('#del').click(function() {
			var chk_cnt = $("input:checkbox[name=sel-id]:checked").length;
			if(chk_cnt == 0){
				alert('삭제할 데이터를 선택하세요');
				return false;
			};
			if(chk_cnt > 0){
				var con = confirm('정말로 삭제하시겠습니까?');
				if(con){
					}
				else{
					return false;
				};
			};
		});
		
		$('#nddel').click(function() {
			var con = confirm('정말로 삭제하시겠습니까?');
			if(con){
				}
			else{	
				return false;
			};
		});		
		

});

// datapicker 세팅
$(function() {
    //모든 datepicker에 대한 공통 옵션 설정
    $.datepicker.setDefaults({
        dateFormat: 'yy-mm-dd' //Input Display Format 변경
        ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
        ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
        ,changeYear: true //콤보박스에서 년 선택 가능
        ,changeMonth: true //콤보박스에서 월 선택 가능                
/*	    ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
        ,buttonImage: "../img/calendar5.svg" //버튼 이미지 경로
        ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함*/
        ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
        ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
        ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
        ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
        ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
/*           ,minDate: "-1M" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
        ,maxDate: "+1M" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)  */                   
    });

    //input을 datepicker로 선언
    $("#datepicker").datepicker();                    
    $("#datepicker2").datepicker();
    
    //From의 초기값을 오늘 날짜로 설정
    $('#datepicker').datepicker('setDate', '-1Y'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
    //To의 초기값을 내일로 설정
    $('#datepicker2').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)

		$(".ui-datepicker-trigger").css("width","25px");
		$(".ui-datepicker-trigger").css("margin-left","2px");
});

// 시 구 SELECT AJAX
function select_do(){
 var city_do = f.city_do.value;
 $.ajax({  
  type: "POST", 
  url: "/RegionSelect",  
  data: "city_do="+city_do,   //&a=xxx 식으로 뒤에 더 붙이면 됨
  success: result_do    //function result 를 의미함
   }
 );
}

//select_do()가 실행되면 결과 값을 가지고 와서 action 을 취해주는 callback 함수
function result_do(msg){
	var region = msg.split('^');
	$('#city_gu').children('option:not(:first)').remove();

	for(var i=0; i<region.length; i++){
		if(region[i] != ""){
			$("#city_gu").append('<option value="' + region[i] + '">' + region[i] + '</option');
		}
	}
}

// 구 군 seleclt ajax
function select_gu(){
 var city_do = f.city_do.value;
 var city_gu = f.city_gu.value;
 $.ajax({  
  type: "POST", 
  url: "/RegionSelect",  
  data: "city_do="+city_do+"&city_gu="+city_gu,   //&a=xxx 식으로 뒤에 더 붙이면 됨
  success: result_gu    //function result 를 의미함
   }
 );
}

function result_gu(msg){
	var region = msg.split('^');
	$('#dong_gu').children('option:not(:first)').remove();

	for(var i=0; i<region.length; i++){
		if(region[i] != ""){
			$("#dong_gu").append('<option value="' + region[i] + '">' + region[i] + '</option');
		}
	}
}

// 동 select ajax
function select_dong(){
 var city_do = f.city_do.value;
 var city_gu = f.city_gu.value;
 var dong_gu = f.dong_gu.value;
 $.ajax({  
  type: "POST", 
  url: "/RegionSelect",  
  data: "city_do="+city_do+"&city_gu="+city_gu+"&dong_gu="+dong_gu,   //&a=xxx 식으로 뒤에 더 붙이면 됨
  success: result_dong    //function result 를 의미함
   }
 );
}

function result_dong(msg){
	var region = msg.split('^');
	$('#apt_name').children('option:not(:first)').remove();

	for(var i=0; i<region.length; i++){
		console.log(region[i]);
		if(region[i] != ""){
			$("#apt_name").append('<option value="' + region[i] + '">' + region[i] + '</option');
		}
	}
	$('#apt_name').selectpicker('refresh');
}
//select_do()가 실행되면 결과 값을 가지고 와서 action 을 취해주는 callback 함수
// var sel  =  document.f.sel2;
// $("#sp1").html(msg); //innerHTML 을 이런 방식으로 사용함
//id 는 $("#id")   name 의 경우 $("name") 으로 접근함

