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

	<link rel="shortcut icon" href="img/icons/icon-48x48.png" />

	<title>Yun's 아파트 거래정보</title>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">	
  <link href="../css/app.css" rel="stylesheet">
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

				<form class="d-none d-sm-inline-block" name="tradeall" method="get">
				 <div class="input-group input-group-navbar">
					<input type="text" class="form-control" placeholder="Search all…" aria-label="Search" name="tradearea_all" value="${param.tradearea_all }">
					 <button class="btn" type="submit" id="tradeserachall" value="all" name="search">
	          <i class="align-middle" data-feather="search"></i>
	         </button>
				 </div>
				 <input type="hidden" id="datetype_area_id" name="datetype" value= "${param.datetype}" >
				 <input type="hidden" id="search_year_id" name="search_year" value= "${param.search_year}" >
				 <input type="hidden" id="trade_gbn_id" name="trade_gbn" value= "${param.trade_gbn}" >
				</form>
				
				<div class="col-auto ml-auto text-right mt-n1 pl-3 d-none d-sm-inline-block">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb bg-transparent p-0 mt-1 mb-0">
							<li class="breadcrumb-item"><a href="../index">홈</a></li>
							<li class="breadcrumb-item"><a href="../index">거래현황</a></li>
							<li class="breadcrumb-item text-danger">지역별</li>
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
			<main class="content">
				<div class="container-fluid p-0">
<!-- ------------------------------------------------------------ -->	
<!--      조건검색 / 버튼 영역 -->			
					<div class="row pb-2">
            <form action="../trade/tradearea" method="get" name="f" id="f">
           		 <div class="row">
	              	<div class="col-md-4 pb-1">
		                <select class="form-select" id="city_do" name="city_do">
		                 <option value="" disabled selected hidden="hidden">시(도) 선택...</option>
		                 <option ${(param.city_do != null)?"selected":"" }>${param.city_do} </option>
		            		</select>
	            		</div>
	              	<div class="col-md-4 pb-1">
		                <select class="form-select" id="city_gu" name="city_gu">
		                 <option value="" disabled selected hidden="hidden">구(시) 선택...</option>
		                 <option ${(param.city_gu != null)?"selected":"" }>${param.city_gu} </option>		                 
		            		</select>
	            		</div>
	              	<div class="col-md-4 pb-1">
		                <select class="form-select" id="dong_gu" name="dong_gu">
		                 <option value="" disabled selected hidden="hidden">동(구) 선택...</option>
		                 <option ${(param.dong_gu != null)?"selected":"" }>${param.dong_gu} </option>			                 
		            		</select>
	            		</div>	            		
            	 </div>
            	 
          		 <div class="row mt-2 mb-3">
	            		<div class="col-md-3 pb-1 ">
		                <select class="form-select" id="datetype_area" name="datetype">
		                 <option value="" disabled selected hidden="hidden">검색기준...</option>
		            	   <option ${(param.datetype == "1")?"selected":"" } value="1">월별</option>
		            	   <option ${(param.datetype == "2")?"selected":"" } value="2">년도별</option>
		            		</select>									
	            		</div>	
	            		<div class="col-md-3 pb-1">
		                <select class="form-select" id="search_year" name="search_year">
			                 <option value="" disabled selected hidden="hidden">검색년도...</option>
			            	   <option ${(param.search_year == "2021")?"selected":"" } value="2021">2021년</option>
			            	   <option ${(param.search_year == "2020")?"selected":"" } value="2020">2020년</option>
			            	   <option ${(param.search_year == "2019")?"selected":"" } value="2019">2019년</option>
			            	   <option ${(param.search_year == "2018")?"selected":"" } value="2018">2018년</option>
			            	   <option ${(param.search_year == "2017")?"selected":"" } value="2017">2017년</option>
			            	   <option ${(param.search_year == "2016")?"selected":"" } value="2016">2016년</option>
			            	   <option ${(param.search_year == "2015")?"selected":"" } value="2015">2015년</option>
			            	   <option ${(param.search_year == "2014")?"selected":"" } value="2014">2014년</option>
			            	   <option ${(param.search_year == "2013")?"selected":"" } value="2013">2013년</option>
			            	   <option ${(param.search_year == "2012")?"selected":"" } value="2012">2012년</option>
			            	   <option ${(param.search_year == "2011")?"selected":"" } value="2011">2011년</option>
			            	   <option ${(param.search_year == "2010")?"selected":"" } value="2010">2010년</option>
		            		</select>	            		
		            	</div>          		
	              	<div class="col-md-3 pb-1">
		                <select class="form-select" id="trade_gbn" name="trade_gbn">
			                 <option value="" disabled selected hidden="hidden">거래구분...</option>
			            	   <option ${(param.trade_gbn == "1")?"selected":"" } value="1">매매</option>
			            	   <option ${(param.trade_gbn == "2")?"selected":"" } value="2">전세</option>
			            	   <option ${(param.trade_gbn == "3")?"selected":"" } value="3">월세</option>
		            		</select>	            		
          		    </div>
          		    <div class="col-md-3 pb-1">
          		    <!--  아파트명 검색 -->
								    <div class="form-group row">
		                  <div class="col-md-12 pb-1">
			                  <button type="submit" class="btn btn-primary w-100"  id="tradeserach" value="normal" name="search">검 색</button>
			                </div>			            			
								    </div>
          		    <!--   끝 -->           		    
                  </div>
          		 </div>
            </form> 
					</div>
