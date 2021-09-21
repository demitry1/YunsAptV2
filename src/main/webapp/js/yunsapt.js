/* global bootstrap: false */

(function () {
  'use strict'
  var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
  tooltipTriggerList.forEach(function (tooltipTriggerEl) {
    new bootstrap.Tooltip(tooltipTriggerEl)
  })
})()

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
// 공지사항 상세조회에서 삭제버튼 눌렸을때 		
		$('#nddel').click(function() {
			var con = confirm('정말로 삭제하시겠습니까?');
			if(con){
				}
			else{	
				return false;
			};
		});		

// 가입하기 버튼을 눌렀을때 패스워드 값 체크 		
		$('#joinsave, #profilesave').click(function() {
			var pass1 = $('input[name=password]').val();
			var pass2 = $('input[name=password2]').val();
			if(pass1 != pass2){				
				alert('패스워드가 일치하지 않습니다');
				return false;
			};
		});				
		
/*// 실거래가 조회내역에서 아파트명을 클릭했을때 해당 아파트를 검색조건으로 넣어주도록 한다. 
		$('.r_apt_name').on('click',(function() {
      var checkBtn = $(this);
    // checkBtn.parent() : checkBtn의 부모는 <td>이다.
    // checkBtn.parent().parent() : <td>의 부모이므로 <tr>이다.
	    var tr = checkBtn.parent();
	    var td = tr.children();
//	    console.log("클릭한 Row의 모든 데이터 : "+tr.text());
	    var s_city_do = td.eq(0).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
	    var s_city_gu = td.eq(1).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
	    var s_dogn_gu = td.eq(2).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
	    var s_apt_name = td.eq(3).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
	    var s_scale = td.eq(6).text();  //해당 tr에서 가지고 오고자하는 td열의 위치

			if(s_scale > 40){
				s_scale = "2";
			}else if(s_scale >= 30 && s_scale <= 39 ){
				s_scale = "3";				
			}else if(s_scale >= 20 && s_scale <= 29 ){
				s_scale = "4";				
			}else if(s_scale < 20 ){
				s_scale = "5";				
			}

      $("#city_do").append('<option value="'+s_city_do+'">'+s_city_do+'</option>');
      $("#city_gu").append('<option value="'+s_city_gu+'">'+s_city_gu+'</option>');
      $("#dong_gu").append('<option value="'+s_dogn_gu+'">'+s_dogn_gu+'</option>');
      $("#scale").append('<option value="'+s_scale+'">'+s_scale+'</option>');
      $("#apt_name").append('<option value="'+s_apt_name+'">'+s_apt_name+'</option>');
			$("#city_do").val(s_city_do).prop("selected", true);	
			$("#city_gu").val(s_city_gu).prop("selected", true);	
			$("#dong_gu").val(s_dogn_gu).prop("selected", true);	
			$("#scale").val(s_scale).prop("selected", true);	
			$("#apt_name").val(s_apt_name).prop("selected", true);	
			
			$("#f").attr("action","../realprice/realprice").submit();
		})
	);*/

// 실거래가 조회내역에서 아파트명을 클릭했을때 해당 아파트 상세정보 화면으로 이동한다. 
		$('.r_apt_name').on('click',(function() {
      var checkBtn = $(this);
	    var tr = checkBtn.parent();
	    var td = tr.children();
	    var s_city_do = td.eq(0).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
	    var s_city_gu = td.eq(1).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
	    var s_dong_gu = td.eq(2).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
	    var s_apt_name = td.eq(3).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
	    var s_construction_year = td.eq(9).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
//	    var s_scale = td.eq(6).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
      var link = "../aptinfo/aptinfo_detail?"+"city_do2="+s_city_do+"&city_gu2="+s_city_gu+"&dong_gu2="+s_dong_gu+"&apt_name2="+s_apt_name+"&construction_year2="+s_construction_year+"&kind2=매매";   	
			$(location).attr("href", link); 
//			$("#aptform").attr("action","../aptinfo/aptinfo_detail").submit();			
		})
	);	
	
	
