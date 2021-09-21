<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>     
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="Responsive Admin &amp; Dashboard Template based on Bootstrap 5">
	<meta name="author" content="AdminKit">
	<meta name="keywords" content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

	<link rel="shortcut icon" href="../img/icons/icon-48x48.png" />

	<title>Yun's 아파트 거래정보</title>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">	
  <link href="../css/app.css" rel="stylesheet">
  <link href="../css/yunscss.css" rel="stylesheet">
  <style type="text/css">
  	#ksy_aptinfo {
  		background-color: #fa8072;
  	}
  </style>

</head>

<body>
	<div class="wrapper">
<!-- ----------------------------------------------------------------------------- -->
<!--  사이드 메뉴 -->	
<!-- ----------------------------------------------------------------------------- -->
<!--  사이드 메뉴는 sidebar/sidebarmenufile.jsp에서 가져온다 -->	
    <c:import url="/sidebar/sidebarmenufile.jsp"/>
<!-- ----------------------------------------------------------------------------- -->
<!--  메인 화면 -->	
<!-- ----------------------------------------------------------------------------- -->
		<div class="main">
<!--  top 영역 -->		
<!--  --------------------------------------------------------------------------- -->		
			<nav class="navbar navbar-expand navbar-light navbar-bg">
				<a class="sidebar-toggle d-flex">
          <i class="hamburger align-self-center"></i>
        </a>

				<form class="d-none d-sm-inline-block" name="infoall" method="get">
					<div class="input-group input-group-navbar">
						<input type="text" class="form-control" placeholder="Search all…" aria-label="Search" name="infoall" value="${param.infoall }">
						<button class="btn" type="submit" id="infoallserach" value="all" name="search">
              <i class="align-middle" data-feather="search"></i>
            </button>
					</div>
					<input type="hidden" id="construction_year_id" name="construction_year" value="${param.construction_year }">
					<input type="hidden" id="listcnt_id" name="listcnt" value="${param.listcnt }">
				</form>
				
				<div class="col-auto ml-auto text-right mt-n1 pl-3 d-none d-sm-inline-block">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb bg-transparent p-0 mt-1 mb-0">
							<li class="breadcrumb-item"><a href="../index">아파트정보</a></li>
							<li class="breadcrumb-item"><a href="../aptinfo/aptinfo_detail">서울지역</a></li>
							<li class="breadcrumb-item text-danger">상세정보</li>
						</ol>
					</nav>
				</div>
				
				<div class="navbar-collapse collapse">
					<ul class="navbar-nav navbar-align">
						<li class="nav-item dropdown">
							<a class="nav-icon dropdown-toggle d-inline-block d-sm-none" href="#" data-toggle="dropdown">
                <i class="align-middle" data-feather="user"></i>
              </a>
							<a class="nav-link dropdown-toggle d-none d-sm-inline-block" href="#" data-toggle="dropdown">
                <i class="fas fa-user fa-lg"></i>  
                <span class="text-dark">${(userid != null)?userid:'로그인'}</span>
              </a>
							<div class="dropdown-menu dropdown-menu-right">
							  <c:if test="${userid == null }">
									<a class="dropdown-item" href="../login/login?cmd=login">
										<i class="align-middle mr-1" data-feather="user"></i>로그인</a>
								</c:if>
								<div class="dropdown-divider"></div>
								<c:if test="${userid != null }">
									<a class="dropdown-item" href="../login/profile?cmd=display">
										<i class="align-middle mr-1" data-feather="settings"></i>프로필</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="../login/login?cmd=logout">
										<i class="align-middle mr-1" data-feather="user"></i>로그아웃</a>
								</c:if>	
							</div>
					
						</li>
					</ul>
				</div>
			</nav>
<!-- ---------------------------------------------------------------------------------------- -->			
<!--  메인 영역 -->
<!-- ---------------------------------------------------------------------------------------- -->			
			<div class="row pl-2 pr-2 pt-4">
				<div class="container-fluid ">
