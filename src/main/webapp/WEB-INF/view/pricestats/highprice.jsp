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
  <STYLE>
		.ui-datepicker-calendar{
		    display: none;
		 }​
  </STYLE>

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
					<input type="text" class="form-control" placeholder="Search all…" aria-label="Search" name="tradeall" value="${param.tradeall }">
					 <button class="btn" type="submit" id="tradeserachall" value="all" name="search">
             <i class="align-middle" data-feather="search"></i>
           </button>
					</div>
				 <input type="hidden" id="datetypeid" name="datetype" value= "${param.datetype}" >
				 <input type="hidden" id="contract_date1_id" name="contract_date1" value= "${param.contract_date1}" >
				 <input type="hidden" id="contract_date2_id" name="contract_date2" value= "${param.contract_date2}" >
				 <input type="hidden" id="trade_gbn_id" name="trade_gbn" value= "${param.trade_gbn}" >
				</form>
				
				<div class="col-auto ml-auto text-right mt-n1 pl-3 d-none d-sm-inline-block">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb bg-transparent p-0 mt-1 mb-0">
							<li class="breadcrumb-item"><a href="../index">홈</a></li>
							<li class="breadcrumb-item"><a href="../index">가격현황</a></li>
							<li class="breadcrumb-item text-danger">신고가현황</li>
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
            <form action="../pricestats/highprice" method="get" name="f" id="f">
           		 <div class="row">
	              	<div class="col-md-2 pb-1">
		                <select class="form-select" id="city_do" name="city_do">
		                 <option value="" disabled selected hidden="hidden">시(도) 선택...</option>
		                 <option ${(param.city_do != null)?"selected":"" }>${param.city_do} </option>
		            		</select>
	            		</div>
	              	<div class="col-md-2 pb-1">
		                <select class="form-select" id="city_gu" name="city_gu">
		                 <option value="" disabled selected hidden="hidden">구(시) 선택...</option>
		                 <option ${(param.city_gu != null)?"selected":"" }>${param.city_gu} </option>		                 
		            		</select>
	            		</div>
	              	<div class="col-md-2 pb-1">
		                <select class="form-select" id="dong_gu" name="dong_gu">
		                 <option value="" disabled selected hidden="hidden">동(구) 선택...</option>
		                 <option ${(param.dong_gu != null)?"selected":"" }>${param.dong_gu} </option>			                 
		            		</select>
	            		</div>          		
	            		<div class="col-md-2 pb-1 ">
										<input type="text" name="contract_ym" class="datapicker form-select" id="datepicker5" size="15" value="${param.contract_date1 }">
									</div>	
                  <div class="col-md-2 pb-1">
	                  <button type="submit" class="btn btn-primary w-100"  id="tradeserach" value="normal" name="search">검 색</button>
	                </div>										
            	 </div>
            </form>
					</div>
          <div class="row pb-3">
     
   			  </div>
<!-- ------------------------------------------------------------ -->	
<!--      메인 내용 영역 -->	
<!-- ------------------------------------------------------------ -->	
					<div class="row">
	          <form action="../trade/tradestatus" method="post" name="tradeform"> 
		          <div class="table-responsive tableFixHead" style="height:calc(100vh - 330px); overflow:auto">
		           <table class="table table-bordered table-hover table-sm">
		             <thead class="bg-secondary text-center text-white sticky-top" >
		               <tr>
		                <th>년/월/일</th>
		                <th>거래건수</th>
		                <th>증감건수</th>
		                <th>증감율</th>
		               </tr>
		             </thead>
		             <tbody>
		             	 <c:forEach var="n" items="${list }">	
		                <tr>
		                  <td class="text-center" id="tradest" >${n.trade_dd }</td>
		                  <td class="text-right pe-3"><fmt:formatNumber value="${n.trade_cnt }"/></td>
		                  <c:if test="${n.change_rate < 0 }">
			                 <td class="text-right pe-3 text-primary"><fmt:formatNumber value="${n.change_cnt }"/></td>
		             	     <td class="text-right pe-3 text-primary">${n.change_rate }</td>
		                  </c:if>
		                  <c:if test="${n.change_rate >= 0 }">
			                 <td class="text-right pe-3 text-danger"><fmt:formatNumber value="${n.change_cnt }"/></td>
		             	     <td class="text-right pe-3 text-danger">${n.change_rate }</td>
		                  </c:if>
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