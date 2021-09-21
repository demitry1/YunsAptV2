////////////////////////////////////////////////////////////////////
// 지도생성 시작
////////////////////////////////////////////////////////////////////
	var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
				center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
				level: 3 //지도의 레벨(확대, 축소 정도)
			};
	var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
	var mapTypeControl = new kakao.maps.MapTypeControl();
	// 지도 타입 컨트롤을 지도에 표시합니다
	map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
	// 지도를 클릭한 위치에 표출할 마커입니다
	var marker = new kakao.maps.Marker({ 
	    // 지도 중심좌표에 마커를 생성합니다 
	    position: map.getCenter() 
	}); 
	// 지도에 마커를 표시합니다
	marker.setMap(map);	
////////////////////////////////////////////////////////////////////
// 지도생성 끝
////////////////////////////////////////////////////////////////////	
  var isDistance = false;
	$('.distance').on('click', function() {      
		  var $this = $(this);
	    if($this.is(':checked')){
		      isDistance = true;
	        kakao.maps.event.addListener(map, 'click', clickHandler);
	        kakao.maps.event.addListener(map, 'mousemove', mousemoveHandler);
	        kakao.maps.event.addListener(map, 'rightclick', rightclickHandler);
	    } else {
		      isDistance = false;
	        kakao.maps.event.removeListener(map, 'click', clickHandler);
	        kakao.maps.event.removeListener(map, 'mousemove', mousemoveHandler);
	        kakao.maps.event.removeListener(map, 'rightclick', rightclickHandler);
	    }
	/*    if($this.hasClass('active')){
	        $this.removeClass('active').text('거리재기 켜기');
	        kakao.maps.event.removeListener(map, 'click', clickHandler);
	        kakao.maps.event.removeListener(map, 'mousemove', mousemoveHandler);
	        kakao.maps.event.removeListener(map, 'rightclick', rightclickHandler);
	    } else {
	        $this.addClass('active').text('거리재기 끄기');
	        kakao.maps.event.addListener(map, 'click', clickHandler);
	        kakao.maps.event.addListener(map, 'mousemove', mousemoveHandler);
	        kakao.maps.event.addListener(map, 'rightclick', rightclickHandler);
	    }*/
	});

	