<!-- ------------------------------------------------------------ -->	
<!--      아파트상세정보-->			
					<div class="row pb-4">						
						<div class="col-md-5 bg-white border rounded border-light">
	             <div class="form-group mb-4" id="info-display">
								  <div class="row" id="info-display-table">
								    <div class="table-responsive tableFixHead" style="overflow:auto">
									  	<table class="table table-bordered table-hover table-sm">
				             	 <c:forEach var="i" items="${info }">	
										  		<tr>
										  			<td class="text-white text-center" id="ksy_aptinfo" style="width:18%">아파트명</td>
										  			<td style="width: 35%" id="map_aptname">${i.apt_name }</td>
										  			<td class="text-white text-center" id="ksy_aptinfo" style="width:18%">건축년도</td>
										  			<td style="width: 35%">${i.construction_year }</td>						  			
										  		</tr>
										  		<tr>
										  			<td class="text-white text-center" id="ksy_aptinfo">도로명주소</td>
										  			<td id="map_road" colspan="3">${i.address_road }</td>
										  		</tr>
										  		<tr>
										  			<td class="text-white text-center" id="ksy_aptinfo">지번주소</td>
										  			<td colspan="3">${i.address_jibun }</td>
										  		</tr>
										  		<tr>
										  			<td class="text-white text-center" id="ksy_aptinfo">동갯수</td>
										  			<td>${i.dong_cnt }</td>
										  			<td class="text-white text-center" id="ksy_aptinfo">세대수</td>
										  			<td><fmt:formatNumber value="${i.household_cnt }"/></td>						  			
										  		</tr>						  		
										  		<tr>
										  			<td class="text-white text-center" id="ksy_aptinfo">난방방식</td>
										  			<td>${i.heating }</td>
										  			<td class="text-white text-center" id="ksy_aptinfo">복도유형</td>
										  			<td>${i.corridor }</td>						  			
										  		</tr>						  		
										  		<tr>
										  			<td class="text-white text-center" id="ksy_aptinfo">시공사</td>
										  			<td>${i.constructor }</td>
										  			<td class="text-white text-center" id="ksy_aptinfo">주차대수</td>
										  			<td><fmt:formatNumber value="${i.park_tot }"/></td>						  			
										  		</tr>	
			  	              </c:forEach>						  						  							  		
									  	</table>								  	
								  	</div>
								  </div>
									  <!--  거래구분/ 평형선택 -->
									<div class="row" >
							      <form action="../aptinfo/aptinfo_detail" method="post" name="aptform" > 									
									    <div class="table-responsive tableFixHead" style="overflow:auto">
										  	<table class="table table-bordered table-hover table-sm">									
										  		<tr>
										  			<td class="text-white text-center bg-info" style="width: 18%">거래구분</td>
										  			<td style="width: 35%">
											  			 <select class="form-select" id="s_kind2" name="s_kind2">
								                 <option value="" disabled selected hidden="hidden">거래구분...</option>
								            	   <option ${(kind2 == "매매")?"selected":"" } value="매매">매매</option>
								            	   <option ${(kind2 == "전세")?"selected":"" } value="전세">전세</option>
								            	   <option ${(kind2 == "월세")?"selected":"" } value="월세">월세</option>		
							            	   </select>								  			
										  			</td>
										  			
										  			<td class="text-white text-center bg-info" style="width: 18%">평형</td>
										  			<td style="width: 35%" >
											  			 <select class="form-select" id="scale2" name="scale2">
								                 <option value="" disabled selected hidden="hidden">평형...</option>
								                 <option value="전체">전체</option>
								                 <c:forEach var="s" items="${scales }">
									            	   <option value="${s.scale }">${s.scale }평</option>
								            	   </c:forEach>
						            		   </select>
										  			</td>
										  		</tr>
									  		</table>
									  	</div>
									  	<div id="info-display-kind">	
									  		<input type="hidden" name="city_do2" value="${city_do2 }">
										  	<input type="hidden" name="city_gu2" value="${city_gu2 }">
										  	<input type="hidden" name="dong_gu2" value="${dong_gu2 }">
										  	<input type="hidden" name="apt_name2" value="${apt_name2 }">
										  	<input type="hidden" name="kind2" value="${kind2 }">
									  	</div>	
									  </form>		
									</div>	
		<!--      실거래가 -->								  
			 						<div class="row">
						          <div class="table-responsive tableFixHead" style="height:calc(100vh - 450px); overflow:auto">
						           <table class="table table-bordered table-hover table-sm">
						             <thead class="bg-secondary text-center text-white sticky-top" >
					               <tr>
					                <th>계약일자</th>
					                <th>전용면적</th>
					                <th>평수</th>
					                <th>거래금액</th>
					                <th>층</th>
					               </tr>
					             </thead>
					             <tbody id="info_tbody">
					             	 <c:forEach var="n" items="${list }">	
					                <tr>
					                  <td class="text-center">${n.contract_date }</td>
					                  <td class="text-center">${n.square }</td>
					                  <td class="text-center">${n.scale }</td>
					                  <c:if test="${kind2 == '매매'}">
					                    <td class="text-right"><fmt:formatNumber value="${n.trade_price }" /></td>
					                  </c:if>
					                  <c:if test="${kind2 == '월세'}">
					                    <td class="text-right"><fmt:formatNumber value="${n.deposit }" /> / <fmt:formatNumber value="${n.monthrent }" /> </td>
					                  </c:if>
					                  <c:if test="${kind2 == '전세'}">
					                    <td class="text-right"><fmt:formatNumber value="${n.deposit }" /></td>
					                  </c:if>
					                  <td class="text-center">${n.floor }</td>
					                </tr>
					               </c:forEach>
					             </tbody>
					           </table>
					          </div>
									</div>	
	             </div>    
            </div>  