/*// 실거래가 조회내역에서 아파트명을 클릭했을때 해당 아파트를 검색조건으로 넣어주도록 한다. 
		$('.r_apt_namerent').on('click',(function() {
      var checkBtn = $(this);
	    var tr = checkBtn.parent();
	    var td = tr.children();
	    var s_city_do = td.eq(0).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
	    var s_city_gu = td.eq(1).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
	    var s_dogn_gu = td.eq(2).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
	    var s_apt_name = td.eq(3).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
	    var s_kind = td.eq(4).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
	    var s_scale = td.eq(7).text();  //해당 tr에서 가지고 오고자하는 td열의 위치

			if(s_scale > "40"){
				s_scale = "2";
			}else if(s_scale >= "30" && s_scale <= "39" ){
				s_scale = "3";				
			}else if(s_scale >= "20" && s_scale <= "29" ){
				s_scale = "4";				
			}else if(s_scale < "20" ){
				s_scale = "5";				
			}
      $("#city_do").append('<option value="'+s_city_do+'">'+s_city_do+'</option>');
      $("#city_gu").append('<option value="'+s_city_gu+'">'+s_city_gu+'</option>');
      $("#dong_gu").append('<option value="'+s_dogn_gu+'">'+s_dogn_gu+'</option>');
      $("#scale").append('<option value="'+s_kind+'">'+s_kind+'</option>');
      $("#scale").append('<option value="'+s_scale+'">'+s_scale+'</option>');
      $("#apt_name").append('<option value="'+s_apt_name+'">'+s_apt_name+'</option>');
			$("#city_do").val(s_city_do).prop("selected", true);	
			$("#city_gu").val(s_city_gu).prop("selected", true);	
			$("#dong_gu").val(s_dogn_gu).prop("selected", true);	
			$("#scale").val(s_kind).prop("selected", true);	
			$("#scale").val(s_scale).prop("selected", true);	
			$("#apt_name").val(s_apt_name).prop("selected", true);	
			
			$("#f").attr("action","../realprice/realpricerent").submit();
		})
	);*/
// 실거래가 조회내역에서 아파트명을 클릭했을때 해당 아파트 상세정보 화면으로 이동한다. 
		$('.r_apt_namerent').on('click',(function() {
      var checkBtn = $(this);
	    var tr = checkBtn.parent();
	    var td = tr.children();
	    var s_city_do = td.eq(0).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
	    var s_city_gu = td.eq(1).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
	    var s_dong_gu = td.eq(2).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
	    var s_apt_name = td.eq(3).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
	    var s_kind = td.eq(4).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
	    var s_construction_year = td.eq(11).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
//	    var s_scale = td.eq(6).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
      var link = "../aptinfo/aptinfo_detail?"+"city_do2="+s_city_do+"&city_gu2="+s_city_gu+"&dong_gu2="+s_dong_gu+"&apt_name2="+s_apt_name+"&construction_year2="+s_construction_year+"&kind2="+s_kind;
			$(location).attr("href", link); 
//			$("#aptform").attr("action","../aptinfo/aptinfo_detail").submit();			
		})
	);	

	
// 지역별 거래현황에서 월별/연도별 조건에 따라 테이블의 헤드타이틀 변경하기 
			var s_datetype_area = $('#datetype_area').val();
	    var s_search_year_area = $('#search_year').val();
      var tmp_now = new Date();
      var tmp_year= tmp_now.getFullYear();
      if(s_search_year_area == null){
				s_search_year_area = tmp_year
			}
      if(s_datetype_area  == '2'){ 
	      $('#th01').text(s_search_year_area-11+'년');	
	      $('#th02').text(s_search_year_area-10+'년');	
	      $('#th03').text(s_search_year_area-9+'년');	
	      $('#th04').text(s_search_year_area-8+'년');	
	      $('#th05').text(s_search_year_area-7+'년');	
	      $('#th06').text(s_search_year_area-6+'년');	
	      $('#th07').text(s_search_year_area-5+'년');	
	      $('#th08').text(s_search_year_area-4+'년');	
	      $('#th09').text(s_search_year_area-3+'년');	
	      $('#th10').text(s_search_year_area-2+'년');	
	      $('#th11').text(s_search_year_area-1+'년');	
	      $('#th12').text(s_search_year_area+'년');	
      }else if(s_datetype_area  == '1'){
	      $('#th01').text('1월');	
	      $('#th02').text('2월');	
	      $('#th03').text('3월');	
	      $('#th04').text('4월');	
	      $('#th05').text('5월');	
	      $('#th06').text('6월');	
	      $('#th07').text('7월');	
	      $('#th08').text('8월');	
	      $('#th09').text('9월');	
	      $('#th10').text('10월');	
	      $('#th11').text('11월');	
	      $('#th12').text('12월');	
			}  