<!-- ------------------------------------------------------------ -->	
<!--      메인 내용 영역 -->	
<!-- ------------------------------------------------------------ -->	
					<div class="row">
	          <form action="../trade/tradearea" method="post" name="tradeform"> 
		          <div class="table-responsive tableFixHead" style="height:calc(100vh - 330px); overflow:auto">
		           <table class="table table-bordered table-hover table-sm">
		             <thead class="bg-secondary text-center text-white sticky-top" >
		               <tr>
		                <th>지역</th>
		                <th id="th01">1월</th>
		                <th id="th02">2월</th>
		                <th id="th03">3월</th>
		                <th id="th04">4월</th>
		                <th id="th05">5월</th>
		                <th id="th06">6월</th>
		                <th id="th07">7월</th>
		                <th id="th08">8월</th>
		                <th id="th09">9월</th>
		                <th id="th10">10월</th>
		                <th id="th11">11월</th>
		                <th id="th12">12월</th>
		                <th>합계</th>
		               </tr>
		             </thead>
		             <tbody>
		             	 <c:forEach var="n" items="${list }">	
		                <tr>
		                  <td class="text-center" id="tradearea" >${n.area }</td>
		                  <td class="text-right pe-3"><fmt:formatNumber value="${n.m01 }"/></td>
		                  <td class="text-right pe-3"><fmt:formatNumber value="${n.m02 }"/></td>
		                  <td class="text-right pe-3"><fmt:formatNumber value="${n.m03 }"/></td>
		                  <td class="text-right pe-3"><fmt:formatNumber value="${n.m04 }"/></td>
		                  <td class="text-right pe-3"><fmt:formatNumber value="${n.m05 }"/></td>
		                  <td class="text-right pe-3"><fmt:formatNumber value="${n.m06 }"/></td>
		                  <td class="text-right pe-3"><fmt:formatNumber value="${n.m07 }"/></td>
		                  <td class="text-right pe-3"><fmt:formatNumber value="${n.m08 }"/></td>
		                  <td class="text-right pe-3"><fmt:formatNumber value="${n.m09 }"/></td>
		                  <td class="text-right pe-3"><fmt:formatNumber value="${n.m10 }"/></td>
		                  <td class="text-right pe-3"><fmt:formatNumber value="${n.m11 }"/></td>
		                  <td class="text-right pe-3"><fmt:formatNumber value="${n.m12 }"/></td>
		                  <td class="text-right pe-3"><fmt:formatNumber value="${n.total }"/></td>
		                </tr>
		               </c:forEach>
		             </tbody>
		           </table>
		          </div>
		<!--          페이징   --> 
		          <div class="row mt-3 text-center">  
		           <span class="fw-bold text-danger">총 건수 : <fmt:formatNumber value="${tradetot }"/></span>
		          </div>   
	          </form>        			
					</div>				
				</div>
			</main>
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
  <script src="../js/yunsapt.js"></script>

</body>

</html>