<!--  다음지도 -->						  
						<div class="col-md-7 bg-white border rounded border-light" id="info-map">
						  <div class="row">
						    <div class="row">
									<div class="col-md-3 form-check form-switch">
									  <input class="distance form-check-input" type="checkbox" id="flexSwitchCheckDefault" name="distance">
									  <label class="form-check-label" for="flexSwitchCheckDefault">거리재기</label>
									</div>
									<div class="col-md-3 form-check form-switch">
									  <input class="bigshow form-check-input" type="checkbox" id="flexSwitchCheckDefault2" name="bigshow">
									  <label class="form-check-label" for="flexSwitchCheckDefault2">크게보기</label>
									</div>						
								</div>		
						    <div id="map2" >
									<div class="map_wrap" style="height:75vh;">
									    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
									</div>						    
						    </div>
<!-- 								<div id="container">
								    <div id="rvWrapper">
								        <div id="roadview" style="width:100%;height:100%;"></div> 로드뷰를 표시할 div 입니다
								        <div id="close" title="로드뷰닫기" onclick="closeRoadview()"><span class="img"></span></div>
								    </div>
								    <div id="mapWrapper">
								        <div id="map" style="width:100%;height:100%"></div> 지도를 표시할 div 입니다
								        <div id="roadviewControl" onclick="setRoadviewRoad()"></div>
								    </div>
								</div>	 -->					    
						  </div>
            </div>          
					</div>
				</div>
			</div>
					
<!--------------------------------------------------------- -->			
<!--  footer -->
<!--------------------------------------------------------- -->			
			<footer class="footer">
				<div class="container-fluid">
					<div class="row text-muted">
						<div class="col-12 text-center">
							<p class="mb-0">
								<a href="index.html" class="text-muted"><strong>Copyright Sangyoun Kim</strong></a> &copy;
							</p>
						</div>
					</div>
				</div>
			</footer>
		</div>
	</div>

<!-- bootstrap core js -->
  <script src="../js/bootstrap.bundle.min.js"></script>
	<script src="../js/app.js"></script>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>	
<!-- 커스텀 js -->
  <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ac0cd92fd4f687c5dcd49b675e7908b9&libraries=services"></script>
  <script src="../js/daummap.js"></script>
  <script src="../js/yunsapt.js"></script>

</body>

</html>