// 아파트 정보 조회내역에서 아파트명을 클릭했을때 해당 아파트를 상세 검색조건으로 넣어주도록 한다. 
		$('.i_apt_name').on('click',(function() {
      var checkBtn = $(this);
	    var tr = checkBtn.parent();
	    var td = tr.children();
	    var s_city_do = td.eq(0).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
	    var s_city_gu = td.eq(1).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
	    var s_dogn_gu = td.eq(2).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
	    var s_apt_name = td.eq(3).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
	    var s_construction_year = td.eq(4).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
	    var s_address_road = td.eq(5).text();  //해당 tr에서 가지고 오고자하는 td열의 위치
	    var s_address_jibun = td.eq(6).text();  //해당 tr에서 가지고 오고자하는 td열의 위치

			$("#city_do2").val(s_city_do);	
			$("#city_gu2").val(s_city_gu);	
			$("#dong_gu2").val(s_dogn_gu);	
			$("#apt_name2").val(s_apt_name);	
			$("#construction_year2").val(s_construction_year);	
			$("#address_road2").val(s_address_road);	
			$("#address_jibun2").val(s_address_jibun);	
			
			$("#aptform").attr("action","../aptinfo/aptinfo_detail").submit();
		})
	);


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
    
    var firstdate = $('#datepicker').datepicker({ dateFormat: 'yy-mm-dd' }).val();
 	  if(firstdate == ""){
   	 $('#datepicker').datepicker('setDate', '-3M'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)		
    } 
    var lastdate = $('#datepicker2').datepicker({ dateFormat: 'yy-mm-dd' }).val();
 	  if(lastdate == ""){
   	 $('#datepicker2').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)		
    } 

    //input을 datepicker로 선언
    $("#datepicker3").datepicker();                    
    $("#datepicker4").datepicker();
    
    var firstdate2 = $('#datepicker3').datepicker({ dateFormat: 'yy-mm-dd' }).val();
 	  if(firstdate2 == ""){
   	 $('#datepicker3').datepicker('setDate', '-1M'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)		
    } 
    var lastdate2 = $('#datepicker4').datepicker({ dateFormat: 'yy-mm-dd' }).val();
 	  if(lastdate2 == ""){
   	 $('#datepicker4').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)		
    } 

    //From의 초기값을 오늘 날짜로 설정
//    $('#datepicker').datepicker('setDate', '-1Y'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
    //To의 초기값을 내일로 설정
//    $('#datepicker2').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)

		$(".ui-datepicker-trigger").css("width","25px");
		$(".ui-datepicker-trigger").css("margin-left","2px");

    //input을 datepicker로 선언  -- 가격현황에서 년월만 선택하도록
    $('#datepicker5').datepicker( {
        changeMonth: true,
        changeYear: true,
        showButtonPanel: true,
        dateFormat: 'yy MM',
 				beforeShow: function (input, inst) {
            var calendar = inst.dpDiv;
            setTimeout(function () {
                calendar.position({
                    my: 'center bottom',
                    at: 'top',
                    collision: 'none',
                    of: input
                });
            }, 1);
        }, 
        onClose: function(dateText, inst) { 
            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
            $(this).datepicker('setDate', new	 Date(year, month, 1));
        }
    });

// 실거래가 에서 검색값이 변경되었을때 시작날자 및 전체검색 히든필드 세팅	
	  $('#scale, #datepicker, #datepicker2, #construction_year, #listcnt').on('change',(function() {
				var date1 = f.contract_date1.value;  // 변경된 날짜값 다시 읽어오기
				var date2 = f.contract_date2.value;  // 변경된 날짜값 다시 읽어오기
				var scale = f.scale.value;         
				var cons_year = f.construction_year.value;         
				var listcnt = f.listcnt.value;         
				$('#scale_id').val(scale);   //전체검색 히든필드에 값넣어주기
				$('#contract_date1_id').val(date1);   //전체검색 히든필드에 값넣어주기
				$('#contract_date2_id').val(date2);   //전체검색 히든필드에 값넣어주기
				$('#construction_year_id').val(cons_year);   //전체검색 히든필드에 값넣어주기
				$('#listcnt_id').val(listcnt);   //전체검색 히든필드에 값넣어주기
			})
	  );
		
	  $('#kind').on('change',(function() {
				var kind = f.kind.value;         
				$('#kind_id').val(kind);   //전체검색 히든필드에 값넣어주기
			})
	  );
