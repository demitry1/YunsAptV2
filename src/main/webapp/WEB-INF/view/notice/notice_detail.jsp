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
	<style type="text/css">
		.input-group-text-demi {
		  align-items: center;
		  padding: 0.375rem 0.75rem;
		  font-size: 1rem;
		  font-weight: 400;
		  line-height: 1.5;
		  color: #212529;
		  text-align: center;
		  white-space: nowrap;
		  background-color: #e9ecef;
		  border: 1px solid #ced4da;
		  vertical-align: middle;
		  border-radius: 0.25rem;
		  display:table-cell;
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
							<li class="breadcrumb-item"><a href="../notice/notice">공지사항</a></li>
							<li class="breadcrumb-item text-danger">상세조회</li>
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

					</div>
<!-- ------------------------------------------------------------ -->	
<!--      메인 내용 영역 -->	
<!-- ------------------------------------------------------------ -->	
					<div class="row">
	          <!-- 공지사항 상세내용 -->
 						<h5 class="card-title text-center"><strong>공지사항 상세조회</strong></h5>				
	          <div class="row">
	            <div class="col col-1" ></div> 
	            <span class="col-2 input-group-text-demi" ><b>제    목</b></span>
	            <input type="text" value="${n.title }" class="col-6 form-control" style="width:70%; background: white;" readonly>
	            <div class="col col-1"></div> 
	          </div> 
	          <div class="row">
	            <div class="col col-1" ></div> 
	            <span class="col-2 input-group-text-demi"><b>작성일</b></span>
	            <input type="text" value="${n.regdate }" class="col-6 form-control" style="width:70%; background: white;" readonly>
	            <div class="col col-1"></div> 
	          </div>
	          <div class="row">
	            <div class="col col-1" ></div> 
	            <span class="col-2 input-group-text-demi"><b>작성자</b></span>
	            <input type="text" value="${n.writer_id }" class="col-3 form-control" style="width:25%;background: white;" readonly>
	            <span class="col-2 input-group-text-demi" ><b>조회수</b></span>
	            <input type="text" value="${n.hit }" class="col-3 form-control" style="width:28.4%;background: white;" readonly>
	            <div class="col col-1"></div> 
	          </div>                     
	          <div class="row">
	            <div class="col col-1" ></div> 
	            <span class="col-2 input-group-text-demi" ><b>첨부파일</b></span>
	            <span class="col-2 input-group-text-demi" style="width:70%;background: white;">
	            	<c:forTokens items="${n.files }" delims="," var="fileName" varStatus="st">
	        	    	<a href="/upload/${fileName }">${fileName } </a>
	        	    	<c:if test="${!st.last }">
	        	    		/    
	        	    	</c:if>
	            	</c:forTokens>
	            </span>
	            <div class="col col-1"></div> 
	          </div>
	          <div class="row">
	            <div class="col col-1" ></div> 
	            <span class="col-2 input-group-text-demi"><b>내 용</b></span>
	            <textarea class="col-6 form-control" readonly style="width:70%;background: white;height:150px" aria-label="With textarea">${n.content }</textarea>
	            <div class="col col-1"></div> 
	          </div>	          				
<!-- 목록으로 가기버튼 -->
						<form action="../notice/detail" method="post">
		 					<div class="row m-3">
		 					<!--  삭제후에 공지사항 리스트 조회화면 표시를 위내 넘겨줄 값 세팅 -->
		 					   <input type="hidden" name="ndid" value="${n.id }"	>
		 					   <input type="hidden" name="p" value="${param.p }"	>
		 					   <input type="hidden" name="q" value="${param.q }"	>
		 					   <input type="hidden" name="f" value="${param.f }"	>
		 						 <table>
		 						 	<tr>
		 						 		<td class="col-4 "></td>
		 						 		<td class="col-7 ">
								 			<a href="../notice/modify?id=${n.id }&p=${param.p }&q=${param.q }&f=${param.f }">
		 						 				<button type="button" class="col-3 btn-primary" name="cmd" value="insert">
					 								수정
					 					    </button>	
				 					    </a>
				 					    <button type="submit" class="col-3 btn-danger" name="cmd" id="nddel" value="delete">삭제</button>
							 				<a href="../notice/notice?p=${param.p }&q=${param.q }&f=${param.f }">
		 						 				<button type="button" class="col-3 btn-primary" name="cmd" value="insert">
					 								목록
					 					    </button>	
				 					    </a>
		 						 		</td>
		 						 		<td class="col-3 "></td>
		 						 	</tr>
		 						 </table>     
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