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

				<form class="d-none d-sm-inline-block">
					<div class="input-group input-group-navbar">
						<input type="text" class="form-control" placeholder="Search all…" aria-label="Search">
						<button class="btn" type="button">
              <i class="align-middle" data-feather="search"></i>
            </button>
					</div>
				</form>
				
				<div class="col-auto ml-auto text-right mt-n1 pl-3 d-none d-sm-inline-block">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb bg-transparent p-0 mt-1 mb-0">
							<li class="breadcrumb-item"><a href="../index">홈</a></li>
							<li class="breadcrumb-item text-danger">공지사항</li>
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
					  <form class="input-group" action="../notice/notice" method="get">
						  <div class="col-md-2">
						    <select id="find-notice" class="form-select" name="f">
		              <option ${(param.f == "title")?"selected":"" } value="title">제목</option>
		              <option ${(param.f == "writer_id")?"selected":"" } value="writer_id">작성자</option>
						    </select>
						  </div>
						  <div class="col-md-4">
						    <input type="text" name="q" value="${param.q }" placeholder="검색할 단어를 입력하세요" class="form-control">
						  </div>
						  <button type="submit" class="btn btn-primary mb-1" value="검색">검 색</button>
					  </form>
					</div>
<!-- ------------------------------------------------------------ -->	
<!--      메인 내용 영역 -->	
<!-- ------------------------------------------------------------ -->	
					<div class="row">
	          <form action="../notice/notice" method="post"> 
		          <div class="table-responsive" >
		           <table class="table table-bordered table-hover table-sm">
		             <thead class="text-center text-white bg-secondary sticky-top" >
		               <tr>
		                <th>선택</th>
		                <th>순번</th>
		                <th>제목</th>
		                <th>첨부</th>
		                <th>작성자</th>
		                <th>작성일</th>
		                <th>조회수</th>
		               </tr>
		             </thead>
		             <tbody>
		             	 <c:forEach var="n" items="${list }">	
		                <tr>
		                  <td class="text-center">
		                  	<input type="checkbox" id="sel-id" name="sel-id" value="${n.id }">
		                  </td>	                   
		                  <td class="text-center">${n.id }</td>
		                  <td><a href="../notice/detail?id=${n.id }&p=${param.p}&q=${param.q}&f=${param.f}&h=${n.hit}">${n.title }</a></td>
		                  <td class="text-center"><c:if test="${n.files != null }"><i class="fas fa-link"></i></c:if></td>
		                  <td class="text-center">${n.writer_id }</td>
		                  <td class="text-center">${n.regdate }</td>
		                  <td class="text-right"><fmt:formatNumber value="${n.hit }"/> </td>
		                </tr>
		               </c:forEach>
		             </tbody>
		           </table>
		          </div>
		<!--          검색건수   --> 
		          <div class="row"> 
		            <div class="col-sm-8">
									<c:set var="page" value="${(empty param.p)?1:param.p}"/>
									<c:set var="startNum" value="${page - (page-1)%5 }"/>			
									<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/10),'.') }"/>			
									<div><span class="text-orange text-strong">${(empty param.p)?1:param.p}</span> of ${lastNum } pages(${count}건 검색 / 총 ${total_count}건 중)</div>
	              </div>
		            <div class="col-sm-2"> 
		              <a href="../notice/insert">
		              <button type="button" class="btn-primary w-100" name="cmd" value="insert">
	 								글쓰기
	 					   	  </button></a>  
	 					   	</div>
	 					   	<div class="col-sm-2">  
	 					      <button type="submit" class="btn-danger w-100" name="cmd" id="del" value="delete">
	 					      삭제
	 					      </button>
 					      </div> 		          
		          </div>		
		<!--          페이징   --> 		          
		          <div class="row pt-2">  
	              <nav aria-label="Standard pagination example">
	                <ul class="pagination justify-content-center">    
	                  <li class="page-item">
											<a class="page-link" aria-label="first" href="?p=1&f=${param.f }&q=${param.q}">
												<span aria-hidden="true" style="color:blue;font-weight: bold;">처음</span>
											</a>
	                  </li>	                                     
	                  <li class="page-item">
											<c:if test="${startNum > 1 }">	
												<a class="page-link" aria-label="Previous" href="?p=${startNum-1}&f=${param.f }&q=${param.q}">
													<span aria-hidden="true">Previous</span>
												</a>
											</c:if>      
											<c:if test="${startNum <= 1 }">	
												<span class="page-link" onclick="alert('이전 페이지가 없습니다.');">Previous</span>
											</c:if>                										            
	                  </li>
										<c:forEach var="i" begin="0" end="4">
											<c:if test="${(startNum+i) <= lastNum }">
												<li class="page-item ${page==(startNum+i)?'active':'' }"><a class="page-link" href="?p=${startNum+i}&f=${param.f }&q=${param.q}">${startNum+i}</a></li>
											</c:if>
										</c:forEach>		                  
	                  <li class="page-item">
											<c:if test="${lastNum > startNum+4 }">
												<a class="page-link" aria-label="Next" href="?p=${startNum+5}&f=${param.f }&q=${param.q}">
													<span aria-hidden="true">Next</span>
												</a>
											</c:if>
											<c:if test="${lastNum <= startNum+4 }">
												<span class="page-link" onclick="alert('다음 페이지가 없습니다.');">Next</span>
											</c:if>
	                  </li>
	                  <li class="page-item">
											<a class="page-link" aria-label="last" href="?p=${lastNum }&f=${param.f }&q=${param.q}">
												<span aria-hidden="true" style="color:blue;font-weight: bold;">끝</span>
											</a>
	                  </li>	                  
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