// 아파트정보에서 건축년도 조건이 변경되었을때 전체검색 히든필드 세팅		
	  $('#construction_year_info').on('change',(function() {
				var cons_year = f.construction_year.value;            
				$('#construction_year_id').val(cons_year);   //전체검색 히든필드에 값넣어주기
			})
	  );
		
// 일자별 거래현황에서 검색값이 변경되었을때 시작날자 및 전체검색 히든필드 세팅	
	  $('#datetype, #datepicker4').on('change',(function() {
		    var stype = f.datetype.value;
				var date1 = f.contract_date1.value;
				var date2 = f.contract_date2.value;
				date = new Date(date2);
        
				if(stype == "1"){
	        date.setMonth(date.getMonth() - 1);   //1달전 세팅
          date.setDate(date.getDate() + 1);   // +1일 세팅
          $('#datepicker3').val(dateToYYYYMMDD(date));   
				}else if(stype =="2"){
	        date.setFullYear(date.getFullYear() - 1);  //1년전 세팅
          date.setDate(date.getDate() + 1);   // +1일 세팅
          $('#datepicker3').val(dateToYYYYMMDD(date));   
				}else if(stype == "3"){
	        date.setFullYear(date.getFullYear() - 5);  //5년전 세팅
          date.setDate(date.getDate() + 1);   // +1일 세팅
          $('#datepicker3').val(dateToYYYYMMDD(date));   
				}	
				var date1_1 = f.contract_date1.value;  // 변경된 날짜값 다시 읽어오기
				var date2_2 = f.contract_date2.value;  // 변경된 날짜값 다시 읽어오기
				$('#datetypeid').val(stype);   //전체검색 히든필드에 값넣어주기
				$('#contract_date1_id').val(date1_1);   //전체검색 히든필드에 값넣어주기
				$('#contract_date2_id').val(date2_2);   //전체검색 히든필드에 값넣어주기
			})
	  );

	  $('#datepicker3').on('change',(function() {
				var date1 = f.contract_date1.value;
				var date2 = f.contract_date2.value;
				$('#contract_date1_id').val(date1);   //전체검색 히든필드에 값넣어주기
				$('#contract_date2_id').val(date2);   //전체검색 히든필드에 값넣어주기
			})
	  );

	  $('#trade_gbn, #search_year, #datetype_area').on('change',(function() {
				var trgbn = f.trade_gbn.value;         
				var sryear = f.search_year.value;         
				var dtype_area = f.datetype.value;         
				$('#trade_gbn_id').val(trgbn);   //전체검색 히든필드에 값넣어주기
				$('#search_year_id').val(sryear);   //전체검색 히든필드에 값넣어주기
				$('#datetype_area_id').val(dtype_area);   //전체검색 히든필드에 값넣어주기
			})
	  );

// 아파트 정보에서 아파트명 클릭했을때 모달창에 아파트명 전달
/*	  $('.td_apt_name').on('click',(function() {
        var checkBtn = $(this);
	      var tr = checkBtn.parent();
	      var td = tr.children();
	      var m_title = td.eq(3).text();  //해당 tr에서 가지고 오고자하는 td열의 위치	
				$('#info_modal_Label').text(m_title + '상세정보');
			})
	  );*/
		
});

// 날짜 형식 변환 함수
function dateToYYYYMMDD(date){
    function pad(num) {
        num = num + '';
        return num.length < 2 ? '0' + num : num;
    }
    return date.getFullYear() + '-' + pad(date.getMonth()+1) + '-' + pad(date.getDate());
}

