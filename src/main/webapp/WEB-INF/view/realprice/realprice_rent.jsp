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

				<form class="d-none d-sm-inline-block" name="realall" method="get">
					<div class="input-group input-group-navbar">
						<input type="text" class="form-control" placeholder="Search all…" aria-label="Search" name="realall" value="${param.realall }">
						<button class="btn" type="submit" id="realallserach" value="all" name="search">
              <i class="align-middle" data-feather="search"></i>
            </button>
					</div>
				  <input type="hidden" id="scale_id" name="scale" value="${param.scale }">
				  <input type="hidden" id="contract_date1_id" name="contract_date1" size="15" value="${param.contract_date1 }">
				  <input type="hidden" id="contract_date2_id" name="contract_date2" size="10" value="${param.contract_date2 }">
				  <input type="hidden" id="construction_year_id" name="construction_year" value="${param.construction_year }">
				  <input type="hidden" id="listcnt_id" name="listcnt" value="${param.listcnt }">
		      <input type="hidden" id="kind_id" name="kind" value="${param.kind }">
				</form>
				
				<div class="col-auto ml-auto text-right mt-n1 pl-3 d-none d-sm-inline-block">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb bg-transparent p-0 mt-1 mb-0">
							<li class="breadcrumb-item"><a href="../index">홈</a></li>
							<li class="breadcrumb-item"><a href="../index">실거래가</a></li>
							<li class="breadcrumb-item text-danger">전/월세</li>
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
						<form action="../realprice/realpricerent" method="get" name="f" id="f">
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
					      <div class="col-md-4 pb-1">
					        <select class="form-select" id="apt_name" name="apt_name" >
					       	 <option value="" disabled selected hidden="hidden">아파트명 선택...</option>
                		 <option ${(param.apt_name != null)?"selected":"" }>${param.apt_name} </option>			 								       	 
					        </select>
					      </div>	            		
              	<div class="col-md-2 pb-1">
	                <select class="form-select" id="scale" name="scale">
	                 <option value="" disabled selected hidden="hidden">평형 선택...</option>
	            	   <option ${(param.scale == "1")?"selected":"" } value="1">전체</option>
	            	   <option ${(param.scale == "2")?"selected":"" } value="2">40평이상</option>
	            	   <option ${(param.scale == "3")?"selected":"" } value="3">30평~39평</option>
	            	   <option ${(param.scale == "4")?"selected":"" } value="4">20평~29평</option>
	            	   <option ${(param.scale == "5")?"selected":"" } value="5">20평미만</option>
	            		</select>
            		</div>	            		
           	 </div>
           	 
         		 <div class="row mt-2 mb-3">
            		<div class="col-md-2 pb-1 ">
									<input type="text" name="contract_date1" class="datapicker form-select" id="datepicker" size="15" value="${param.contract_date1 }">
								</div>	
            		<div class="col-md-2 pb-1">
									<input type="text" name="contract_date2" class="datapicker form-select" id="datepicker2" size="10" value="${param.contract_date2 }">
            		</div>          		
              	<div class="col-md-2 pb-1">
	                <select class="form-select" id="construction_year" name="construction_year">
		                 <option value="" disabled selected hidden="hidden">건축년도...</option>
		            	   <option ${(param.construction_year == "1")?"selected":"" } value="1">전체</option>
		            	   <option ${(param.construction_year == "2")?"selected":"" } value="2">3년이하</option>
		            	   <option ${(param.construction_year == "3")?"selected":"" } value="3">5년이하</option>
		            	   <option ${(param.construction_year == "4")?"selected":"" } value="4">10년이하</option>
		            	   <option ${(param.construction_year == "5")?"selected":"" } value="5">15년이하</option>
		            	   <option ${(param.construction_year == "6")?"selected":"" } value="6">20년이하</option>
		            	   <option ${(param.construction_year == "7")?"selected":"" } value="7">25년이하</option>
		            	   <option ${(param.construction_year == "8")?"selected":"" } value="8">30년이하</option>
		            	   <option ${(param.construction_year == "9")?"selected":"" } value="9">30년초과</option>
	            		</select>
         		    </div>
         		    <div class="col-md-6 pb-1">
         		    <!--  아파트명 검색 -->
							    <div class="form-group row">
							      <div class="col-md-4 pb-1">
			                <select class="form-select" id="kind" name="kind">
		                   <option value="" disabled selected hidden="hidden">전월세...</option>
			            	   <option ${(param.kind == "전세")?"selected":"" } value="전세">전세</option>
			            	   <option ${(param.kind == "월세")?"selected":"" } value="월세">월세</option>
			            		</select>
		            		</div>
							      <div class="col-md-4 pb-1">
			                <select class="form-select" id="listcnt" name="listcnt">
			            	   <option ${(param.listcnt == "40")?"selected":"" } value="40">40개</option>
			            	   <option ${(param.listcnt == "20")?"selected":"" } value="20">20개</option>
			            	   <option ${(param.listcnt == "60")?"selected":"" } value="60">60개</option>
			            	   <option ${(param.listcnt == "80")?"selected":"" } value="80">80개</option>
			            	   <option ${(param.listcnt == "100")?"selected":"" } value="100">100개</option>
			            		</select>
		            		</div>
	                  <div class="col-md-4 pb-1">
		                  <button type="submit" class="btn btn-primary w-100"  id="realserach" value="normal" name="search">검 색</button>
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
		        <form action="../realprice/realpricerent" method="post" name="realform"> 
		          <div class="table-responsive tableFixHead" style="height:calc(100vh - 375px); overflow:auto">
		           <table class="table table-bordered table-hover table-sm">
		             <thead class="bg-secondary text-center text-white sticky-top" >
		               <tr>
		                <th>시(도)</th>
		                <th>구(시)</th>
		                <th>동(구)</th>
		                <th>아파트명</th>
		                <th>전월세</th>
		                <th>계약일자</th>
		                <th>면적</th>
		                <th>평수</th>
		                <th>보증금</th>
		                <th>월세</th>
		                <th>층</th>
		                <th>건축년도</th>
		               </tr>
		             </thead>
		             <tbody>
		             	 <c:forEach var="n" items="${list }">	
		                <tr>
		                  <td class="text-center">${n.city_do }</td>
		                  <td class="text-center">${n.city_gu }</td>
		                  <td class="text-center">${n.dong_gu }</td>
		                  <td class="text-center text-primary r_apt_namerent" id="r_apt_namerent"  style="cursor:pointer">${n.apt_name }</td>
		                  <td class="text-center">${n.kind }</td>
		                  <td class="text-center">${n.contract_date }</td>
		                  <td class="text-center">${n.square }</td>
		                  <td class="text-center">${n.scale }</td>
		                  <td class="text-right"><fmt:formatNumber value="${n.deposit }" /></td>
		                  <td class="text-right"><fmt:formatNumber value="${n.monthrent }" /></td>
		                  <td class="text-center">${n.floor }</td>
		                  <td class="text-center">${n.construction_year }</td>
		                </tr>
		               </c:forEach>
		             </tbody>
		           </table>
		          </div>
		<!--          검색건수   --> 
		          <div class="row mt-3"> 
		            <div class="col-sm-8">
									<c:set var="page" value="${(empty param.p)?1:param.p}"/>
									<c:set var="startNum" value="${page - (page-1)%5 }"/>			
									<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/listcnt),'.') }"/>			
									<div><span class="text-orange text-strong">
										${(empty param.p)?1:param.p}</span> of <fmt:formatNumber value="${lastNum }"/> pages(<fmt:formatNumber value="${count}"/>건 검색 / 총 <fmt:formatNumber value="${total_count}"/>건 중)
									</div>
		            </div>		          
		          </div>		          
		<!--          페이징   --> 
							<div class="row pt-2">
	              <nav aria-label="Standard pagination example">
	                <ul class="pagination justify-content-center">      
	                  <li class="page-item">
											<a class="page-link" aria-label="first" 
											   href="?p=1&city_do=${param.city_do}&city_gu=${param.city_gu}&dong_gu=${param.dong_gu}&scale=${param.scale}&contract_date1=${param.contract_date1}&contract_date2=${param.contract_date2}&construction_year=${param.construction_year}&apt_name=${param.apt_name}&search=${param.search}&realall=${param.realall}&listcnt=${param.listcnt}&kind=${param.kind}">
												<span aria-hidden="true" style="color:blue;font-weight: bold;">처음</span>
											</a>
	                  </li>	                                   
	                  <li class="page-item">
											<c:if test="${startNum > 1 }">	
												<a class="page-link" aria-label="Previous" 
												   href="?p=${startNum-1}&city_do=${param.city_do}&city_gu=${param.city_gu}&dong_gu=${param.dong_gu}&scale=${param.scale}&contract_date1=${param.contract_date1}&contract_date2=${param.contract_date2}&construction_year=${param.construction_year}&apt_name=${param.apt_name}&search=${param.search}&realall=${param.realall}&listcnt=${param.listcnt}&kind=${param.kind}">
													<span aria-hidden="true">Previous</span>
												</a>
											</c:if>      
											<c:if test="${startNum <= 1 }">	
												<span class="page-link" onclick="alert('이전 페이지가 없습니다.');">Previous</span>
											</c:if>                										            
	                  </li>
										<c:forEach var="i" begin="0" end="4">
											<c:if test="${(startNum+i) <= lastNum }">
												<li class="page-item ${page==(startNum+i)?'active':'' }">
													<a class="page-link" href="?p=${startNum+i}&city_do=${param.city_do}&city_gu=${param.city_gu}&dong_gu=${param.dong_gu}&scale=${param.scale}&contract_date1=${param.contract_date1}&contract_date2=${param.contract_date2}&construction_year=${param.construction_year}&apt_name=${param.apt_name}&search=${param.search}&realall=${param.realall}&listcnt=${param.listcnt}&kind=${param.kind}">${startNum+i}
													 </a>
												 </li>
											</c:if>
										</c:forEach>		                  
	                  <li class="page-item">
											<c:if test="${lastNum > startNum+4 }">
												<a class="page-link" aria-label="Next" 
												   href="?p=${startNum+5}&city_do=${param.city_do}&city_gu=${param.city_gu}&dong_gu=${param.dong_gu}&scale=${param.scale}&contract_date1=${param.contract_date1}&contract_date2=${param.contract_date2}&construction_year=${param.construction_year}&apt_name=${param.apt_name}&search=${param.search}&realall=${param.realall}&listcnt=${param.listcnt}&kind=${param.kind}">
													<span aria-hidden="true">Next</span>
												</a>
											</c:if>
											<c:if test="${lastNum <= startNum+4 }">
												<span class="page-link" onclick="alert('다음 페이지가 없습니다.');">Next</span>
											</c:if>
	                  </li>
	                  <!--  마지막페이지 테스트 -->
	                  <li class="page-item">
											<a class="page-link" aria-label="Last" 
											   href="?p=${lastNum }&city_do=${param.city_do}&city_gu=${param.city_gu}&dong_gu=${param.dong_gu}&scale=${param.scale}&contract_date1=${param.contract_date1}&contract_date2=${param.contract_date2}&construction_year=${param.construction_year}&apt_name=${param.apt_name}&search=${param.search}&realall=${param.realall}&listcnt=${param.listcnt}&kind=${param.kind}">
												<span aria-hidden="true" style="color:blue;font-weight: bold;">끝</span>
											</a>
	                  </li>	                  
	                  <!--  마지막페이지 테스트 -->
	                </ul>
	              </nav>			      
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