////////////////////////////////////////////////////////////////////
// 거리재기 시작
////////////////////////////////////////////////////////////////////	
	var drawingFlag = false; // 선이 그려지고 있는 상태를 가지고 있을 변수입니다
	var moveLine; // 선이 그려지고 있을때 마우스 움직임에 따라 그려질 선 객체 입니다
	var clickLine // 마우스로 클릭한 좌표로 그려질 선 객체입니다
	var distanceOverlay; // 선의 거리정보를 표시할 커스텀오버레이 입니다
	var dots = {}; // 선이 그려지고 있을때 클릭할 때마다 클릭 지점과 거리를 표시하는 커스텀 오버레이 배열입니다.
	
	var clickHandler = function(mouseEvent) {
	
	    // 마우스로 클릭한 위치입니다
	    var clickPosition = mouseEvent.latLng;
	
	    // 지도 클릭이벤트가 발생했는데 선을 그리고있는 상태가 아니면
	    if (!drawingFlag) {
	
	        // 상태를 true로, 선이 그리고있는 상태로 변경합니다
	        drawingFlag = true;
	
	        // 지도 위에 선이 표시되고 있다면 지도에서 제거합니다
	        deleteClickLine();
	
	        // 지도 위에 커스텀오버레이가 표시되고 있다면 지도에서 제거합니다
	        deleteDistnce();
	
	        // 지도 위에 선을 그리기 위해 클릭한 지점과 해당 지점의 거리정보가 표시되고 있다면 지도에서 제거합니다
	        deleteCircleDot();
	
	        // 클릭한 위치를 기준으로 선을 생성하고 지도위에 표시합니다
	        clickLine = new kakao.maps.Polyline({
	            map: map, // 선을 표시할 지도입니다
	            path: [clickPosition], // 선을 구성하는 좌표 배열입니다 클릭한 위치를 넣어줍니다
	            strokeWeight: 3, // 선의 두께입니다
	            strokeColor: '#db4040', // 선의 색깔입니다
	            strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
	            strokeStyle: 'solid' // 선의 스타일입니다
	        });
	
	        // 선이 그려지고 있을 때 마우스 움직임에 따라 선이 그려질 위치를 표시할 선을 생성합니다
	        moveLine = new kakao.maps.Polyline({
	            strokeWeight: 3, // 선의 두께입니다
	            strokeColor: '#db4040', // 선의 색깔입니다
	            strokeOpacity: 0.5, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
	            strokeStyle: 'solid' // 선의 스타일입니다
	        });
	
	        // 클릭한 지점에 대한 정보를 지도에 표시합니다
	        displayCircleDot(clickPosition, 0);
	
	
	    } else { // 선이 그려지고 있는 상태이면
	
	        // 그려지고 있는 선의 좌표 배열을 얻어옵니다
	        var path = clickLine.getPath();
	
	        // 좌표 배열에 클릭한 위치를 추가합니다
	        path.push(clickPosition);
	
	        // 다시 선에 좌표 배열을 설정하여 클릭 위치까지 선을 그리도록 설정합니다
	        clickLine.setPath(path);
	
	        var distance = Math.round(clickLine.getLength());
	        displayCircleDot(clickPosition, distance);
	    }
	}
	
	var mousemoveHandler = function(mouseEvent) {
	    // 지도 마우스무브 이벤트가 발생했는데 선을 그리고있는 상태이면
	    if (drawingFlag){
	
	        // 마우스 커서의 현재 위치를 얻어옵니다
	        var mousePosition = mouseEvent.latLng;
	
	        // 마우스 클릭으로 그려진 선의 좌표 배열을 얻어옵니다
	        var path = clickLine.getPath();
	
	        // 마우스 클릭으로 그려진 마지막 좌표와 마우스 커서 위치의 좌표로 선을 표시합니다
	        var movepath = [path[path.length-1], mousePosition];
	        moveLine.setPath(movepath);
	        moveLine.setMap(map);
	
	        var distance = Math.round(clickLine.getLength() + moveLine.getLength()), // 선의 총 거리를 계산합니다
	            content = '<div class="dotOverlay distanceInfo">총거리 <span class="number">' + distance + '</span>m</div>'; // 커스텀오버레이에 추가될 내용입니다
	
	        // 거리정보를 지도에 표시합니다
	        showDistance(content, mousePosition);
	    }
	}
	
	var rightclickHandler = function (mouseEvent) {
	
	    // 지도 오른쪽 클릭 이벤트가 발생했는데 선을 그리고있는 상태이면
	    if (drawingFlag) {
	
	        // 마우스무브로 그려진 선은 지도에서 제거합니다
	        moveLine.setMap(null);
	        moveLine = null;
	
	        // 마우스 클릭으로 그린 선의 좌표 배열을 얻어옵니다
	        var path = clickLine.getPath();
	
	        // 선을 구성하는 좌표의 개수가 2개 이상이면
	        if (path.length > 1) {
	
	            // 마지막 클릭 지점에 대한 거리 정보 커스텀 오버레이를 지웁니다
	            if (dots[dots.length-1].distance) {
	                dots[dots.length-1].distance.setMap(null);
	                dots[dots.length-1].distance = null;
	            }
	
	            var distance = Math.round(clickLine.getLength()), // 선의 총 거리를 계산합니다
	                content = getTimeHTML(distance); // 커스텀오버레이에 추가될 내용입니다
	
	            // 그려진 선의 거리정보를 지도에 표시합니다
	            showDistance(content, path[path.length-1]);
	
	        } else {
	
	            // 선을 구성하는 좌표의 개수가 1개 이하이면
	            // 지도에 표시되고 있는 선과 정보들을 지도에서 제거합니다.
	            deleteClickLine();
	            deleteCircleDot();
	            deleteDistnce();
	
	        }
	
	        // 상태를 false로, 그리지 않고 있는 상태로 변경합니다
	        drawingFlag = false;
	    }
	}
	
	// 클릭으로 그려진 선을 지도에서 제거하는 함수입니다
	function deleteClickLine() {
	    if (clickLine) {
	        clickLine.setMap(null);
	        clickLine = null;
	    }
	}
	
	// 마우스 드래그로 그려지고 있는 선의 총거리 정보를 표시하거
	// 마우스 오른쪽 클릭으로 선 그리가 종료됐을 때 선의 정보를 표시하는 커스텀 오버레이를 생성하고 지도에 표시하는 함수입니다
	function showDistance(content, position) {
	
	    if (distanceOverlay) { // 커스텀오버레이가 생성된 상태이면
	
	        // 커스텀 오버레이의 위치와 표시할 내용을 설정합니다
	        distanceOverlay.setPosition(position);
	        distanceOverlay.setContent(content);
	
	    } else { // 커스텀 오버레이가 생성되지 않은 상태이면
	
	        // 커스텀 오버레이를 생성하고 지도에 표시합니다
	        distanceOverlay = new kakao.maps.CustomOverlay({
	            map: map, // 커스텀오버레이를 표시할 지도입니다
	            content: content,  // 커스텀오버레이에 표시할 내용입니다
	            position: position, // 커스텀오버레이를 표시할 위치입니다.
	            xAnchor: 0,
	            yAnchor: 0,
	            zIndex: 3
	        });
	    }
	}
	
	// 그려지고 있는 선의 총거리 정보와
	// 선 그리가 종료됐을 때 선의 정보를 표시하는 커스텀 오버레이를 삭제하는 함수입니다
	function deleteDistnce () {
	    if (distanceOverlay) {
	        distanceOverlay.setMap(null);
	        distanceOverlay = null;
	    }
	}
	
	// 선이 그려지고 있는 상태일 때 지도를 클릭하면 호출하여
	// 클릭 지점에 대한 정보 (동그라미와 클릭 지점까지의 총거리)를 표출하는 함수입니다
	function displayCircleDot(position, distance) {
	
	    // 클릭 지점을 표시할 빨간 동그라미 커스텀오버레이를 생성합니다
	    var circleOverlay = new kakao.maps.CustomOverlay({
	        content: '<span class="dot"></span>',
	        position: position,
	        zIndex: 1
	    });
	
	    // 지도에 표시합니다
	    circleOverlay.setMap(map);
	
	    if (distance > 0) {
	        // 클릭한 지점까지의 그려진 선의 총 거리를 표시할 커스텀 오버레이를 생성합니다
	        var distanceOverlay = new kakao.maps.CustomOverlay({
	            content: '<div class="dotOverlay">거리 <span class="number">' + distance + '</span>m</div>',
	            position: position,
	            yAnchor: 1,
	            zIndex: 2
	        });
	
	        // 지도에 표시합니다
	        distanceOverlay.setMap(map);
	    }
	
	    // 배열에 추가합니다
	    dots.push({circle:circleOverlay, distance: distanceOverlay});
	}
	
	// 클릭 지점에 대한 정보 (동그라미와 클릭 지점까지의 총거리)를 지도에서 모두 제거하는 함수입니다
	function deleteCircleDot() {
	    var i;
	
	    for ( i = 0; i < dots.length; i++ ){
	        if (dots[i].circle) {
	            dots[i].circle.setMap(null);
	        }
	
	        if (dots[i].distance) {
	            dots[i].distance.setMap(null);
	        }
	    }
	
	    dots = [];
	}
	
	// 마우스 우클릭 하여 선 그리기가 종료됐을 때 호출하여
	// 그려진 선의 총거리 정보와 거리에 대한 도보, 자전거 시간을 계산하여
	// HTML Content를 만들어 리턴하는 함수입니다
	function getTimeHTML(distance) {
	
	    // 도보의 시속은 평균 4km/h 이고 도보의 분속은 67m/min입니다
	    var walkkTime = distance / 67 | 0;
	    var walkHour = '', walkMin = '';
	
	    // 계산한 도보 시간이 60분 보다 크면 시간으로 표시합니다
	    if (walkkTime > 60) {
	        walkHour = '<span class="number">' + Math.floor(walkkTime / 60) + '</span>시간 '
	    }
	    walkMin = '<span class="number">' + walkkTime % 60 + '</span>분'
	
	    // 자전거의 평균 시속은 16km/h 이고 이것을 기준으로 자전거의 분속은 267m/min입니다
	    var bycicleTime = distance / 227 | 0;
	    var bycicleHour = '', bycicleMin = '';
	
	    // 계산한 자전거 시간이 60분 보다 크면 시간으로 표출합니다
	    if (bycicleTime > 60) {
	        bycicleHour = '<span class="number">' + Math.floor(bycicleTime / 60) + '</span>시간 '
	    }
	    bycicleMin = '<span class="number">' + bycicleTime % 60 + '</span>분'
	
	    // 거리와 도보 시간, 자전거 시간을 가지고 HTML Content를 만들어 리턴합니다
	    var content = '<ul class="dotOverlay distanceInfo">';
	    content += '    <li>';
	    content += '        <span class="label">총거리</span><span class="number">' + distance + '</span>m';
	    content += '    </li>';
	    content += '    <li>';
	    content += '        <span class="label">도보</span>' + walkHour + walkMin;
	    content += '    </li>';
	    content += '    <li>';
	    content += '        <span class="label">자전거</span>' + bycicleHour + bycicleMin;
	    content += '    </li>';
	    content += '</ul>'
	
	    return content;
	}