// 아파트 선택조건 select box click 시
$(document).ready(function () {
// 시도 선택 select box click 시
    $("#city_do").click(function () {
			 var city_do = f.city_do.value;
			 $.ajax({  
			  type: "POST", 
			  url: "/RegionSelect",  
			  data: "city_do=",   //&a=xxx 식으로 뒤에 더 붙이면 됨
			  success: result_do    //function result 를 의미함
			   }
			 );
    })

	//시도를 선택하면 값을 가지고 와서 action 을 취해주는 callback 함수
	function result_do(msg){
		var region = msg.split('^');
		$('#city_do').children('option:not(:first)').remove();
//	  $('#city_gu').children('option').remove();
//	  $('#dong_gu').children('option').remove();
//	  $('#apt_name').children('option').remove();
//		$('#city_do').children('option:not(:first)').remove();
	  $('#city_gu').children('option:not(:first)').remove();
	  $('#dong_gu').children('option:not(:first)').remove();
	  $('#apt_name').children('option:not(:first)').remove();
	
		for(var i=0; i<region.length; i++){
			if(region[i] != ""){
				$('#city_do').append('<option value="' + region[i] + '">' + region[i] + '</option');
			}
		}
	}
	
// 구군 선택 select box click 시
  $("#city_gu").click(function () {
		 var city_do = f.city_do.value;
		 var city_gu = f.city_gu.value;
		 var num = $('#city_gu option').length	// 동 옵션 갯수
		
	   if(num < 3) {  // 현재 option갯수가 3개보다 작으면 ajax로 다시 불러오기
			 $.ajax({  
			  type: "POST", 
			  url: "/RegionSelect",  
			  data: "city_do="+city_do,   //&a=xxx 식으로 뒤에 더 붙이면 됨
			  success: result_gu    //function result 를 의미함
			   }
			 );		
		 }else {   //선택값이 있으면 
			$("#city_gu").val(city_gu).prop("selected", true);
  	  $('#dong_gu').children('option:not(:first)').remove(); 
	    $('#apt_name').children('option:not(:first)').remove();
//			$("#dong_gu").children('option').remove();
//		  $('#apt_name').children('option').remove();			
	 	 }
  })

	function result_gu(msg){
		var region = msg.split('^');
//		$('#city_gu').children('option').remove();
  	  $('#city_gu').children('option:not(:first)').remove(); 
		for(var i=0; i<region.length; i++){
			if(region[i] != ""){
				$("#city_gu").append('<option value="' + region[i] + '">' + region[i] + '</option');
			}
		}
		$("#city_gu").prepend('<option value="">구(시) 선택...</option');		
	}	

// 동  선택 select box click 시
  $("#dong_gu").click(function () {
		 var city_do = f.city_do.value;
		 var city_gu = f.city_gu.value;
		 var dong_gu = f.dong_gu.value;
		 var num = $('#dong_gu option').length	// 동 옵션 갯수
	
		 if(city_gu == null || city_gu == ""){   //구 선택값이 없으면 동 선택 안되게
			return false;
		 }
     if(num < 3) {  // 현재 option갯수가 3개보다 작으면 ajax로 다시 불러오기
			 $.ajax({  
			  type: "POST", 
			  url: "/RegionSelect",  
			  data: "city_do="+city_do+"&city_gu="+city_gu,   //&a=xxx 식으로 뒤에 더 붙이면 됨
			  success: result_dong    //function result 를 의미함
			   }
			 );
     }else {   // 선택값이 2개이하면 
				$("#dong_gu").val(dong_gu).prop("selected", true);
//			  $('#apt_name').children('option').remove();
	      $('#apt_name').children('option:not(:first)').remove();
     }
  })

	function result_dong(msg){
		var region = msg.split('^');
		$('#dong_gu').children('option').remove();
	
		for(var i=0; i<region.length; i++){
			if(region[i] != ""){
				$("#dong_gu").append('<option value="' + region[i] + '">' + region[i] + '</option');
			}
		}
		$("#dong_gu").prepend('<option value="">동(구) 선택...</option');		
	}	

// 아파트명  선택 select box click 시
  $('#apt_name').click(function () {
		 var city_do = f.city_do.value;
		 var city_gu = f.city_gu.value;
		 var dong_gu = f.dong_gu.value;
		 var apt_name = f.apt_name.value;
		 var num = $('#apt_name option').length	// 아파트명 옵션 갯수	
		 if(city_gu == null || city_gu == ""){   // 구 선택값이 없으면 아파트명 선택 안되게
			return false;
		 }	
		 if(dong_gu == null || dong_gu == ""){   // 동 선택값이 없으면 아파트명 선택 안되게
			return false;
		 }
	   if(num < 3) {  // 현재 option갯수가 3개보다 작으면 ajax로 다시 불러오기
			 $.ajax({  
			  type: "POST", 
			  url: "/RegionSelect",  
			  data: "city_do="+city_do+"&city_gu="+city_gu+"&dong_gu="+dong_gu,   //&a=xxx 식으로 뒤에 더 붙이면 됨
			  success: result_apt    //function result 를 의미함
			   }
			 );
	   }else {   //선택값이 있으면
				$("#apt_name").val(apt_name).prop("selected", true);
	   }
  })

	function result_apt(msg){
		var region = msg.split('^');
		$('#apt_name').children('option').remove();
	
		for(var i=0; i<region.length; i++){
			if(region[i] != ""){
				$("#apt_name").append('<option value="' + region[i] + '">' + region[i] + '</option');
			}
		}
		$("#apt_name").prepend('<option value="">아파트명 선택...</option');			
	}	

});


// 아파트 세부정보에 거래구분, 평형 select box 값 변경 시
$(document).ready(function () {
  $('#scale2, #s_kind2').change(function () {
		 var city_do = aptform.city_do2.value;
		 var city_gu = aptform.city_gu2.value;
		 var dong_gu = aptform.dong_gu2.value;
		 var apt_name = aptform.apt_name2.value;
		 var scale = aptform.scale2.value;
		 var kind = aptform.s_kind2.value;
 
		 $.ajax({  
		  type: "POST", 
      dataType : 'json',
		  url: "/aptinfo/aptinfo_select",  
		  data: "city_do2="+city_do+"&city_gu2="+city_gu+"&dong_gu2="+dong_gu+"&apt_name2="+apt_name+"&scale2="+scale+"&kind2="+kind,   //&a=xxx 식으로 뒤에 더 붙이면 됨
		  success: result_select    //function result 를 의미함
		   }
		 );

  })

	function result_select(msg){
		$('#info_tbody').children().remove();  //기존 테이블 tbody 삭제
    var html = '';

		for(var i=0; i<msg.length; i++){
      html+='<tr>';
      html+='<td class="text-center">'+msg[i].contract_date+'</td>';
      html+='<td class="text-center">'+msg[i].square+'</td>';
      html+='<td class="text-center">'+msg[i].scale+'</td>';
      if(msg[i].kind == "매매") {	
   		   html+='<td class="text-right">'+msg[i].trade_price.toLocaleString('ko-KR')+'</td>';
      }else if(msg[i].kind == "전세") {
   		 
 		     html+='<td class="text-right">'+msg[i].deposit.toLocaleString('ko-KR')+'</td>';
      }else if(msg[i].kind == "월세") {
   		   html+='<td class="text-right">'+msg[i].deposit.toLocaleString('ko-KR')+'/'+msg[i].monthrent.toLocaleString('ko-KR')+'</td>';	                               
	    } 
      html+='<td class="text-center">'+msg[i].floor+'</td>';
      html+='</tr>';			
		}
		$("#info_tbody").append(html);
	}
	
  $('#scale2, #s_kind2').click(function () {
	   var selvalue = $("#scale2 option:selected").val();
     if(selvalue == null) {
			 var city_do = aptform.city_do2.value;
			 var city_gu = aptform.city_gu2.value;
			 var dong_gu = aptform.dong_gu2.value;
			 var apt_name = aptform.apt_name2.value;
			 var kind = aptform.s_kind2.value;
	 
			 $.ajax({  
			  type: "POST", 
	      dataType : 'json',
			  url: "/aptinfo/aptinfo_kindselect",  
			  data: "city_do2="+city_do+"&city_gu2="+city_gu+"&dong_gu2="+dong_gu+"&apt_name2="+apt_name+"&kind2="+kind,   //&a=xxx 식으로 뒤에 더 붙이면 됨
			  success: result_kindselect    //function result 를 의미함
			   }
			 );
	   }

  })

	function result_kindselect(msg){
		$('#scale2').children().remove();  //기존 테이블 tbody 삭제
    var html = '';

    html+='<option value="" disabled selected hidden="hidden">평형...</option>';
    html+='<option value="전체">전체</option>';
		for(var i=0; i<msg.length; i++){
      html+='<option value="'+msg[i].scale+'">'+msg[i].scale+'평</option>';
		}
		$("#scale2").append(html);
	}		
		
});