////////////////////////////////////////////////////////////////////
// 거리재기 종료
////////////////////////////////////////////////////////////////////		
	
	
////////////////////////////////////////////////////////////////////
// 클릭한 위치에 주소정보 표시 시작
////////////////////////////////////////////////////////////////////			
// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();
// 아파트의 주소/이름를 가져온다
  var s_address_road = $('#map_road').text();
  var s_apt_name = $('#map_aptname').text();
	// 주소로 좌표를 검색합니다
	geocoder.addressSearch(s_address_road, function(result, status) {
    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {
        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            disableAutoPan: true,
            position: coords
        });
        // 인포윈도우로 장소에 대한 설명을 표시합니다
//        var infowindow = new kakao.maps.InfoWindow({
//            content: '<div style="width:150px;text-align:center;padding:6px 0;">' + s_apt_name + '</div>'
//       });
//       infowindow.open(map, marker);
        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
/*				map.setLevel(11);
				map.relayout();
				map.setLevel(3);
				map.relayout();*/
    }
  });

	var marker = new kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
	    infowindow = new kakao.maps.InfoWindow({zindex:1}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다
	
	var selRoadAddress = '';  //맵에서 선택한 곳 도로명주소
	var selJibunAddress = ''; //맵에서 선택한 곳 지번주소
	// 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
	searchAddrFromCoords(map.getCenter(), displayCenterInfo);
	// 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
	kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
	    searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
	        if (status === kakao.maps.services.Status.OK && isDistance === false) {
	            var detailAddr = !!result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
	            detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';
	            var content = '<div class="bAddr">' +
	                            detailAddr + 
	                        '</div>';
	            // 맵에서 선택한 곳 주소
	            if(result[0].road_address !== null) {
	            	selRoadAddress = result[0].road_address.address_name;
	              selRoadAddress = selRoadAddress.replace(selRoadAddress.substr(0,2), '서울특별시');
							}
	            selJibunAddress = result[0].address.address_name;
	            selJibunAddress = selJibunAddress.replace(selJibunAddress.substr(0,2), '서울특별시');
	            // 마커를 클릭한 위치에 표시합니다 
	            marker.setPosition(mouseEvent.latLng);
	            marker.setMap(map);
	
	            // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
	            infowindow.setContent(content);
	            infowindow.open(map, marker);

	            // 지도를 클릭했을때 클릭한 위치의 아파트 상세정보를 가져와서 출력한다.   
 						 $.ajax({  
						  type: "POST", 
				      dataType : 'json',
						  url: "/aptinfo/aptinfo_mapselect",  
						  data: "selRoadAddress"+selRoadAddress+"&selJibunAddress="+selJibunAddress,   //&a=xxx 식으로 뒤에 더 붙이면 됨
						  success: result_select_map    //function result_select_map 를 의미함
						   }
						 );
					
						function result_select_map(msg){
							if(msg == ""){    //맵에서 선택한 곳에 아파트이름이 없으면 동작 안하도록 함
								return;
							}
							$('#info-display-table').children().remove();  //기존 테이블 tbody 삭제
							$('#info-display-kind').children().remove();  //기존 테이블 tbody 삭제
							$('#scale2').children().remove();  //기존 테이블 tbody 삭제
					    var html = '';
						    html+='<div class="table-responsive tableFixHead" style="overflow:auto">';
						    html+='   <table class="table table-bordered table-hover table-sm">';
					      html+='    <tr>';
					      html+='      <td class="text-white text-center" id="ksy_aptinfo" style="width:18%">아파트명</td>';
					      html+='      <td style="width: 35%" id="map_aptname">'+msg[0].apt_name+'</td>';
					      html+='      <td class="text-white text-center" id="ksy_aptinfo" style="width:18%">건축년도</td>';
					      html+='      <td style="width: 35%">'+msg[0].construction_year+'</td>';
                html+='    </tr>';
                html+='    <tr>';
					      html+='      <td class="text-white text-center" id="ksy_aptinfo">도로명주소</td>';
					      html+='      <td id="map_road" colspan="3">'+msg[0].address_road+'</td>';
                html+='    </tr>';
                html+='    <tr>';
					      html+='      <td class="text-white text-center" id="ksy_aptinfo">지번주소</td>';
					      html+='      <td id="map_road" colspan="3">'+msg[0].address_jibun+'</td>';
                html+='    </tr>';
					      html+='    <tr>';
					      html+='      <td class="text-white text-center" id="ksy_aptinfo">동갯수</td>';
					      html+='      <td>'+msg[0].dong_cnt+'</td>';
					      html+='      <td class="text-white text-center" id="ksy_aptinfo">세대수</td>';
					      html+='      <td>'+msg[0].household_cnt+'</td>';
                html+='    </tr>';
					      html+='    <tr>';
					      html+='      <td class="text-white text-center" id="ksy_aptinfo">난방방식</td>';
					      html+='      <td>'+msg[0].heating+'</td>';
					      html+='      <td class="text-white text-center" id="ksy_aptinfo">복도유형</td>';
					      html+='      <td>'+msg[0].corridor+'</td>';
                html+='    </tr>';
					      html+='    <tr>';
					      html+='      <td class="text-white text-center" id="ksy_aptinfo">시공사</td>';
					      html+='      <td>'+msg[0].constructor+'</td>';
					      html+='      <td class="text-white text-center" id="ksy_aptinfo">주차대수</td>';
					      html+='      <td>'+msg[0].park_tot+'</td>';
                html+='    </tr>';
	              html+='  </table>';
	              html+='</div>';
					     	$("#info-display-table").append(html);

                html = '';  
	              html+='    <input type="hidden" name="city_do2" value="'+msg[0].city_do+'">';
	              html+='    <input type="hidden" name="city_gu2" value="'+msg[0].city_gu+'">';
	              html+='    <input type="hidden" name="dong_gu2" value="'+msg[0].dong_gu+'">';
	              html+='    <input type="hidden" name="apt_name2" value="'+msg[0].apt_name+'">';
	              html+='    <input type="hidden" name="kind2" value="'+msg[0].kind+'">';
                $("#info-display-kind").append(html); 
					  	// console.log("mst:"+JSON.stringify(msg,null,2));				
              // 지도를 클릭했을때 클릭한 위치의 아파트 실거래가를 가져와서 출력한다. 
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
							  success: result_select_real    //function result 를 의미함
							   }
							 );						
						}	

						function result_select_real(msg){
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
								
				  //   var link = "../aptinfo/aptinfo_mapselect?"+"selRoadAddress"+selRoadAddress+"&selJibunAddress="+selJibunAddress;   	
			    //   $(location).attr("href", link); 
	
	        }   
	    });
	});
	
	// 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
	kakao.maps.event.addListener(map, 'idle', function() {
	    searchAddrFromCoords(map.getCenter(), displayCenterInfo);
	});
	
	function searchAddrFromCoords(coords, callback) {
	    // 좌표로 행정동 주소 정보를 요청합니다
	    geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);         
	}
	
	function searchDetailAddrFromCoords(coords, callback) {
	    // 좌표로 법정동 상세 주소 정보를 요청합니다
	    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
	}
	
	// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
	function displayCenterInfo(result, status) {
	    if (status === kakao.maps.services.Status.OK) {
	        var infoDiv = document.getElementById('centerAddr');
	
	/*        for(var i = 0; i < result.length; i++) {
	            // 행정동의 region_type 값은 'H' 이므로
	            if (result[i].region_type === 'H') {
	                infoDiv.innerHTML = result[i].address_name;
	                break;
	            }
	        }*/
	    }    
	}
	////////////////////////////////////////////////////////////////////
	// 클릭한 위치에 주소정보 표시 끝
	////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////
	// 지도크게 보기
	////////////////////////////////////////////////////////////////////	
	$('.bigshow').on('click', function() {      
		  var $this = $(this);
      map.relayout()
	    if($this.is(':checked')){
				$('#info-display').addClass('d-none');
				$('#info-map').addClass('col-md-12');
				$('#info-map').removeClass('col-md-7');
	    } else {
		    $('#info-display').removeClass('d-none');
				$('#info-map').addClass('col-md-7');
				$('#info-map').removeClass('col-md-12');
	    }
	});	
